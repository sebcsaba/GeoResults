package hu.sebcsaba.geochampionship;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ResultTable {
	
	private final Connection conn;
	private final Properties config;
	private final String type;
	
	private final MetaNamedData metaNamedData;
	private final NevezesMap nevezesMap;
	private final PointList points;
	private List<ResultRow> rows;
	private final List<Pair<NameWithId,List<NameWithId>>> columns = new ArrayList<Pair<NameWithId,List<NameWithId>>>();
	
	/**
	 * @param columnsSql Olyan SQL-t ad, ami (string,long) oszlopban visszaadja egy adott 
	 * verseny megfelelő versenyszámait (név,id). A megfelelő vid helyén ? szerepeljen.
	 * @throws ConfigException 
	 */
	public ResultTable(Connection conn, Properties config, String type, MetaNamedData metaNamedData, NevezesMap nevezesMap, PointList points, String columnsSql, String dataSql) throws SQLException, ConfigException {
		this.conn = conn;
		this.config = config;
		this.type = type;
		this.metaNamedData = metaNamedData;
		this.nevezesMap = nevezesMap;
		this.points = points;
		buildColumns(columnsSql);
		buildRows(dataSql);
	}

	public MetaNamedData getMetaNamedData() {
		return metaNamedData;
	}
	
	public List<ResultRow> getRows() {
		return rows;
	}

	public List<Pair<NameWithId,List<NameWithId>>> getColumns() {
		return columns;
	}
	
	public int getTotalColumnsCount() {
		int acc = 0;
		for (Pair<NameWithId,List<NameWithId>> item : columns) {
			acc += item.second.size();
		}
		return acc;
	}
	
	private void buildColumns(String columnsSql) throws SQLException {
		String vids = config.getProperty("source.vids");
		if (vids==null || !vids.matches("(?:\\d+,)*\\d+")) throw new SQLException("source.vids config parameter is not a comma-separated list of longs.");
		String blocksSql = "SELECT nev,vid FROM v_versenyek WHERE vid IN ("+vids+")";
		PreparedStatement colStm = conn.prepareStatement(columnsSql);
		ResultSet blockRs = conn.createStatement().executeQuery(blocksSql);
		while (blockRs.next()) {
			NameWithId block = new NameWithId(blockRs.getString(1),blockRs.getLong(2));
			List<NameWithId> blockColumns = new ArrayList<NameWithId>();
			colStm.setLong(1, block.getId());
			ResultSet columnRs = colStm.executeQuery();
			while (columnRs.next()) {
				NameWithId col = new NameWithId(columnRs.getString(1),columnRs.getLong(2));
				blockColumns.add(col);
			}
			columns.add(new Pair<NameWithId, List<NameWithId>>(block,blockColumns));
		}
	}
	
	private void buildRows(String dataSql) throws SQLException, ConfigException {
		Map<Long,ResultRow> myrows = new HashMap<Long,ResultRow>();
		PreparedStatement dataStm = conn.prepareStatement(dataSql);
		for (Pair<NameWithId, List<NameWithId>> block : columns) {
			for (NameWithId col : block.second) {
				dataStm.setLong(1, col.getId());
				int previousPlace = 0;
				int previousPoint = -1;
				int index = 1;
				for (ResultSet dataRs = dataStm.executeQuery(); dataRs.next(); ++index) {
					int rajtszam = dataRs.getInt(1);
					int pontszam = dataRs.getInt(2);
					int place;
					if (pontszam==previousPoint) {
						place = previousPlace;
					} else {
						place = index;
						previousPlace = index;
						previousPoint = pontszam;
					}
					ResultCell cell = new ResultCell(place,points.get(place));
					long vid = block.first.getId();
					long nevezesId = nevezesMap.getId(vid,rajtszam);
					if (!myrows.containsKey(nevezesId)) {
						myrows.put(nevezesId, new ResultRow(nevezesMap.getNevezes(nevezesId)));
					}
					myrows.get(nevezesId).addCell(col.getId(), cell);
					myrows.get(nevezesId).addRajtszam(vid, rajtszam);
				}
			}
		}
		addExtraPoints(myrows);
		rows = new ArrayList<ResultRow>(myrows.values());
		Collections.sort(rows, new ResultRow.Cmp());
	}
	
	private void addExtraPoints(Map<Long,ResultRow> myrows) throws ConfigException {
		String extra = config.getProperty(type+".extra_pontok");
		if (extra!=null && extra.length()>0) {
			for (String def : extra.split(";")) {
				String[] parts = def.split(":");
				long nevezesId = nevezesMap.getId(Integer.parseInt(parts[1]), Integer.parseInt(parts[0]));
				ResultRow resultRow = myrows.get(nevezesId);
				List<NameWithId> vparts = findVersenyPartsByVid(Integer.parseInt(parts[2]));
				String[] extraPoints = parts[3].split(",");
				for (int i=0; i<extraPoints.length; ++i) {
					if (extraPoints[i].length()>0) {
						long vszid = vparts.get(i).getId();
						resultRow.addCell(vszid, new ResultCell(0, Integer.parseInt(extraPoints[i])));
					}
				}
			}
		}
	}
	
	private List<NameWithId> findVersenyPartsByVid(long vid) {
		for (Pair<NameWithId,List<NameWithId>> column : columns) {
			if (column.getFirst().getId()==vid) {
				return column.getSecond();
			}
		}
		throw new NullPointerException();
	}

}
