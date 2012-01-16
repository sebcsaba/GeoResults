package hu.sebcsaba.geochampionship;

import java.util.Map;

public class SzlalomNevezesMap extends NevezesMap {

	public SzlalomNevezesMap(String config_nevezesek) throws ConfigException {
		super(config_nevezesek);
	}

	@Override
	protected NamedData createNamedData(Nevezes n) {
		return new SzlalomNamedData(n.sofor);
	}

	@Override
	protected void appendNevezes(NamedData namedData, Nevezes n) {
	}

	@Override
	protected Long findMatchingNevezes(Nevezes n) {
		for (Map.Entry<Long, NamedData> entry : nevezesek.entrySet()) {
			SzlalomNamedData nd = (SzlalomNamedData) entry.getValue();
			if (nd.getSofor().equals(n.sofor)) return entry.getKey();
		}
		return null;
	}
	
}
