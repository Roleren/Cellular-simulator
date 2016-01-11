package MolecularEnvironments;

import java.util.ArrayList;

import Molecules.H2O;
import Molecules.Molecule;

public class Water extends Molecule {
	ArrayList<Molecule> molecules;
	
	public Water(){
		type = "molecular environment";
		name = "water";
		molecules = new ArrayList<Molecule>(100);
	}
	
	public ArrayList<Molecule> createWater(){
		System.out.println("hei");
		for(int i = 0; i<100;i++){
			molecules.add(new H2O());
		}
		return molecules;
	}
}
