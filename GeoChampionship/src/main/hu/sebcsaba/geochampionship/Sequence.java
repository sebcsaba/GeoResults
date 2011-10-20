package hu.sebcsaba.geochampionship;

public class Sequence {

	private long data = 0;
	
	public synchronized long getNext() {
		return ++data;
	}
	
	public synchronized long getActual() {
		return data;
	}
	
}
