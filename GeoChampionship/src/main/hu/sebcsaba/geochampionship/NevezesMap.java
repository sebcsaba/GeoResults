package hu.sebcsaba.geochampionship;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class NevezesMap {

	private static final String SQL_NEVEZESEK = "SELECT * FROM n_nevezesek WHERE vid=?";
	
	private final Sequence seq = new Sequence();
	protected final Map<Long,NamedData> nevezesek = new HashMap<Long,NamedData>();
	private final Map<Long,Map<Long,Integer>> rajtszamok = new HashMap<Long,Map<Long,Integer>>();
	private final List<Pair<Long,List<Integer>>> configNevezesek = new ArrayList<Pair<Long,List<Integer>>>();
	
	public NevezesMap(String config_nevezesek) throws ConfigException {
		parseConfigNevezesek(config_nevezesek);
	}

	private void parseConfigNevezesek(String config_nevezesek) throws ConfigException {
		if (config_nevezesek==null || config_nevezesek.length()==0) return;
		String[] blocks = config_nevezesek.split(";");
		for (String block : blocks) {
			if (!block.startsWith("(") || !block.endsWith(")")) throw new ConfigException("Invalid nevezesek string: ( and ) expected, "+block+" found.");
			String[] rsz = block.substring(1, block.length()-1).split(",");
			List<Integer> rajtszamLista = new ArrayList<Integer>(rsz.length);
			for (String r : rsz) {
				if (r==null || r.length()==0) {
					rajtszamLista.add(null);
				} else {
					rajtszamLista.add(Integer.valueOf(r));
				}
			}
			configNevezesek.add(new Pair<Long,List<Integer>>(seq.getNext(),rajtszamLista));
		}
	}

	public long getId(long vid, int rajtszam) throws ConfigException {
		for (Map.Entry<Long,Map<Long,Integer>> re : rajtszamok.entrySet()) {
			Integer x = re.getValue().get(vid);
			if (x!=null && x==rajtszam) return re.getKey();
		}
		throw new ConfigException("cannot find assigned id for vid="+vid+", rajtszam="+rajtszam);
	}
	
	public NamedData getNevezes(long id) {
		return nevezesek.get(id);
	}
	
	public int getRajtszam(long id, long vid) {
		return rajtszamok.get(id).get(vid);
	}

	public void build(Connection conn, long[] vids) throws SQLException {
		PreparedStatement colStm = conn.prepareStatement(SQL_NEVEZESEK);
		int versenyIndex = 0;
		for (long vid : vids) {
			colStm.setLong(1, vid);
			ResultSet nevezesRs = colStm.executeQuery();
			while (nevezesRs.next()) {
				Nevezes n = new Nevezes(nevezesRs);
				// config alapján keresünk
				Long id = getConfigAssignedId(versenyIndex, n.rajtszam);
				if (id==null) {
					// korábbi nevezések alapján keresünk
					id = findMatchingNevezes(n);
					if (id==null) {
						// új, adunk neki id-t
						id = seq.getNext();
					}
				}
				if (nevezesek.containsKey(id)) {
					appendNevezes(nevezesek.get(id),n);
				} else {
					nevezesek.put(id, createNamedData(n));
				}
				if (!rajtszamok.containsKey(id)) rajtszamok.put(id, new HashMap<Long,Integer>());
				rajtszamok.get(id).put(vid, n.rajtszam);
			}
			++versenyIndex;
		}
	}
	
	private Long getConfigAssignedId(int versenyIndex, int rajtszam) {
		for (Pair<Long,List<Integer>> cn : configNevezesek) {
			if (cn.second.get(versenyIndex)!=null && cn.second.get(versenyIndex)==rajtszam) {
				return cn.first;
			}
		}
		return null;
	}
	
	/**
	 * A megadott nevezésnek megfelelőt keres a korábbiak között.
	 * @param n A keresett nevezés adatai.
	 * @return A talált nevezés azonosítója, vagy null ha nem talált.
	 */
	protected abstract Long findMatchingNevezes(Nevezes n);

	/**
	 * Létrehozza a nevezésnek megfelelő NamedData objektumot.
	 * @param n A kiinduló nevezés adatai.
	 * @return A megfelelő NamedData.
	 */
	protected abstract NamedData createNamedData(Nevezes n);

	/**
	 * A korábban illeszkedőnek jelölt NamedData objektumot szükség
	 * szerint kiegészíti további adatokkal.
	 * @param namedData Az illeszkedő NamedData.
	 * @param n A rá illeszkedő nevezés adatai.
	 */
	protected abstract void appendNevezes(NamedData namedData, Nevezes n);

	protected static class Nevezes {
		
		public final int rajtszam;
		public final String sofor;
		public final String navigator;
		public final String utas1, utas2, utas3;
		public final String orszag;

		public Nevezes(ResultSet rs) throws SQLException {
			rajtszam = rs.getInt("rajtszam");
			sofor = rs.getString("sofor");
			navigator = rs.getString("navigator");
			utas1 = rs.getString("utas1");
			utas2 = rs.getString("utas2");
			utas3 = rs.getString("utas3");
			orszag = rs.getString("orszag");
		}
		
	}
	
}
