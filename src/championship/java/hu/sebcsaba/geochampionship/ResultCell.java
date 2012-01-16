package hu.sebcsaba.geochampionship;

public class ResultCell {
	
	private final int place;
	private final int point;
	
	public ResultCell(int place, int point) {
		this.place = place;
		this.point = point;
	}

	public int getPlace() {
		return place;
	}

	public int getPoint() {
		return point;
	}

}
