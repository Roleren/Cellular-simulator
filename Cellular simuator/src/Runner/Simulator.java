package Runner;

import java.util.ArrayList;
import java.util.Random;

import Atoms.Carbon;
import Atoms.Hydrogen;
import Atoms.Nitrogen;
import Atoms.Oxygen;
import Atoms.atom;
public class Simulator {
	ArrayList<atom> atom = new ArrayList<atom>();
	Random random = new Random();
	public Simulator(){}
	
	public Simulator(int xMax,int yMax,int zMax,int xMin,int yMin,int zMin ){
		testStart(xMax,yMax,zMax, xMin,yMin, zMin);
		
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
	public void restart(int xMax,int yMax,int zMax, int xMin,int yMin,int zMin ){
		atom = new ArrayList<atom>();
		System.out.println(atom.size());
		testStart(xMax,yMax,zMax, xMin,yMin, zMin);
	}
	public void testStart(int xMax,int yMax,int zMax, int xMin,int yMin,int zMin){
		atom.add(new Carbon(random.nextInt(xMax-xMin)+xMin, random.nextInt(yMax-yMin)+ yMin, random.nextInt(zMax-zMin)+zMin));
		atom.add(new Carbon(random.nextInt(xMax-xMin)+xMin, random.nextInt(yMax-yMin)+ yMin, random.nextInt(zMax-zMin)+zMin));
		atom.add(new Hydrogen(random.nextInt(xMax-xMin)+xMin, random.nextInt(yMax-yMin)+ yMin, random.nextInt(zMax-zMin)+zMin));
		atom.add(new Hydrogen(random.nextInt(xMax-xMin)+xMin, random.nextInt(yMax-yMin)+ yMin, random.nextInt(zMax-zMin)+zMin));
		atom.add(new Hydrogen(random.nextInt(xMax-xMin)+xMin, random.nextInt(yMax-yMin)+ yMin, random.nextInt(zMax-zMin)+zMin));
		atom.add(new Hydrogen(random.nextInt(xMax-xMin)+xMin, random.nextInt(yMax-yMin)+ yMin, random.nextInt(zMax-zMin)+zMin));
		atom.add(new Hydrogen(random.nextInt(xMax-xMin)+xMin, random.nextInt(yMax-yMin)+ yMin, random.nextInt(zMax-zMin)+zMin));
//		atom.add(new Nitrogen(300,300,300));
//		atom.add(new Oxygen(310,310,310));
		
	}
}
