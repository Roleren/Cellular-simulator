package Molecules;

import java.util.ArrayList;

import Atoms.atom;

public class molecule extends atom {
	
	ArrayList<atom> atoms;
	int numberOfAtoms;
	int totalCharge;
	int totalWeight;
	
	public molecule(atom one, atom two){
		numberOfAtoms = 2;
		totalCharge = one.getCharge() + two.getCharge();
		
	}
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
	
}
