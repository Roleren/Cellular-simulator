package Molecules;

import java.util.ArrayList;
import java.util.Random;

import Atoms.atom;

public class Molecule extends atom {
	
	ArrayList<atom> atoms = new ArrayList<atom>();
	int numberOfAtoms;
	int totalCharge;
	int totalWeight;
	static Random random = new Random();
	
	//startPosition
	int rx = random.nextInt(350)+106;
	int ry = random.nextInt(150)+106;
	int rz = random.nextInt(350)+106;
	
	
	
	public void addAtom(atom that){
		atoms.add(that);
		numberOfAtoms++;
		totalCharge = totalCharge+that.getCharge();
		totalWeight = totalWeight + that.getWeight();
	}
	public void removeAtom(atom that,int index){
		atoms.remove(index);
		numberOfAtoms--;
		totalCharge = totalCharge - that.getCharge();
		totalWeight = totalWeight - that.getWeight();
	}
	public void setName(){
		
	}
	public ArrayList<atom> getAtomsOfMolecule(){
		return atoms;
	}
	
	
}
