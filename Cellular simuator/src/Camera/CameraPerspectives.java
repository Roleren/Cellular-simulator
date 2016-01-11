package Camera;

import Runner.GUI;
import Runner.Painter;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.geometry.Point3D;

public class CameraPerspectives {
	public static void rotateAroundYAxis(GUI gui, Painter mainFrame){
		int x = gui.getX();
		int y = gui.getY();
		int z = gui.getZ();
		
		RotateTransition rotation;
		rotation = new RotateTransition(new javafx.util.Duration(10000),mainFrame);
		rotation.setFromAngle(0);
		rotation.setToAngle(360);
		rotation.setAxis(new Point3D(x/2, y/2, z/2));
		rotation.setCycleCount(1);
		rotation.setInterpolator(Interpolator.LINEAR);
		rotation.play();
	}
}
