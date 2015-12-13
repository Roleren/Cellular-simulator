package Atoms;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

public class Phosphorus extends atom {

	public Phosphorus(int xPos,int yPos, int zPos){
		isotope = 31;
		name = "phosphorus";
		charName = 'P';
		color = "blue";
		materialColor = new PhongMaterial();
		materialColor.setSpecularColor(Color.rgb(100, 80, 50));
		materialColor.setDiffuseColor(Color.rgb(150, 100, 70));
		setWeight(isotope);
		covalentRadius = 107;
		vdwRadius = 180;
		NumberOfValenceElectrons = 6;
		electronAffinity = 6;
		setCharge(+5);
		this.xPos = xPos;
		this.yPos = yPos;
		this.zPos = zPos;
		maxBindNumber = 5;
	}
	
	
	
}


