package Atoms;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

public class Carbon extends atom {

	public Carbon(int xPos, int yPos, int zPos) {
		isotope = 12;
		name = "carbon";
		charName = 'C';
		color = "black";
		materialColor = new PhongMaterial();
		materialColor.setSpecularColor(Color.rgb(30, 30, 30));
		materialColor.setDiffuseColor(Color.rgb(60, 60, 60));
		setWeight(isotope);
		setCovalentRadius(73);
		vdwRadius = 170;
		setNumberOfValenceElectrons(4);
		electronAffinity = 7;

		setCharge(4);
		setStartPositions(xPos, yPos, zPos);
		setMaxBindNumber(4);
	}

}
