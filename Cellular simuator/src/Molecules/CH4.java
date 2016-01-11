package Molecules;

import Atoms.Carbon;
import Atoms.Hydrogen;

public class CH4 extends Molecule {

	
	public CH4() {
		type = "molecule";
		name = "CH4";
		
		Carbon carbon = new Carbon(rx,ry,rz);
		Hydrogen hydro1 = new Hydrogen(rx+11,ry+11,rz+11);
		Hydrogen hydro2 = new Hydrogen(rx-11,ry+11,rz-11);
		Hydrogen hydro3 = new Hydrogen(rx+11,ry-11,rz+11);
		Hydrogen hydro4 = new Hydrogen(rx-11,ry-11,rz-11);
		
		atoms.add(carbon);
		atoms.add(hydro1);
		atoms.add(hydro2);
		atoms.add(hydro3);
		atoms.add(hydro4);
		
		carbon.bind(hydro1);
		carbon.bind(hydro2);
		carbon.bind(hydro3);
		carbon.bind(hydro4);
		
		
	}

}
