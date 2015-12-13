package Atoms;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

public class Carbon extends atom {

	public Carbon(int xPos,int yPos, int zPos){
		isotope = 12;
		name = "carbon";
		charName = 'C';
		color = "black";
		materialColor = new PhongMaterial();
		materialColor.setSpecularColor(Color.rgb(30, 30, 30));
		materialColor.setDiffuseColor(Color.rgb(60, 60, 60));
		setWeight(isotope);
		covalentRadius = 73;
		vdwRadius = 170;
		NumberOfValenceElectrons = 4;
		electronAffinity = 7;
		
		setCharge(4);
		this.xPos = xPos;
		this.yPos = yPos;
		this.zPos = zPos;
		maxBindNumber = 4;
	}
	
	
	
}
