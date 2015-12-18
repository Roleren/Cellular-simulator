package Molecules;

import Atoms.Carbon;
import Atoms.Hydrogen;
import Atoms.Oxygen;
import Atoms.atom;

public class CO2 extends Molecule {

	
	public CO2() {
		name = "CO2";
		rx = random.nextInt(400)+106;
		ry = random.nextInt(400)+106;
		rz = random.nextInt(400)+106;
		
		
		Carbon carbon = new Carbon(rx,ry,rz);
		Oxygen oxygen1 = new Oxygen(rx+10,ry+10,rz+10);
		Oxygen oxygen2 = new Oxygen(rx-10,ry-10,rz-10);
		
		
		atoms.add(carbon);
		atoms.add(oxygen1);
		atoms.add(oxygen2);
		
		
		carbon.bind(oxygen1);
		carbon.bind(oxygen1);
		carbon.bind(oxygen2);
		carbon.bind(oxygen2);
		
	}

}
