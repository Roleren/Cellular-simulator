package Atoms;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

public class Potassium extends atom {

	public Potassium(int xPos, int yPos, int zPos) {
		isotope = 39;
		name = "potassium";
		charName = 'K';
		color = "cyan";
		materialColor = new PhongMaterial();
		materialColor.setSpecularColor(Color.rgb(0, 230, 230));
		materialColor.setDiffuseColor(Color.rgb(0, 250, 250));
		setWeight(isotope);
		setCovalentRadius(203);
		vdwRadius = 275;
		setNumberOfValenceElectrons(1);
		electronAffinity = 42;

		setCharge(1);
		setStartPositions(xPos, yPos, zPos);
		setMaxBindNumber(1);
	}

}
