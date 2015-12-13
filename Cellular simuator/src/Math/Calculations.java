package Math;

import Atoms.atom;

public class Calculations {
	int x, y,z;
	/**
	 * Euclidean distance
	 * @param one
	 * @param two
	 * @return
	 */
	public static int senterAvstand(atom one, atom two){
		return (int) Math.sqrt(Math.pow(one.getxPos()-two.getxPos(),2)
						+Math.pow(one.getyPos()-two.getyPos(),2)
								+Math.pow(one.getyPos()-two.getyPos(),2));
	}
	public static int xRetning(atom one, atom two){
		int xr = (one.getxPos()-two.getxPos());
		return xr;
	}
	public static int yRetning(atom one, atom two){
		int yr = (one.getyPos()-two.getyPos());
		return yr;
	}
	public static int zRetning(atom one, atom two){
		int yr = (one.getzPos()-two.getzPos());
		return yr;
	}
	public static int xRetningNormal(atom one, atom two){
		int dist = senterAvstand(one,two);
		int xr = (one.getxPos()-two.getxPos() /dist);
		return xr;
	}
	public static int yRetningNormal(atom one, atom two){
		int dist = senterAvstand(one,two);
		int yr = (one.getyPos()-two.getyPos()/dist);
		return yr;
	}
}
