package PossibleReactions;

import java.util.Random;

import Atoms.atom;

public class Reactions {
	Random random = new Random();
	public void checkForRections(atom one, atom that){
		waterDissociation(one, that);
	}
	
	
	public void waterDissociation(atom one, atom that){
		boolean ThisIsAHydrogen = (one.getName().equals("hydrogen"));
		if(ThisIsAHydrogen){
			atom boundOxygen = one.getBoundAtoms().get(0);
			boolean haveBoundOxygen = boundOxygen.getName().equals("oxygen");
			if(haveBoundOxygen){
				boolean chanceHappened = (random.nextInt(5) == 0);
				if(chanceHappened){
					one.unBind(boundOxygen);
					one.bind(that);
				}
			}
		}
	}
}
