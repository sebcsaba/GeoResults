package hu.sebcsaba.geochampionship;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class EtapNamedData implements NamedData {
	
	private final String sofor;
	private final String navigator;
	private final Set<String> utasok;

	public EtapNamedData(String sofor, String navigator, Collection<String> utasok) {
		this.sofor = sofor;
		this.navigator = navigator;
		this.utasok = new HashSet<String>(utasok);
	}

	@Override
	public String getFirstRowCells() {
		if (utasok.size()==0) {
			return "<td class=\"ltb text\" rowspan=\"2\">"+sofor+"</td><td class=\"rtb text\" rowspan=\"2\">"+navigator+"</td>";
		} else {
			return "<td class=\"lt text\">"+sofor+"</td><td class=\"rt text\">"+navigator+"</td>";
		}
	}

	@Override
	public String getSecondRowCells() {
		if (utasok.size()==0) return "";
		StringBuilder sb = new StringBuilder();
		sb.append("<td class=\"lrb text\" colspan=\"2\">");
		int i = 0;
		for (String utas : utasok) {
			if (i++>0) sb.append(", ");
			sb.append(utas);
		}
		sb.append("</td>");
		return sb.toString();
	}
	
	public Set<String> getAllNames() {
		Set<String> result = new HashSet<String>(utasok);
		result.add(sofor);
		result.add(navigator);
		return result;
	}
	
	public Set<String> getUtasok() {
		return utasok;
	}

}
