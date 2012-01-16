package hu.sebcsaba.geochampionship;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public abstract class ChampionshipWriter {
	
	protected final Connection conn;
	protected final Properties config;
	protected final String type;
	
	private long[] vids;
	protected PointList pointList;
	protected NevezesMap nevezesMap;
	
	private static final String HEADER1 = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n<html>\n\t<head>\n\t\t<title>GeoChampionship 2009</title>\n\t\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n\t\t<link rel=\"shortcut icon\" href=\"../common/favicon.ico\" />\n\t\t<link rel=\"icon\" href=\"../common/favicon.ico\" />\n\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"../common/base.css\" />\n\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"../common/menu.css\" />\n\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"../common/panel.css\" />\n\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"../common/form.css\" />\n\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"../common/crud.css\" />\n\t\t<script type=\"text/javascript\" src=\"../common/commonUtils.js\"></script>\n\t\t<script type=\"text/javascript\" src=\"../common/domUtils.js\"></script>\n\t\t<script type=\"text/javascript\" src=\"../common/menuUtils.js\"></script>\n\t\t<script type=\"text/javascript\" src=\"../common/formUtils.js\"></script>\n\t\t<script type=\"text/javascript\" src=\"../common/popupUtils.js\"></script>\n\t\t<script type=\"text/javascript\" src=\"../common/titleUtils.js\"></script>\n\t\t<script type=\"text/javascript\" src=\"../common/addonUtils.js\"></script>\n\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"../common/title.css\" />\n\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"../common/megjelenites.css\" />\n\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"../common/megjelenitesTabla.css\" />\n\t</head>\n\t<body>\n\n\t\t<div class=\"navigBar\">\n\t\t\t<div class=\"title\"><img class=\"title\" src=\"../common/logo2.png\" alt=\"GeoResults2006 logo\" onclick=\"kezdolap();\"/></div>\n\t\t\t<div class=\"addons\">\n\t\t\t\t<div class=\"addonButton\" id=\"print\" onclick=\"print();\">Nyomtatás</div>\n\t\t\t\t<div class=\"addonButton\" id=\"close\" onclick=\"closeEredmenyPopup();\">Vissza</div>\n\t\t\t</div>\n\t\t</div>\n\n\t\t<div class=\"displayBlock\">\n\t\t\t<div class=\"pageHeader\">";
	private static final String HEADER2 = "</div>\n\n\t\t\t<table class=\"results\">\n";
	private static final String FOOTER = "\t\t\t</table>\n\n\t\t<div class=\"copyright\">GeoChampionship - &copy; SebCsaba 2009</div>\n\t</div>\n</body>\n</html>\n";

	public ChampionshipWriter(Connection conn, Properties config, String type) {
		this.conn = conn;
		this.config = config;
		this.type = type;
	}

	public void run() throws ConfigException, SQLException, IOException {
		pointList = new PointList(config.getProperty("source.points"));
		parseVids();
		nevezesMap = createNevezesMap();
		nevezesMap.build(conn,vids);
		ResultTable resultTable = createResultTable();
		print(resultTable);
	}
	
	private void parseVids() throws ConfigException {
		String vidsStr = config.getProperty("source.vids");
		if (vidsStr==null) throw new ConfigException("source.vids not defined!");
		String[] vidsList = vidsStr.split(",");
		vids = new long[vidsList.length];
		for (int i=0; i<vidsList.length; ++i) {
			try {
				vids[i] = Long.parseLong(vidsList[i]);
			} catch (NumberFormatException e) {
				throw new ConfigException("invalid id in source.vids: "+vidsList[i]);
			}
		}
	}

	private void print(ResultTable resultTable) throws IOException {
		PrintWriter out = new PrintWriter(config.getProperty(type+".file"),"UTF-8");
		out.print(HEADER1);
		out.print(config.getProperty("output.title"));
		out.print(" - ");
		out.print(config.getProperty(type+".title"));
		out.print(HEADER2);
		out.println("<col style=\"width:12.0mm;\"></col>");
		out.println("<col style=\"width:"+resultTable.getMetaNamedData().getWidthPerCol()+"mm;\" span=\""+resultTable.getMetaNamedData().getColspan()+"\"></col>");
		out.println("<col style=\"width:8.0mm;\" span=\""+(resultTable.getTotalColumnsCount()*2+resultTable.getColumns().size())+"\"></col>");
		out.println("<col style=\"width:20.0mm;\"></col>");
		printTableHeadrows(out,resultTable);
		List<ResultRow> resultRows = resultTable.getRows();
		int i = 0;
		for (ResultRow resultRow : resultRows) printTableRow(out,++i,resultTable,resultRow);
		out.print(FOOTER);
		out.close();
	}
	
	private void printTableHeadrows(PrintWriter out, ResultTable resultTable) {
		out.println("<thead>");
		out.println("<tr class=\"row\" style=\"height:9.0mm;\">");
		out.println("<th rowspan=\"2\" class=\"lrtb bold header\">Hely</th>");
		out.println(resultTable.getMetaNamedData().getFirstRowCells());
		List<Pair<NameWithId,List<NameWithId>>> columns = resultTable.getColumns();
		for (int i=0; i<columns.size(); ++i) {
			int colspan = columns.get(i).second.size()*2+1;
			out.println("<th colspan=\""+colspan+"\" class=\"lrt bold header\">"+columns.get(i).first.getName()+"</th>");
		}
		out.println("<th rowspan=\"2\" class=\"lrtb bold header\">Pont</th>");
		out.println("</tr>");
		out.println("<tr class=\"row\" style=\"height:9.0mm;\">");
		out.println(resultTable.getMetaNamedData().getSecondRowCells());
		for (int i=0; i<columns.size(); ++i) {
			out.println("<th class=\"lrb small header\" left small header\">Rsz.</th>");
			List<NameWithId> innerCols = columns.get(i).second;
			for (int j=0; j<innerCols.size(); ++j) {
				String className = (j==0?"l":"") + (j==innerCols.size()-1?"r":"") + "b";
				out.println("<th colspan=\"2\" class=\""+className+" left small header\">"+innerCols.get(j).getName()+"</th>");
			}
		}
		out.println("</tr>");
		out.println("</thead>");
	}

	private void printTableRow(PrintWriter out, int index, ResultTable resultTable, ResultRow resultRow) {
		String tr = "<tr class=\"row\" style=\"height:9.0mm;\">";
		out.println("<tbody>");
		out.println(tr);
		String rowspan2 = isDoubleRow()?" rowspan=\"2\"":"";
		out.println("<td class=\"lrtb important numeric\""+rowspan2+">"+index+".</td>");
		out.println(resultRow.getNamedData().getFirstRowCells());
		List<Pair<NameWithId,List<NameWithId>>> columns = resultTable.getColumns();
		for (Pair<NameWithId,List<NameWithId>> block : columns) {
			Integer rajtszam = resultRow.getRajtszam(block.first.second);
			out.println("<td class=\"ltb numeric\""+rowspan2+">"+(rajtszam!=null ? rajtszam : "")+"</td>");
			for (NameWithId col : block.second) {
				ResultCell cell = resultRow.getCell(col.getId());
				if (cell==null) {
					out.println("<td class=\"ltb numeric\""+rowspan2+"></td>");
					out.println("<td class=\"rtb numeric\""+rowspan2+"></td>");
				} else {
					out.println("<td class=\"ltb numeric\""+rowspan2+">"+cell.getPlace()+".</td>");
					out.println("<td class=\"rtb numeric\""+rowspan2+">"+cell.getPoint()+"</td>");
				}
			}
		}
		out.println("<td class=\"lrtb numeric\""+rowspan2+">"+resultRow.getSumPoints()+"</td>");
		out.println("</tr>");
		if (isDoubleRow()) {
			out.println(tr);
			out.println(resultRow.getNamedData().getSecondRowCells());
			out.println("</tr>");
		}
		out.println("</tbody>");
	}

	protected abstract boolean isDoubleRow();

	protected abstract ResultTable createResultTable() throws SQLException, ConfigException;

	protected abstract NevezesMap createNevezesMap() throws ConfigException;

}
