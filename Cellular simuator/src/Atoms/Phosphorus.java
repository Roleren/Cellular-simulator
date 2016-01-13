package Atoms;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

public class Phosphorus extends atom {

	public Phosphorus(int xPos, int yPos, int zPos) {
		isotope = 31;
		name = "phosphorus";
		charName = 'P';
		color = "blue";
		materialColor = new PhongMaterial();
		materialColor.setSpecularColor(Color.rgb(100, 80, 50));
		materialColor.setDiffuseColor(Color.rgb(150, 100, 70));
		setWeight(isotope);
		setCovalentRadius(107);
		vdwRadius = 180;
		setNumberOfValenceElectrons(6);
		electronAffinity = 6;
		setCharge(+5);
		setStartPositions(xPos, yPos, zPos);
		setMaxBindNumber(5);
	}

}
