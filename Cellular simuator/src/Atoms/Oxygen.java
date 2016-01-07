package Atoms;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

public class Oxygen extends atom {

	public Oxygen(int xPos,int yPos, int zPos){
		isotope = 16;
		name = "oxygen";
		charName = 'O';
		color = "red";
		materialColor = new PhongMaterial();
		materialColor.setSpecularColor(Color.rgb(200, 20, 20));
		materialColor.setDiffuseColor(Color.rgb(230, 20, 20));
		setWeight(isotope);
		covalentRadius = 66;
		vdwRadius = 152;
		setNumberOfValenceElectrons(6);
		electronAffinity = 3;
		setCharge(-2);
		this.xPos = xPos;
		this.yPos = yPos;
		this.zPos = zPos;
		setMaxBindNumber(2);
	}
	
	
	
}

