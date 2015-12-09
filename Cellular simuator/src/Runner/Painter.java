package Runner;



import java.util.ArrayList;

import Atoms.atom;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Painter extends Pane {
	GraphicsContext gc;
	Simulator simulator;
	int scale;
	Image backGround;
	
	public Painter(Simulator simulator,GraphicsContext gc){
		super();
		this.simulator = simulator;
		this.gc = gc;
		paint(gc);
		scale = 100;
		setBackGround("/Carbon.gif");
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
	
	public void setBackGround(String name){
		backGround = new Image(getClass().getResourceAsStream(name));
		ImageView bg = new ImageView(backGround);
		this.getChildren().add(bg);
	}
	public Image getBackGround(){
		return backGround;
	}
}
