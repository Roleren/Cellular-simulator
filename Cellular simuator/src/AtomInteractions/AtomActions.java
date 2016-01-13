package AtomInteractions;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import Atoms.atom;
import Math.VdwCalculations;
import Runner.Simulator;

public class AtomActions {
	static int scale = atom.getScale();

	/**
	 * Primary starting point for atom behavior
	 * 
	 * @param one
	 * @param simulator
	 */
	public static void Action(atom one, Simulator simulator) {
		preActions(one);

		boolean atomIsNotAMolecule = !MoleculeActions.isMolecule(one, simulator);
		if (atomIsNotAMolecule) { // Free atom
			freeMovement(one, simulator); // Move freely
		}
		endActions(one);
	}

	public static void freeMovement(atom one, Simulator simulator) {
		atom that = atomCloseEnoughToReact(one, simulator);
		boolean ToocloseAtomExist = (that != null);
		if (ToocloseAtomExist) {
			// if(ElectronAffinityAction.canAffinityBind(one, that))
			// one.bind(that);
			// else
			Movements.moveAway(one, that);
		} else
			Movements.moveRandomly(one);
	}

	private static atom atomCloseEnoughToReact(atom one, Simulator simulator) {
		atom that = null;
		double min = 100000000;

		for (atom a : allAtoms(simulator)) {
			boolean atomColidedWithUnconnectedOther = (!one.getBoundAtoms().contains(a) && one.hasCollided(a));
//			System.out.println(atomColidedWithUnconnectedOther+" atom one: "+ one + " atom a: "+ a);
			if (atomColidedWithUnconnectedOther) {
				one.setColition(true);

				if (one.canBind(a)) {
					one.bind(a);
					System.out.println("bind");
				} else { // can't bind, this is wrong!
					if (VdwCalculations.distanceIsSmallEnough(one, a, min)) { //can atom a be the new min ?
						min = VdwCalculations.currentDistanceToVdwRatio(one, a);
						that = a;
					}
				}
			}
		}
		if (that == null) // No atom found
			return null;
		else // Atom found
			return that;
	}

	/**
	 * The lists the atoms need to check for interactions, that is its own
	 * position list and +-1.
	 * 
	 * @param one
	 * @param simulator
	 * @return
	 */
	private static ArrayList<atom> atomsInsidePerimeter(atom one, Simulator simulator) {
		ArrayList<atom> atomsCloseEnough = new ArrayList<atom>();
		int d = simulator.getCellGrid().getD();
		int listIndex = (one.getxPos() + one.getyPos() + one.getzPos()) / d;

		atomsCloseEnough = simulator.getCellGrid().getCellLists().get(listIndex);
		// if((listIndex) < simulator.getCellGrid().getNumberOfcellLists()){
		// atomsCloseEnough.addAll(simulator.getCellGrid().getCellLists().
		// get(listIndex+1));
		// }
		// if(listIndex > 0){
		// atomsCloseEnough.addAll(simulator.getCellGrid().getCellLists().
		// get((listIndex)-1));
		// }
		return atomsCloseEnough;
	}

	private static CopyOnWriteArrayList<atom> allAtoms(Simulator simulator) {
		return simulator.getAtoms();
	}

	public static void preActions(atom one) {
		one.updateOldPositions();
	}

	public static void endActions(atom one) {
		// ElectronAffinityAction();
		Movements.isAtomOutsideBounds(one);
		one.setColition(false);
	}
}
