package Buttons;

import java.util.ArrayList;

import Atoms.Carbon;
import Atoms.Chlorine;
import Atoms.Hydrogen;
import Atoms.Nitrogen;
import Atoms.Oxygen;
import Atoms.Phosphorus;
import Atoms.Potassium;
import Atoms.Sodium;
import Atoms.atom;
import MolecularEnvironments.Water;
import Molecules.Adenine;
import Molecules.CH4;
import Molecules.CO2;
import Molecules.H2O;
import Molecules.Phosphate;
import Molecules.Ribose;

public class PossibleObjects {

	public static ArrayList<atom> createListOfObjects() {
		ArrayList<atom> possibleObjects = new ArrayList<atom>();
		// Atoms
		possibleObjects = new ArrayList<atom>();
		possibleObjects.add(new Carbon(0, 0, 0));
		possibleObjects.add(new Hydrogen(0, 0, 0));
		possibleObjects.add(new Oxygen(0, 0, 0));
		possibleObjects.add(new Phosphorus(0, 0, 0));
		possibleObjects.add(new Nitrogen(0, 0, 0));
		possibleObjects.add(new Sodium(0, 0, 0));
		possibleObjects.add(new Chlorine(0, 0, 0));
		possibleObjects.add(new Potassium(0, 0, 0));
		// Molecules
		possibleObjects.add(new H2O());
		possibleObjects.add(new CO2());
		possibleObjects.add(new CH4());
		possibleObjects.add(new Phosphate());
		possibleObjects.add(new Ribose());
		possibleObjects.add(new Adenine());
		// Environments
		possibleObjects.add(new Water());

		return possibleObjects;
	}
}
