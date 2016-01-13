package AtomInteractions;

import Atoms.atom;
import Math.CovalentCalculations;
import Runner.Simulator;

public class MoleculeActions {
	static int scale = atom.getScale();

	/**
	 * Behavior for atoms that are bound and the distance between two atoms in
	 * the bound atom is less or greater than min/max.
	 **/
	public static boolean isMolecule(atom one, Simulator simulator) {

		if (one.isBound()) {

			double max = 0;
			double min = 100000000;
			atom thatMin = null;
			atom thatMax = null;
			for (atom a : one.getBoundAtoms()) {
				// below bounds
				if (CovalentCalculations.distanceIsSmallEnough(one, a, min)) {
					min = CovalentCalculations.currentDistanceToCovalentRatio(one, a);
					thatMin = a;
				}
				// above bounds
				else if (CovalentCalculations.distanceIsBigEnough(one, a, max)) {
					max = CovalentCalculations.currentDistanceToCovalentRatio(one, a);
					thatMax = a;
				}
			}
			MoveTowardOrAway(thatMin, thatMax, one, simulator);

			return true;
		} else
			return false;
	}
	/**
	 * Check if min or max atom has biggest difference in distance from this atom
	 * Then moves away or towards, or random both are null.
	 */
	private static void MoveTowardOrAway(atom thatMin, atom thatMax, atom one, Simulator simulator) {

		boolean MinOrMaxAtomExists = (thatMin != null || thatMax != null);
		if (MinOrMaxAtomExists) {
			if (thatMin != null) {

				double rangeMin = CovalentCalculations.getMinimumDistance(one, thatMin);
				if (thatMax != null) {
					double rangeMax = CovalentCalculations.getMaximumDistance(one, thatMax);

					if (rangeMax > rangeMin)
						Movements.moveTowards(one, thatMax);
					else
						Movements.moveAway(one, thatMin);
				} // No max atom
				else
					Movements.moveAway(one, thatMin);
			} // No min atom
			else
				Movements.moveTowards(one, thatMax);
		} // noMinOrMaxAtomExists
		else
			AtomActions.freeMovement(one, simulator);

	}
}
