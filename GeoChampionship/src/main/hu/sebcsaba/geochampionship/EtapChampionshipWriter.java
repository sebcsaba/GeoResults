package hu.sebcsaba.geochampionship;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class EtapChampionshipWriter extends ChampionshipWriter {

	private static final String SQL_COLUMNS = "SELECT nev,eid FROM v_etapok WHERE vid=? AND ertekelendo=1";
	private static final String SQL_RESULTS = "SELECT rajtszam,pontszam FROM r_etaperedmenyek WHERE eid=? ORDER BY pontszam ASC";

	public EtapChampionshipWriter(Connection conn, Properties config) {
		super(conn, config, "etapok");
	}

	@Override
	protected ResultTable createResultTable() throws SQLException, ConfigException {
		return new ResultTable(conn,config,type,new EtapMetaNamedData(),nevezesMap,pointList,SQL_COLUMNS,SQL_RESULTS);
	}

	@Override
	protected boolean isDoubleRow() {
		return true;
	}

	@Override
	protected NevezesMap createNevezesMap() throws ConfigException {
		return new EtapNevezesMap(config.getProperty("etapok.nevezesek"));
	}
	
}
