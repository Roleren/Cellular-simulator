package Math;

import Atoms.atom;

public class Calculations {
	int x, y;
	
	public int senterAvstand(atom one, atom two){
		return (int) Math.sqrt(Math.pow(one.getxPos()-two.getxPos(),2)+Math.pow(one.getyPos()-two.getyPos(),2));
	}
}
