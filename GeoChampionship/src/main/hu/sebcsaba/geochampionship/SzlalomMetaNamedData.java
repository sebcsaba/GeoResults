package hu.sebcsaba.geochampionship;

public class SzlalomMetaNamedData implements MetaNamedData {

	@Override
	public double getWidthPerCol() {
		return 64.5;
	}

	@Override
	public int getColspan() {
		return 1;
	}

	@Override
	public String getFirstRowCells() {
		return "<th rowspan=\"2\" class=\"lt bold header\">Sofőr</th>";
	}

	@Override
	public String getSecondRowCells() {
		return "";
	}

}
