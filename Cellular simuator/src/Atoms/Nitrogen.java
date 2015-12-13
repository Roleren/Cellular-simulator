package Atoms;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

public class Nitrogen extends atom {

	public Nitrogen(int xPos,int yPos, int zPos){
		isotope = 14;
		name = "nitrogen";
		charName = 'N';
		color = "green";
		materialColor = new PhongMaterial();
		materialColor.setSpecularColor(Color.rgb(0, 180, 0));
		materialColor.setDiffuseColor(Color.rgb(0, 200, 0));
		setWeight(isotope);
		covalentRadius = 71;
		vdwRadius = 155;
		NumberOfValenceElectrons = 5;
		setCharge(+5);
		this.xPos = xPos;
		this.yPos = yPos;
		this.zPos = zPos;
		maxBindNumber = 3;
	}
	
	
	
}
