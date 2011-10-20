package hu.sebcsaba.geochampionship;

public class EtapMetaNamedData implements MetaNamedData {

	@Override
	public double getWidthPerCol() {
		return 32;
	}

	@Override
	public int getColspan() {
		return 2;
	}

	@Override
	public String getFirstRowCells() {
		return "<th class=\"lt bold header\">Sofőr</th><th class=\"rt bold header\">Navigátor</th>";
	}

	@Override
	public String getSecondRowCells() {
		return "<th colspan=\"2\" class=\"lrb bold header\">Utasok</th>";
	}

}
