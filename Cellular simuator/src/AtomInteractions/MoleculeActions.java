package AtomInteractions;

import Atoms.atom;
import Math.CovalentCalculations;
import Runner.Simulator;

public class MoleculeActions {
	static int scale = atom.getScale(); 
	/**
	 * Behavior for atoms that are bound and the distance between two atoms
	 * in the bound atom is less or greater than min/max.
	 **/
	public static boolean isMolecule(atom one, Simulator simulator){
		
		if(one.isBound()){
			
			double max = 0;
			double min = 100000000;
			atom thatMin = null;
			atom thatMax = null;
			for(atom a : one.getBoundAtoms()){
				//below bounds
				if(CovalentCalculations.distanceIsSmallEnough(one,a,min)){
					min = CovalentCalculations.currentDistanceToCovalentRatio(one,a);
					thatMin = a;	
				}
				//above bounds
				else if(CovalentCalculations.distanceIsBigEnough(one,a,max)){
					max = CovalentCalculations.currentDistanceToCovalentRatio(one,a);
					thatMax = a;
				}
			}
			MoveTowardOrAway(thatMin, thatMax, one, simulator);
			
			return true;
		}
		else 
			return false;
	}
	
	private static void MoveTowardOrAway(atom thatMin, atom thatMax, atom one, Simulator simulator){
		double rangeMin = 0;
		double rangeMax = 0;
		
		if(thatMin != null || thatMax != null){
			if(thatMin != null){
				
				rangeMin = CovalentCalculations.getMinimumDistance(one, thatMin);
				if(thatMax != null){
					rangeMax = CovalentCalculations.getMaximumDistance(one, thatMax);
					
					if(rangeMax > rangeMin)
						Movements.moveTowards(one,thatMax);
					else
						Movements.moveAway(one,thatMin);
				}//No max atom
				else
					Movements.moveAway(one,thatMin);
			}//No min atom
			else
				Movements.moveTowards(one,thatMax);	
		}
		else
			AtomActions.freeMovement(one, simulator);
		
	}
}
