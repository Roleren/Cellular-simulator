package Runner;



import java.util.ArrayList;

import Atoms.atom;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Painter extends Pane {
	Canvas canvas;
	GraphicsContext gc;
	Simulator simulator;
	int scale;
	public Painter(Simulator simulator,GraphicsContext gc){
		super();
		this.simulator = simulator;
		this.gc = gc;
		paint(gc);
		scale = 100;
	
	}
	
	public void paint(GraphicsContext gc){
		ArrayList<atom> currentAtoms = simulator.getAtoms();
		currentAtoms.size();
		for(atom a : currentAtoms ){
			gc.setFill(Color.rgb(250, 130, 77));
			gc.fillOval(a.getxPos(), a.getyPos(),
						gc.getCanvas().getWidth()/scale,
						        gc.getCanvas().getHeight()/scale);
			
		}
	}

}
