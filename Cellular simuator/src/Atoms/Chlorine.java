package Atoms;


import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

public class Chlorine extends atom {

	public Chlorine(int xPos,int yPos, int zPos){
		isotope = 35;
		name = "chlorine";
		charName = 'C';
		color = "purple";
		materialColor = new PhongMaterial();
		materialColor.setSpecularColor(Color.rgb(100, 0, 100));
		materialColor.setDiffuseColor(Color.rgb(130, 0, 130));
		setWeight(isotope);
		setCovalentRadius(102);
		vdwRadius = 175;
		setNumberOfValenceElectrons(7);
		electronAffinity = 4;
		
		setCharge(-1);
		this.xPos = xPos;
		this.yPos = yPos;
		this.zPos = zPos;
		setMaxBindNumber(1);
	}
	
	
	
}
