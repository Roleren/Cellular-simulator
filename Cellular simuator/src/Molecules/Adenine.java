package Molecules;

import Atoms.Carbon;
import Atoms.Hydrogen;
import Atoms.Nitrogen;

public class Adenine extends Molecule {

	
	public Adenine() {
		type = "molecule";
		name = "Adenine";
		
		//Main rings
		Nitrogen nitrogen1 = new Nitrogen(rx,ry,rz);
		Carbon carbon2 = new Carbon(rx+5,ry+15,rz);
		Nitrogen nitrogen3 = new Nitrogen(rx-5,ry+30,rz);
		Carbon carbon4 = new Carbon(rx-20,ry+25,rz);
		Carbon carbon5 = new Carbon(rx-25,ry+5,rz);
		Carbon carbon6 = new Carbon(rx-12,ry-5,rz);
		Nitrogen nitrogen7 = new Nitrogen(rx-40,ry+5,rz);
		Carbon carbon8 = new Carbon(rx-45,ry+20,rz);
		Nitrogen nitrogen9 = new Nitrogen(rx-40,ry+35,rz);
		
		//Extensions
		Nitrogen nitrogenA = new Nitrogen(rx-25,ry-20,rz);
		//Nitrogen hydrogens
		Hydrogen hydroA = new Hydrogen(rx-40,ry-40,rz);
		Hydrogen hydroB = new Hydrogen(rx-15,ry-40,rz);
		
		Hydrogen hydroC = new Hydrogen(rx-40,ry+50,rz);
		//right/left
		Hydrogen hydroD = new Hydrogen(rx+20,ry+15,rz);
		Hydrogen hydroE = new Hydrogen(rx-60,ry+20,rz);
		
		atoms.add(carbon2);
		atoms.add(carbon4);
		atoms.add(carbon5);
		atoms.add(carbon6);
		atoms.add(carbon8);
		atoms.add(nitrogen1);
		atoms.add(nitrogen3);
		atoms.add(nitrogen7);
		atoms.add(nitrogen9);
		atoms.add(nitrogenA);
		atoms.add(hydroA);
		atoms.add(hydroB);
		atoms.add(hydroC);
		atoms.add(hydroD);
		atoms.add(hydroE);
		
		//Main rings
		nitrogen1.bind(carbon2);
		nitrogen1.bind(carbon6);
		nitrogen1.bind(carbon6);
		carbon2.bind(nitrogen3);
		carbon2.bind(nitrogen3);
		carbon2.bind(hydroD);
		nitrogen3.bind(carbon4);
		carbon4.bind(nitrogen9);
		carbon4.bind(carbon5);
		carbon4.bind(carbon5);
		carbon5.bind(carbon6);
		carbon5.bind(nitrogen7);
		carbon6.bind(nitrogenA);
		nitrogen7.bind(carbon8);
		nitrogen7.bind(carbon8);
		carbon8.bind(hydroE);
		carbon8.bind(nitrogen9);
		nitrogen9.bind(hydroC);
		
		//Extensions
		nitrogenA.bind(hydroA);
		nitrogenA.bind(hydroB);
	}
}
