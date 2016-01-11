package AtomInteractions;

import java.util.ArrayList;

import Atoms.atom;
import Math.Calculations;
import Runner.Simulator;

public class AtomActions {
	static int scale = atom.getScale(); 
	
	/**
	 * Primary starting point for atom behavior
	 * @param one
	 * @param simulator
	 */
	public static void Action(atom one, Simulator simulator){
		one.updateOldPositions();
		;
		
			if(!isMolecule(one)){
				
				atom that = null;
				double min = 100000000;
				//Checks each atom in this atoms cell list
	//			System.out.println(getxPos()+" "+getyPos()+" "+getzPos());
				//Create the list to of atoms to be checked
				
				
				for(atom a : atomsInsidePerimeter(one, simulator)){
					if(!one.getBoundAtoms().contains(a) && one.hasCollided(a)){
						one.setColition(true);
					
						if(one.canBind(a)){
							one.bind(a);
							System.out.println("bind");
						}
						else{
							
							if(Calculations.senterAvstandDouble(one, a)/
									((one.getVdwRadius()+a.getVdwRadius())/scale) < min &&
									Calculations.senterAvstandDouble(one, a) <
										((one.getVdwRadius()+a.getVdwRadius()))/scale){
								min = Calculations.senterAvstandDouble(one, a)/((one.getVdwRadius()+a.getVdwRadius())/scale);
								that = a;
								}
							}
					}
				}
				if(!one.isColition()){
	//				System.out.println("Random");
					Movements.moveRandomly(one);
				}
				else if(that != null){
					if(!one.getBoundAtoms().contains(that)
							&& one.isBound() && that.isBound()
							&& ElectronAffinityAction.compareElectronAffinity(one,that)){
						System.out.println("bind");
						one.bind(that);
					}
					
					else {
	//					System.out.println("Colition away");
						Movements.moveAway(one,that);
					}
				}
				
				else{
	//				System.out.println("With colition Random");
					Movements.moveRandomly(one);
				}
			}
	//		ElectronAffinityAction();
			one.checkBorder();
			one.setColition(false);
		}
	
	
	/**
	 * Behavior for atoms that are bound and the distance between two atoms
	 * in the bound atom is less or greater than min/max.
	 **/
	public static boolean isMolecule(atom one){
		
		if(one.isBound()){
			double rangeMin = 0;
			double rangeMax = 0;
			double max = 0;
			double min = 100000000;
			atom thatMin = null;
			atom thatMax = null;
			for(atom a : one.getBoundAtoms()){
				double currentDistance = Calculations.senterAvstandDouble(one, a);
				double covalentDistance = (one.getCovalentRadius()+a.getCovalentRadius())/scale;

				if(currentDistance/
						(covalentDistance) < min &&
						currentDistance <
							((one.getCovalentRadius()+a.getCovalentRadius())/1.2)/scale){
					//Check which ratio is least, of all.
					min = currentDistance/covalentDistance;
					thatMin = a;
					
				}
				else if(currentDistance/
						covalentDistance > max && 
						currentDistance > 
							((one.getCovalentRadius()+a.getCovalentRadius())*1.2)/scale ){
					//Check which ratio is highest, of all.
					max = currentDistance/covalentDistance;
					thatMax = a;
					
				}
			}
			if(thatMin != null || thatMax != null){
				if(thatMin != null){
//					System.out.println("min "+thatMin);
					rangeMin = (((one.getCovalentRadius()+thatMin.getCovalentRadius())/1.2)/scale )
							-Calculations.senterAvstandDouble(one, thatMin);
					if(thatMax != null){
						rangeMax = Calculations.senterAvstandDouble(one, thatMax) 
								- (((one.getCovalentRadius()+thatMax.getCovalentRadius())*1.2)/one.getScale() );
						if(rangeMax > rangeMin)
							Movements.moveTowards(one,thatMax);
						else
							Movements.moveAway(one,thatMin);
						
					}
					else
						Movements.moveAway(one,thatMin);
				}
				else{
//					System.out.println("max "+thatMax);
						Movements.moveTowards(one,thatMax);
				}
				
			}
			return true;
		}
		else 
			return false;
	}
	/**
	 * The lists the atoms need to check for interactions, 
	 * that is its own position list and +-1.
	 * @param one
	 * @param simulator
	 * @return
	 */
	public static ArrayList<atom> atomsInsidePerimeter(atom one, Simulator simulator){
		ArrayList<atom> atomsCloseEnough = new ArrayList<atom>();
		int d = simulator.getCellGrid().getD();
		int listIndex = (one.getxPos()+one.getyPos()+one.getzPos())/d;
		
		atomsCloseEnough = simulator.getCellGrid().getCellLists().
				get(listIndex);
		if((listIndex) < simulator.getCellGrid().getNumberOfcellLists()){
			atomsCloseEnough.addAll(simulator.getCellGrid().getCellLists().
					get(listIndex+1));
		}
		if(listIndex > 0){
			atomsCloseEnough.addAll(simulator.getCellGrid().getCellLists().
					get((listIndex)-1));
		}
		return atomsCloseEnough;
	}
}
