package Runner;

import java.util.ArrayList;
import java.util.Random;

import Atoms.Carbon;
import Atoms.Hydrogen;
import Atoms.atom;
public class Simulator {
	ArrayList<atom> atom = new ArrayList<atom>();
	Random random = new Random();
	
	public Simulator(int xMax,int yMax){
		atom.add(new Carbon(random.nextInt(xMax), random.nextInt(yMax)));
		atom.add(new Carbon(random.nextInt(xMax), random.nextInt(yMax)));
		atom.add(new Hydrogen(random.nextInt(xMax), random.nextInt(yMax)));
		atom.add(new Hydrogen(random.nextInt(xMax), random.nextInt(yMax)));
		atom.add(new Hydrogen(random.nextInt(xMax), random.nextInt(yMax)));
		atom.add(new Hydrogen(random.nextInt(xMax), random.nextInt(yMax)));
		atom.add(new Hydrogen(random.nextInt(xMax), random.nextInt(yMax)));
		atom.add(new Hydrogen(random.nextInt(xMax), random.nextInt(yMax)));
		
	}
	public void nesteSteg(){
		objectExecutor();
	}
	public void objectExecutor(){
		for(atom currentAtom : atom){
			currentAtom.updateAtom();
		}
	}
	public ArrayList<Atoms.atom> getAtoms(){
		return atom;
	}
}
