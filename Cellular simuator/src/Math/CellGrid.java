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

	// Divisor
	static int d = 50;

	public CellGrid(int x, int y, int z) {
		numberOfcellLists = (x + y + z) / d;
		cellLists = new ArrayList<ArrayList<atom>>(numberOfcellLists);
		for (int i = 0; i < numberOfcellLists; i++) {
			cellLists.add(new ArrayList<atom>(30));
		}
	}

	/**
	 * Primary method for CellGrid Removes and adds the atom in the new
	 * listPosition
	 * 
	 * @param simulator
	 */
	public void updateCellGrid(Simulator simulator) {
		for (atom a : simulator.getAtoms()) {
			removeAtomFromOldPosition(a);
			addAtomInNewPosition(a);
		}
	}

	private void removeAtomFromOldPosition(atom a) {

		boolean cantRemove = !cellLists.get((a.getLastXPos() + a.getLastYPos() + a.getLastZPos()) / d).remove(a);
		if (cantRemove) {
			System.out.println("Remove error");
			cellLists.get((a.getxPos() + a.getyPos() + a.getzPos()) / d).remove(a);
			System.out.println("hello");
		}
	}

	private void addAtomInNewPosition(atom a) {
		cellLists.get((a.getxPos() + a.getyPos() + a.getzPos()) / d).add(a);
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
