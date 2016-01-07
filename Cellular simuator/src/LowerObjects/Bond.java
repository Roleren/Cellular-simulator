package LowerObjects;

import Atoms.atom;

public class Bond extends Electron {

	public Bond(boolean pair) {
		super(pair);

	}

	// single covalent bond formation
	public static void BindSingle(atom one, atom that) {
		one.setBound(true);
		that.setBound(true);

		one.setBindNumber(one.getBindNumber() + 1);
		that.setBindNumber(that.getBindNumber() + 1);

		one.getBoundAtoms().add(that);
		that.getBoundAtoms().add(one);

		one.setNumberOfValenceElectrons(one.getNumberOfValenceElectrons() + 1);
		that.setNumberOfValenceElectrons(that.getNumberOfValenceElectrons() + 1);

		int oldThatCharge = one.getCharge();
		one.setCharge(one.getCharge() + that.getCharge());
		that.setCharge(oldThatCharge + that.getCharge());
	}

	// single covalent bond formation
	public static void BindDouble(atom one, atom that) {
		one.setBound(true);
		that.setBound(true);

		one.setBindNumber(one.getBindNumber() + 2);
		that.setBindNumber(that.getBindNumber() + 2);

		one.getBoundAtoms().add(that);
		that.getBoundAtoms().add(one);

		one.setNumberOfValenceElectrons(one.getNumberOfValenceElectrons() + 2);
		that.setNumberOfValenceElectrons(that.getNumberOfValenceElectrons() + 2);
		// This must be fixed!
		int oldThatCharge = one.getCharge();
		one.setCharge(one.getCharge() + that.getCharge());
		that.setCharge(oldThatCharge + that.getCharge());
	}

	// single covalent bond removal
	public static void BindSingleRemove(atom one, atom that) {
		if(one.getBoundAtoms().size() == 1)
			one.setBound(false);
		if(that.getBoundAtoms().size() == 1)
			that.setBound(false);

		one.setBindNumber(one.getBindNumber() - 1);
		that.setBindNumber(that.getBindNumber() - 1);

		one.getBoundAtoms().remove(that);
		that.getBoundAtoms().remove(one);

		one.setNumberOfValenceElectrons(one.getNumberOfValenceElectrons() - 1);
		that.setNumberOfValenceElectrons(that.getNumberOfValenceElectrons() - 1);
		// This must be fixed!
		int oldThatCharge = one.getCharge();
		one.setCharge(one.getCharge() - that.getCharge());
		that.setCharge(that.getCharge() - oldThatCharge);
	}
	/*
	 * Checks if all rules needed to bind are true
	 */
	public static boolean canBind(atom one, atom that) {
		// if not already bound
		if (!one.getBoundAtoms().contains(that)) {
			// If it can bind one more
			if (one.getBindNumber() < one.getMaxBindNumber() && that.getBindNumber() < that.getMaxBindNumber()) {
				// If one of the binding rules apply
				if ((one.getCharge() + that.getCharge() == 0 || one.getCharge() + that.getCharge() > one.getCharge()
						|| one.getCharge() - that.getCharge() == 0)) {
					return true;
				}
			}
		}
		return false;
	}

}
