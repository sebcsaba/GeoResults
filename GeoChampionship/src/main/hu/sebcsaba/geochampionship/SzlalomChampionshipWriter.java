package hu.sebcsaba.geochampionship;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class SzlalomChampionshipWriter extends ChampionshipWriter {

	private static final String SQL_COLUMNS = "SELECT nev,szlid FROM v_szlalomok WHERE vid=? AND ertekelendo=1";
	private static final String SQL_RESULTS = "SELECT rajtszam,pontszam FROM r_szlalomeredmenyek WHERE szlid=? ORDER BY pontszam ASC";

	public SzlalomChampionshipWriter(Connection conn, Properties config) {
		super(conn, config, "szlalomok");
	}

	@Override
	protected ResultTable createResultTable() throws SQLException, ConfigException {
		return new ResultTable(conn,config,type,new SzlalomMetaNamedData(),nevezesMap,pointList,SQL_COLUMNS,SQL_RESULTS);
	}

	@Override
	protected boolean isDoubleRow() {
		return false;
	}
	
	@Override
	protected NevezesMap createNevezesMap() throws ConfigException {
		return new SzlalomNevezesMap(config.getProperty("szlalomok.nevezesek"));
	}

}
