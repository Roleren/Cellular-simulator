package Atoms;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

public class Nitrogen extends atom {

	public Nitrogen(int xPos, int yPos, int zPos) {
		isotope = 14;
		name = "nitrogen";
		charName = 'N';
		color = "green";
		materialColor = new PhongMaterial();
		materialColor.setSpecularColor(Color.rgb(0, 180, 0));
		materialColor.setDiffuseColor(Color.rgb(0, 200, 0));
		setWeight(isotope);
		setCovalentRadius(71);
		vdwRadius = 155;
		setNumberOfValenceElectrons(5);
		electronAffinity = 5;
		setCharge(+5);
		setStartPositions(xPos, yPos, zPos);
		setMaxBindNumber(3);
	}

}
