package Math;

import Atoms.atom;

/**
 * Van der walls calculations
 * 
 * @author Hakon Tjeldnes
 *
 */
public class VdwCalculations {
	static int scale = atom.getScale();
	
	/**
	 * Can atom a be the new min ?
	 */
	public static boolean distanceIsSmallEnough(atom one, atom a, double min) {
		if (currentDistanceToVdwRatio(one, a) < min && isCurrentDistanceLessWdv(one, a)) {
			return true;
		}
		return false;
	}

	public static double currentDistanceToVdwRatio(atom one, atom a) {
		return Calculations.senterAvstandDouble(one, a) / vanDerWallsRatio(one, a);
	}

	public static boolean isCurrentDistanceLessWdv(atom one, atom a) {
		System.out.println("distance: "+Calculations.senterAvstandDouble(one, a)+" vdw: "+vanDerWallsRatio(one, a));
		return Calculations.senterAvstandDouble(one, a) < vanDerWallsRatio(one, a);
	}
	private static double vanDerWallsRatio(atom one, atom a) {
		return (one.getVdwRadius() + a.getVdwRadius()) / scale;
	}
}
