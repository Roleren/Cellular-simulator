package Atoms;

public class Hydrogen extends atom {
	public Hydrogen(int xPos,int yPos){
		isotope = 1;
		name = "hydrogen";
		charName = 'H';
		color = "grey";
		setWeight(isotope);
		covalentRadius = 31;
		vdwRadius = 120;
		NumberOfValenceElectrons = 1;
		setCharge(1);
		this.xPos = xPos;
		this.yPos = yPos;
		maxBindNumber = 1;
	}
}
