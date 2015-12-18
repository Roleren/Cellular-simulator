package LowerObjects;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

public class Electron {
	boolean pair;
	char orbital;
	int charge;
	PhongMaterial materialColor = new PhongMaterial();
	
	
	public Electron(boolean pair){
		if(pair){
			this.pair = pair;
			charge = -2;
		}
		else{
			this.pair = false;
			charge = -1;
		}
		materialColor.setSpecularColor(Color.rgb(200, 20, 20));
		materialColor.setDiffuseColor(Color.rgb(180, 180, 180));
	}
}
