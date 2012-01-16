package hu.sebcsaba.geochampionship;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ResultRow {
	
	private final NamedData namedData;
	private final Map<Long,ResultCell> cells = new HashMap<Long,ResultCell>();
	private final Map<Long,Integer> rajtszamok = new HashMap<Long, Integer>();
	private int sumPoints = 0;
	
	public ResultRow(NamedData namedData) {
		this.namedData = namedData;
	}
	
	public void addRajtszam(long vid, int rajtszam) {
		rajtszamok.put(vid, rajtszam);
	}
	
	public void addCell(long vszid, ResultCell cell) {
		cells.put(vszid,cell);
		sumPoints += cell.getPoint();
	}
	
	public NamedData getNamedData() {
		return namedData;
	}
	
	public int getSumPoints() {
		return sumPoints;
	}
	
	public ResultCell getCell(long vszid) {
		return cells.get(vszid);
	}
	
	public Integer getRajtszam(long vid) {
		return rajtszamok.get(vid);
	}
	
	public static class Cmp implements Comparator<ResultRow> {

		@Override
		public int compare(ResultRow r1, ResultRow r2) {
			if (r1.sumPoints!=r2.sumPoints) return r2.sumPoints-r1.sumPoints;
			return r2.cells.size()-r1.cells.size();
		}
		
	}

}
