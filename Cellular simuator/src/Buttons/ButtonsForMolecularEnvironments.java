package Buttons;

import java.util.ArrayList;
import java.util.Random;

import Atoms.atom;
import MolecularEnvironments.Water;
import Molecules.Molecule;
import Runner.Simulator;

public class ButtonsForMolecularEnvironments {
	
	Random random = new Random();
	
	
	

	
	public static void createAtom(atom currentAtom, Simulator simulator){
		String name = currentAtom.toString();
		
		if(name.equals("water")){
			
			Water water = new Water();
			ArrayList<Molecule> molecules = water.createWater();
			for(Molecule a : molecules){
				for(atom b : a.getAtomsOfMolecule()){
					simulator.atom.add(b);
				}
			}
		}
	}
}
