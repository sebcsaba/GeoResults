package hu.sebcsaba.geochampionship;

public class PointList {
	
	private final int[] points;

	public PointList(String pointListStr) {
		String[] p = pointListStr.split(",");
		points = new int[p.length];
		for (int i=0; i<p.length; ++i) {
			points[i] = Integer.parseInt(p[i]);
		}
	}
	
	public int get(int place) {
		if (place<=points.length) return points[place-1];
		return 0;
	}
	
}
