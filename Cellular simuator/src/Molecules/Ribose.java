package Molecules;

import Atoms.Carbon;
import Atoms.Hydrogen;
import Atoms.Oxygen;

public class Ribose extends Molecule {

	public Ribose() {
		type = "molecule";
		name = "ribose";

		Carbon carbon1 = new Carbon(rx, ry, rz);
		Carbon carbon2 = new Carbon(rx - 15, ry, rz - 15);
		Carbon carbon3 = new Carbon(rx - 30, ry, rz - 30);
		Carbon carbon4 = new Carbon(rx - 45, ry, rz);
		Carbon carbon5 = new Carbon(rx - 45, ry - 15, rz);

		Hydrogen hydro1 = new Hydrogen(rx, ry + 12, rz);
		Hydrogen hydro2 = new Hydrogen(rx, ry - 24, rz);
		Hydrogen hydro3 = new Hydrogen(rx - 24, ry - 24, rz - 24);
		Hydrogen hydro4 = new Hydrogen(rx - 24, ry + 24, rz - 24);
		Hydrogen hydro5 = new Hydrogen(rx - 36, ry - 12, rz - 24);
		Hydrogen hydro6 = new Hydrogen(rx - 48, ry + 24, rz - 24);
		Hydrogen hydro7 = new Hydrogen(rx - 72, ry + 12, rz);
		Hydrogen hydro8 = new Hydrogen(rx - 60, ry - 24, rz - 12);
		Hydrogen hydro9 = new Hydrogen(rx - 60, ry - 24, rz + 12);
		Hydrogen hydro10 = new Hydrogen(rx - 96, ry - 12, rz - 12);

		Oxygen oxygen1 = new Oxygen(rx, ry - 15, rz);
		Oxygen oxygen2 = new Oxygen(rx - 45, ry - 15, rz + 15);
		Oxygen oxygen3 = new Oxygen(rx - 30, ry + 15, rz - 30);
		Oxygen oxygen4 = new Oxygen(rx - 60, ry + 15, rz - 15);
		Oxygen oxygen5 = new Oxygen(rx - 105, ry - 15, rz);

		atoms.add(carbon1);
		atoms.add(carbon2);
		atoms.add(carbon3);
		atoms.add(carbon4);
		atoms.add(carbon5);
		atoms.add(hydro1);
		atoms.add(hydro2);
		atoms.add(hydro3);
		atoms.add(hydro4);
		atoms.add(hydro5);
		atoms.add(hydro6);
		atoms.add(hydro7);
		atoms.add(hydro8);
		atoms.add(hydro9);
		atoms.add(hydro10);
		atoms.add(oxygen1);
		atoms.add(oxygen2);
		atoms.add(oxygen3);
		atoms.add(oxygen4);
		atoms.add(oxygen5);
		// Carbon 1
		carbon1.bind(hydro1);
		carbon1.bind(oxygen1);
		carbon1.bind(oxygen2);
		carbon1.bind(carbon2);
		oxygen1.bind(hydro2);
		// Carbon 2
		carbon2.bind(hydro3);
		carbon2.bind(oxygen3);
		carbon2.bind(carbon3);
		oxygen3.bind(hydro4);
		// Carbon 3
		carbon3.bind(hydro5);
		carbon3.bind(oxygen4);
		carbon3.bind(carbon4);
		oxygen4.bind(hydro6);
		// Carbon 4
		carbon4.bind(hydro7);
		carbon4.bind(oxygen2);
		carbon4.bind(carbon5);
		// Carbon 4
		carbon5.bind(hydro8);
		carbon5.bind(hydro9);
		carbon5.bind(oxygen5);
		oxygen5.bind(hydro10);

	}

}
