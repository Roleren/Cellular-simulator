package Buttons;

import java.util.Random;

import Atoms.Carbon;
import Atoms.Chlorine;
import Atoms.Hydrogen;
import Atoms.Nitrogen;
import Atoms.Oxygen;
import Atoms.Phosphorus;
import Atoms.Potassium;
import Atoms.Sodium;
import Atoms.atom;
import Runner.Simulator;

public class ButtonsForAtoms {
	
	static Random random = new Random();
	
	public static void createAtom(atom currentAtom, Simulator simulator){
		
		String name = currentAtom.toString();
		if(name.equals("hydrogen")){
			
			Hydrogen hydrogen = new Hydrogen
					(random.nextInt(350)+106,
							random.nextInt(150)+ 106,
								random.nextInt(350)+106);
			simulator.atom.add(hydrogen);
		}
		else if(name.equals("carbon")){
			
			Carbon carbon = new Carbon
					(random.nextInt(350)+106,
							random.nextInt(150)+ 106,
								random.nextInt(350)+106);
			simulator.atom.add(carbon);
		}
		else if(name.equals("oxygen")){
			
			Oxygen oxygen = new Oxygen
					(random.nextInt(350)+106,
							random.nextInt(150)+ 106,
								random.nextInt(350)+106);
			simulator.atom.add(oxygen);
		}
		else if(name.equals("phosphorus")){
		
			Phosphorus phosporus = new Phosphorus
					(random.nextInt(350)+106,
							random.nextInt(150)+ 106,
								random.nextInt(350)+106);
			simulator.atom.add(phosporus);
		}
		else if(name.equals("nitrogen")){
			
			Nitrogen nitrogen = new Nitrogen
					(random.nextInt(350)+106,
							random.nextInt(150)+ 106,
								random.nextInt(350)+106);
			simulator.atom.add(nitrogen);
		}
		else if(name.equals("sodium")){
			
			Sodium sodium = new Sodium
					(random.nextInt(350)+106,
							random.nextInt(150)+ 106,
								random.nextInt(350)+106);
			simulator.atom.add(sodium);
		}
		else if(name.equals("potassium")){
			
			Potassium potassium = new Potassium
					(random.nextInt(350)+106,
							random.nextInt(150)+ 106,
								random.nextInt(350)+106);
			simulator.atom.add(potassium);
		}
		else if(name.equals("chlorine")){
			
			Chlorine chlorine = new Chlorine
					(random.nextInt(350)+106,
							random.nextInt(150)+ 106,
								random.nextInt(350)+106);
			simulator.atom.add(chlorine);
		}
	}
}
