package Atoms;

public class Carbon extends atom {

	public Carbon(int xPos,int yPos){
		isotope = 12;
		name = "carbon";
		color = "black";
		setWeight(isotope);
		covalentRadius = 73;
		vdwRadius = 170;
		NumberOfValenceElectrons = 4;
		setCharge(4);
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	
	
}
