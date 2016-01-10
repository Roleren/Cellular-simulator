package Math;

import java.util.ArrayList;

import Atoms.atom;
import Runner.Simulator;

/*
 * Class that creates the cell grid, 
 * to reduce the amount of atoms each atom must check for interactions.
 */
public class CellGrid {
	ArrayList<ArrayList<atom>> cellLists;
	int numberOfcellLists;
	
	//Divisor
	static int d = 50;
	
	public CellGrid(int x, int y, int z){
//		System.out.println(x+" "+y+" "+z);
		numberOfcellLists = (x+y+z)/d;
//		System.out.println(numberOfcellLists);
		cellLists = new ArrayList<ArrayList<atom>>(numberOfcellLists);
		for(int i = 0; i<numberOfcellLists; i++){
			cellLists.add(new ArrayList<atom>(30));
		}
	}
	
	public void updateCellGrid(Simulator simulator){
		for(atom a: simulator.getAtoms()){
			a.updateOldPositions();
			System.out.println("Simulator size: "+ simulator.getAtoms().size());
			System.out.println((a.getxPos()+a.getyPos()+a.getzPos())/d);
			System.out.println(a.getxPos()+" "+a.getyPos()+" "+a.getzPos());
//			cellLists.get((a.getLastXPos()+a.getLastYPos()+a.getLastZPos())/d).remove(a);
			if(!cellLists.get((a.getLastXPos()+a.getLastYPos()+a.getLastZPos())/d).remove(a)){
				System.out.println("Remove error");
				cellLists.get((a.getLastXPos()+a.getLastYPos()+a.getLastZPos())/d).remove(a);
				System.out.println("hello");
			}
			
			cellLists.get((a.getxPos()+a.getyPos()+a.getzPos())/d).add(a);
			System.out.println(!cellLists.get((a.getxPos()+a.getyPos()+a.getzPos())/d).contains(a));
			System.out.println("current cellList size:"+ cellLists.get((a.getxPos()+a.getyPos()+a.getzPos())/d).size());
			if(!cellLists.get((a.getxPos()+a.getyPos()+a.getzPos())/d).contains(a)){
				System.out.println("error");
				System.out.println((a.getxPos()+a.getyPos()+a.getzPos())/d);
				System.out.println(a.getxPos()+" "+a.getyPos()+" "+a.getzPos());
			}
		} 
	}
	
	public ArrayList<ArrayList<atom>> getCellLists() {
		return cellLists;
	}
	public int getNumberOfcellLists() {
		return numberOfcellLists;
	}
	/*
	 * Get divisor for cellGrid
	 */
	public int getD() {
		return d;
	}
	
}
