package Molecules;

import Atoms.Phosphorus;
import Atoms.atom;

public class Phosphate extends Molecule {

	public Phosphate() {
		name = "phosphate";
		rx = random.nextInt(400)+106;
		ry = random.nextInt(400)+106;
		rz = random.nextInt(400)+106;
		
		Phosphorus phosphorus1 = new Phosphorus(rx,ry,rz);
		Phosphorus phosphorus2 = new Phosphorus(rx+5,ry+5,rz+5);
		Phosphorus phosphorus3 = new Phosphorus(rx-5,ry+5,rz-5);
		Phosphorus phosphorus4 = new Phosphorus(rx+5,ry-5,rz+5);
		Phosphorus phosphorus5 = new Phosphorus(rx+7,ry-3,rz-5);
		
		atoms.add(phosphorus1);
		atoms.add(phosphorus2);
		atoms.add(phosphorus3);
		atoms.add(phosphorus4);
		atoms.add(phosphorus5);
		
		phosphorus1.bind(phosphorus2);
		phosphorus1.bind(phosphorus3);
		phosphorus1.bind(phosphorus4);
		phosphorus1.bind(phosphorus5);
		
	}

}
