package Math;

import Atoms.atom;
/**
 * Van der walls calculations
 * @author Hakon Tjeldnes
 *
 */
public class VdwCalculations {
	static int scale = atom.getScale(); 
	
	
	public static boolean distanceIsSmallEnough(atom one, atom a, double min){
		if(currentDistanceToVdwRatio(one, a) < min &&
				isCurrentDistanceLessWdv(one, a)){
			return true;
		}
		return false;
	}
	
	public static int vanDerWallsRatio(atom one, atom a){
		return (one.getVdwRadius()+a.getVdwRadius())/scale;
	}
	public static double currentDistanceToVdwRatio(atom one, atom a){
		return Calculations.senterAvstandDouble(one, a)/
				vanDerWallsRatio(one, a);
	}
	public static boolean isCurrentDistanceLessWdv(atom one, atom a){
		return Calculations.senterAvstandDouble(one, a) <
				vanDerWallsRatio(one,a);
	}
}
