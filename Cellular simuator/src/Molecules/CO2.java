package Molecules;

import Atoms.Carbon;
import Atoms.Oxygen;

public class CO2 extends Molecule {

	
	public CO2() {
		type = "molecule";
		name = "CO2";

		Carbon carbon = new Carbon(rx,ry,rz);
		Oxygen oxygen1 = new Oxygen(rx+11,ry+11,rz+11);
		Oxygen oxygen2 = new Oxygen(rx-11,ry-11,rz-11);
		
		
		atoms.add(carbon);
		atoms.add(oxygen1);
		atoms.add(oxygen2);
		
		
		carbon.bind(oxygen1);
		carbon.bind(oxygen2);
		
		carbon.setBindNumber(4);
		oxygen1.setBindNumber(2);
		oxygen2.setBindNumber(2);
		
	}

}
