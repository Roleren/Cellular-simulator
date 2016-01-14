package Math;

import Atoms.atom;

public class CovalentCalculations {
	static int scale = atom.getScale();

	public static boolean distanceIsSmallEnough(atom one, atom a, double min) {
		if (currentDistanceToCovalentRatio(one, a) < min && isCurrentDistanceLessCovalent(one, a)) {
			return true;
		}
		return false;
	}

	public static boolean distanceIsBigEnough(atom one, atom a, double max) {
		if (currentDistanceToCovalentRatio(one, a) > max && isCurrentDistanceBigCovalent(one, a)) {
			return true;
		}
		return false;
	}

	public static double getMinimumDistance(atom one, atom thatMin) {
		return (getMinimumCovalentDistance(one, thatMin)) - Calculations.senterAvstandDouble(one, thatMin);
	}

	public static double getMaximumDistance(atom one, atom thatMax) {
		return Calculations.senterAvstandDouble(one, thatMax) - getMaximumCovalentDistance(one, thatMax);
	}

	public static double getMinimumCovalentDistance(atom one, atom thatMin) {
		return ((one.getCovalentRadius() + thatMin.getCovalentRadius()) / 1.2) / scale;
	}

	public static double getMaximumCovalentDistance(atom one, atom thatMax) {
		System.out.println(((one.getCovalentRadius() + thatMax.getCovalentRadius()) * 1.2) / scale);
		return ((one.getCovalentRadius() + thatMax.getCovalentRadius()) * 1.2) / scale;
	}

	public static double currentDistanceToCovalentRatio(atom one, atom a) {
		return Calculations.senterAvstandDouble(one, a) / getCovalentRatio(one, a);
	}

	public static double getCovalentRatio(atom one, atom a) {
		return (one.getCovalentRadius() + a.getCovalentRadius()) / scale;
	}

	public static boolean isCurrentDistanceLessCovalent(atom one, atom a) {
		return Calculations.senterAvstandDouble(one, a) < getMinimumCovalentDistance(one, a);
	}

	public static boolean isCurrentDistanceBigCovalent(atom one, atom a) {
		return Calculations.senterAvstandDouble(one, a) > getMaximumCovalentDistance(one, a);
	}
}
