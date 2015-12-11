package Runner;

import java.util.ArrayList;
import java.util.Random;

import Atoms.Carbon;
import Atoms.Hydrogen;
import Atoms.atom;
public class Simulator {
	ArrayList<atom> atom = new ArrayList<atom>();
	Random random = new Random();
	
	public Simulator(int xMax,int yMax, int xMin,int yMin){
		testStart( xMax,yMax, xMin, yMin);
		
	}
	public void nesteSteg(){
		objectExecutor();
	}
	public void objectExecutor(){
		for(atom currentAtom : atom){
			currentAtom.updateAtom(this);
		}
	}
	public ArrayList<Atoms.atom> getAtoms(){
		return atom;
	}
	public void restart(int xMax,int yMax, int xMin,int yMin){
		atom = new ArrayList<atom>();
		System.out.println(atom.size());
		testStart( xMax,yMax, xMin, yMin);
	}
	public void testStart(int xMax,int yMax, int xMin,int yMin){
		atom.add(new Carbon(random.nextInt(xMax-xMin)+xMin, random.nextInt(yMax-yMin)+ yMin));
		atom.add(new Carbon(random.nextInt(xMax-xMin)+xMin, random.nextInt(yMax-yMin)+ yMin));
		atom.add(new Hydrogen(random.nextInt(xMax-xMin)+xMin, random.nextInt(yMax-yMin)+ yMin));
		atom.add(new Hydrogen(random.nextInt(xMax-xMin)+xMin, random.nextInt(yMax-yMin)+ yMin));
		atom.add(new Hydrogen(random.nextInt(xMax-xMin)+xMin, random.nextInt(yMax-yMin)+ yMin));
		atom.add(new Hydrogen(random.nextInt(xMax-xMin)+xMin, random.nextInt(yMax-yMin)+ yMin));
		atom.add(new Hydrogen(random.nextInt(xMax-xMin)+xMin, random.nextInt(yMax-yMin)+ yMin));
		atom.add(new Hydrogen(random.nextInt(xMax-xMin)+xMin, random.nextInt(yMax-yMin)+ yMin));
		
	}
}
