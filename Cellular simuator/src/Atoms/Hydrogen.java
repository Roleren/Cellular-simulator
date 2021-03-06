package Atoms;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

public class Hydrogen extends atom {
	public Hydrogen(int xPos, int yPos, int zPos) {
		isotope = 1;
		name = "hydrogen";
		charName = 'H';
		color = "orange";
		materialColor = new PhongMaterial();
		materialColor.setSpecularColor(Color.rgb(200, 130, 77));
		materialColor.setDiffuseColor(Color.rgb(250, 130, 77));
		setWeight(isotope);
		setCovalentRadius(31);
		vdwRadius = 120;
		setNumberOfValenceElectrons(1);
		electronAffinity = 50;
		setCharge(1);
		setStartPositions(xPos, yPos, zPos);
		setMaxBindNumber(1);
	}
}
