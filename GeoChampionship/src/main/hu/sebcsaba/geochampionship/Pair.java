package hu.sebcsaba.geochampionship;

public class Pair<P,Q> {

	public final P first;
	public final Q second;
	
	public Pair(P first, Q second) {
		this.first = first;
		this.second = second;
	}
	
	public P getFirst() {
		return first;
	}

	public Q getSecond() {
		return second;
	}

	@Override
	public int hashCode() {
		return (first==null?0:first.hashCode()) + (second==null?0:second.hashCode());
	}
	
	@Override
	public boolean equals(Object o) {
		if (o==this) return true;
		if (!(o instanceof Pair)) return false;
		Pair<?,?> that = (Pair<?,?>) o;
		return eq(this.first, that.first) && eq(this.second, that.second);
	}
	
	@Override
	public String toString() {
		return "("+first+','+second+')';
	}
	
	private static boolean eq(Object a, Object b) {
		if (a==null) return b==null;
		return a.equals(b);
	}
}
