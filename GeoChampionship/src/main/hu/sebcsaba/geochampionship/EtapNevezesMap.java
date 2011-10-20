package hu.sebcsaba.geochampionship;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EtapNevezesMap extends NevezesMap {

	public EtapNevezesMap(String config_nevezesek) throws ConfigException {
		super(config_nevezesek);
	}

	@Override
	protected NamedData createNamedData(Nevezes n) {
		List<String> utasok = new ArrayList<String>(3);
		if (n.utas1!=null) utasok.add(n.utas1);
		if (n.utas2!=null) utasok.add(n.utas2);
		if (n.utas3!=null) utasok.add(n.utas3);
		return new EtapNamedData(n.sofor,n.navigator,utasok);
	}

	@Override
	protected void appendNevezes(NamedData namedData, Nevezes n) {
		EtapNamedData end = (EtapNamedData) namedData;
		Set<String> newNames = getAllNames(n);
		Set<String> oldNames = end.getAllNames();
		for (String newName : newNames) if (!oldNames.contains(newName)) end.getUtasok().add(newName);
	}

	@Override
	protected Long findMatchingNevezes(Nevezes n) {
		Set<String> newNames = getAllNames(n);
		for (Map.Entry<Long, NamedData> entry : nevezesek.entrySet()) {
			EtapNamedData ed = (EtapNamedData) entry.getValue();
			Set<String> oldNames = ed.getAllNames();
			int i = intersectionSize(oldNames, newNames);
			if (i*2 >= oldNames.size()) return entry.getKey();
			if (i*2 >= newNames.size()) return entry.getKey();
		}
		return null;
	}
	
	private Set<String> getAllNames(Nevezes n) {
		Set<String> result = new HashSet<String>();
		result.add(n.sofor);
		result.add(n.navigator);
		if (n.utas1!=null) result.add(n.utas1);
		if (n.utas2!=null) result.add(n.utas2);
		if (n.utas3!=null) result.add(n.utas3);
		return result;
	}
	
	private int intersectionSize(Set<String> s1, Set<String> s2) {
		int acc = 0;
		for (String a : s1) {
			if (s2.contains(a)) ++acc;
		}
		return acc;
	}

}
