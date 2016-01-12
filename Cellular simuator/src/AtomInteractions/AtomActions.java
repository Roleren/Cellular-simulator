package AtomInteractions;

import java.util.ArrayList;

import Atoms.atom;
import Math.VdwCalculations;
import Runner.Simulator;

public class AtomActions {
	static int scale = atom.getScale(); 
	
	/**
	 * Primary starting point for atom behavior
	 * @param one
	 * @param simulator
	 */
	public static void Action(atom one, Simulator simulator){
		preActions(one);
		
		boolean atomIsNotMolecule = !MoleculeActions.isMolecule(one, simulator);
		if(atomIsNotMolecule){
				freeMovement(one,simulator);
		}
		endActions(one);
	}
	
	
	
	
	private static atom atomCloseEnoughToReact(atom one, Simulator simulator){
		atom that = null;
		double min = 100000000;
		
		for(atom a : atomsInsidePerimeter(one, simulator)){
			if(!one.getBoundAtoms().contains(a) && one.hasCollided(a)){
				one.setColition(true);
			
				if(one.canBind(a)){
					one.bind(a);
					System.out.println("bind");
				}
				else{
					
					if(VdwCalculations.distanceIsSmallEnough(one, a,min)){
						min = VdwCalculations.currentDistanceToVdwRatio(one,a);
						that = a;
						}
					}
			}
		}
		if(that == null) //No atom found
			return null;
		else			 //Atom found
			return that;
	}
	/**
	 * The lists the atoms need to check for interactions, 
	 * that is its own position list and +-1.
	 * @param one
	 * @param simulator
	 * @return
	 */
	private static ArrayList<atom> atomsInsidePerimeter(atom one, Simulator simulator){
		ArrayList<atom> atomsCloseEnough = new ArrayList<atom>();
		int d = simulator.getCellGrid().getD();
		int listIndex = (one.getxPos()+one.getyPos()+one.getzPos())/d;
		
		atomsCloseEnough = simulator.getCellGrid().getCellLists().
				get(listIndex);
//		if((listIndex) < simulator.getCellGrid().getNumberOfcellLists()){
//			atomsCloseEnough.addAll(simulator.getCellGrid().getCellLists().
//					get(listIndex+1));
//		}
//		if(listIndex > 0){
//			atomsCloseEnough.addAll(simulator.getCellGrid().getCellLists().
//					get((listIndex)-1));
//		}
		return atomsCloseEnough;
	}
	public static void preActions(atom one){
		one.updateOldPositions();
	}
	
	public static void freeMovement(atom one,Simulator simulator){
		atom that = atomCloseEnoughToReact(one, simulator);
		boolean closeAtomsExist = (that != null);

		if(closeAtomsExist){
			if(ElectronAffinityAction.canAffinityBind(one, that))
				one.bind(that);
			else 
				Movements.moveAway(one,that);
		}
		else
			Movements.moveRandomly(one);
	}
	
	public static void endActions(atom one){
//		ElectronAffinityAction();
		one.checkBorder();
		one.setColition(false);
	}
}
