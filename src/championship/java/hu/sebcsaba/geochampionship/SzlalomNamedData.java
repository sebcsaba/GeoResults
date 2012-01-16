package hu.sebcsaba.geochampionship;

public class SzlalomNamedData implements NamedData {
	
	private final String sofor;

	public SzlalomNamedData(String sofor) {
		this.sofor = sofor;
	}

	@Override
	public String getFirstRowCells() {
		return "<td class=\"lrtb text\">"+sofor+"</td>";
	}

	@Override
	public String getSecondRowCells() {
		return "";
	}

	public String getSofor() {
		return sofor;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName()+"["+sofor+"]";
	}

}
