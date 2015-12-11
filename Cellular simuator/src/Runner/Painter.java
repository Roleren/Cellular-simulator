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
	ArrayList<atom> currentAtoms;
	int scale = 100;
	Image backGround;
	
	public Painter(Simulator simulator,GraphicsContext gc){
		super();
		this.simulator = simulator;
		this.gc = gc;
		setBackGround("/Carbon.gif");
		paint(gc);
		
	}
	
	public void paint(GraphicsContext gc){

		currentAtoms = simulator.getAtoms();
		for(atom a : currentAtoms ){
			
			if(a.getCharName() == 'H') gc.setFill(Color.rgb(250, 130, 77));
			
			else if(a.getCharName() == 'C') gc.setFill(Color.rgb(0, 0, 0));
			
			else if(a.getCharName() == 'O') gc.setFill(Color.rgb(230, 20, 20));
			
			gc.fillOval(a.getxPos(), a.getyPos(),
						gc.getCanvas().getWidth()/scale,
						        gc.getCanvas().getHeight()/scale);
			if(a.isBound()){
				for(atom currentAtom : a.getBoundAtoms()){
					gc.setFill(Color.rgb(20, 20, 200));
					
					gc.strokeLine(a.getxPos(), a.getyPos(),
							currentAtom.getxPos(), currentAtom.getyPos());
				}
			}
			
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
