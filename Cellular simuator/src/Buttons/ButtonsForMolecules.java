package Buttons;

import java.util.ArrayList;
import java.util.Random;

import Atoms.atom;
import Molecules.Adenine;
import Molecules.CH4;
import Molecules.CO2;
import Molecules.H2O;
import Molecules.Phosphate;
import Molecules.Ribose;
import Runner.Simulator;

public class ButtonsForMolecules {

	Random random = new Random();

	public static void createAtom(atom currentAtom, Simulator simulator) {
		String name = currentAtom.toString();

		if (name.equals("CH4")) {

			CH4 cH4 = new CH4();
			ArrayList<atom> atoms = cH4.getAtomsOfMolecule();
			for (atom a : atoms) {
				simulator.atom.add(a);
			}
		} else if (name.equals("phosphate")) {

			Phosphate phosphate = new Phosphate();
			ArrayList<atom> atoms = phosphate.getAtomsOfMolecule();
			for (atom a : atoms) {
				simulator.atom.add(a);
			}
		} else if (name.equals("ribose")) {

			Ribose ribose = new Ribose();
			ArrayList<atom> atoms = ribose.getAtomsOfMolecule();
			for (atom a : atoms) {
				simulator.atom.add(a);
			}
		} else if (name.equals("H2O")) {

			H2O h2o = new H2O();
			ArrayList<atom> atoms = h2o.getAtomsOfMolecule();
			for (atom a : atoms) {
				simulator.atom.add(a);
			}
		} else if (name.equals("CO2")) {

			CO2 co2 = new CO2();
			ArrayList<atom> atoms = co2.getAtomsOfMolecule();
			for (atom a : atoms) {
				simulator.atom.add(a);
			}
		} else if (name.equals("Adenine")) {

			Adenine adenine = new Adenine();
			ArrayList<atom> atoms = adenine.getAtomsOfMolecule();
			for (atom a : atoms) {
				simulator.atom.add(a);
			}
		}
	}
}
