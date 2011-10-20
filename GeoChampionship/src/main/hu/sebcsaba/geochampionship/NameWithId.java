package hu.sebcsaba.geochampionship;

public class NameWithId extends Pair<String,Long> {

	public NameWithId(String name, Long id) {
		super(name, id);
	}
	
	public final String getName() {
		return first;
	}
	
	public final long getId() {
		return second;
	}

}
