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
		NumberOfValenceElectrons = 4;
		setCharge(4);
		this.xPos = xPos;
		this.yPos = yPos;
	}
}
