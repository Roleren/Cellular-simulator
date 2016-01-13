package Molecules;

import Atoms.Phosphorus;

public class Phosphate extends Molecule {

	public Phosphate() {
		type = "molecule";
		name = "phosphate";

		Phosphorus phosphorus1 = new Phosphorus(rx, ry, rz);
		Phosphorus phosphorus2 = new Phosphorus(rx + 17, ry + 17, rz + 17);
		Phosphorus phosphorus3 = new Phosphorus(rx - 17, ry + 17, rz - 17);
		Phosphorus phosphorus4 = new Phosphorus(rx + 17, ry - 17, rz + 17);
		Phosphorus phosphorus5 = new Phosphorus(rx + 17, ry - 17, rz - 17);

		atoms.add(phosphorus1);
		atoms.add(phosphorus2);
		atoms.add(phosphorus3);
		atoms.add(phosphorus4);
		atoms.add(phosphorus5);

		phosphorus1.bind(phosphorus2);
		phosphorus1.bind(phosphorus3);
		phosphorus1.bind(phosphorus4);
		phosphorus1.bind(phosphorus5);

		phosphorus2.setBindNumber(5);
		phosphorus3.setBindNumber(5);
		phosphorus4.setBindNumber(5);
		phosphorus5.setBindNumber(5);

	}

}
