package Molecules;

import Atoms.Carbon;
import Atoms.Hydrogen;
import Atoms.Oxygen;
import Atoms.atom;

public class H2O extends Molecule {

	
	public H2O() {
		name = "H2O";
		rx = random.nextInt(400)+106;
		ry = random.nextInt(400)+106;
		rz = random.nextInt(400)+106;
		Oxygen oxygen1 = new Oxygen(rx,ry,rz);
		Hydrogen hydro1 = new Hydrogen(rx+5,ry+5,rz+5);
		Hydrogen hydro2 = new Hydrogen(rx-5,ry+5,rz-5);
		
		
		atoms.add(oxygen1);
		atoms.add(hydro1);
		atoms.add(hydro2);
		
		
		oxygen1.bind(hydro1);
		oxygen1.bind(hydro2);
		
		
	}

}
