package AtomInteractions;

import java.util.Random;

import Atoms.atom;

public class ElectronAffinityAction {
	static Random random = new Random();

	public static boolean compareElectronAffinity(atom one, atom that) {
		if (atomIsHydrogenThatCanAffinityBind(one, that)) {
			return true;
		}
		return false;
	}

	public static boolean canAffinityBind(atom one, atom that) {
		if (!one.getBoundAtoms().contains(that) && one.isBound() && that.isBound()
				&& ElectronAffinityAction.compareElectronAffinity(one, that)) {
			return true;
		}
		return false;
	}

	private static boolean atomIsHydrogenThatCanAffinityBind(atom one, atom that) {
		if (one.getElectronAffinity() - that.getElectronAffinity() > 3) {
			if (one.getName().equals("hydrogen")) {
				if (one.getAffinityElectrons().size() == 0 && that.getAffinityElectrons().size() < 2) {
					one.getAffinityElectrons().add(that);
					that.getAffinityElectrons().add(one);
					return true;
				}
			}
		}
		return false;
	}

	public static void RandomUnbonding(atom one) {
		if (!one.getAffinityElectrons().isEmpty()) {
			atom currentAtom = null;
			for (atom a : one.getAffinityElectrons()) {
				if (random.nextInt(5) == 4) {
					currentAtom = a;
				}
			}
			if (currentAtom != null) {
				one.getAffinityElectrons().remove(currentAtom);
				currentAtom.getAffinityElectrons().remove(one);
				one.unBind(currentAtom);
			}
		}
	}

}
