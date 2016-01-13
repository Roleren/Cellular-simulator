package Atoms;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

public class Sodium extends atom {

	public Sodium(int xPos, int yPos, int zPos) {
		isotope = 23;
		name = "sodium";
		charName = 'N';
		color = "Dark green";
		materialColor = new PhongMaterial();
		materialColor.setSpecularColor(Color.rgb(230, 230, 0));
		materialColor.setDiffuseColor(Color.rgb(250, 250, 0));
		setWeight(isotope);
		setCovalentRadius(166);
		vdwRadius = 227;
		setNumberOfValenceElectrons(1);
		electronAffinity = 40;

		setCharge(1);
		setStartPositions(xPos, yPos, zPos);
		setMaxBindNumber(1);
	}

}
