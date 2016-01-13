package Molecules;

import Atoms.Hydrogen;
import Atoms.Oxygen;

public class H2O extends Molecule {

	public H2O() {
		type = "molecule";
		name = "H2O";

		Oxygen oxygen1 = new Oxygen(rx, ry, rz);
		Hydrogen hydro1 = new Hydrogen(rx + 11, ry + 11, rz + 11);
		Hydrogen hydro2 = new Hydrogen(rx - 11, ry + 11, rz - 11);

		atoms.add(oxygen1);
		atoms.add(hydro1);
		atoms.add(hydro2);

		oxygen1.bind(hydro1);
		oxygen1.bind(hydro2);

	}

}
