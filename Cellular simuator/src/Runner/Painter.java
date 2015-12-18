package Runner;



import java.util.ArrayList;

import Atoms.atom;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;

public class Painter extends Pane {
	GraphicsContext gc;
	Simulator simulator;
	ArrayList<atom> currentAtoms;
	int scale = 10;
	Image backGround;
	
	PhongMaterial bond = new PhongMaterial();
	
	public Painter(Simulator simulator){
		super();
		this.simulator = simulator;
		setBackGround("/Carbon.gif");
		bond.setSpecularColor(Color.rgb(30, 30, 200));
		bond.setDiffuseColor(Color.rgb(60, 60, 250));
//		setStyle("-fx-background-color: lightgrey; -fx-border-color: blue;");
		
		
		
		
		paint();
		
	}
	
	public void paint(){

		currentAtoms = simulator.getAtoms();
		for(atom a : currentAtoms ){

			Sphere sphere = new Sphere(a.getVdwRadius()/(scale));
			sphere.setMaterial(a.getMaterialColor());
			sphere.setTranslateX(a.getxPos());
			sphere.setTranslateY(a.getyPos());
			sphere.setTranslateZ(a.getzPos());
			this.getChildren().add(sphere);
			
			if(a.isBound()){
				for(atom currentAtom : a.getBoundAtoms()){
					Cylinder cylinder = new Cylinder(50/scale,100/scale);
					cylinder.setMaterial(bond);
					cylinder.setTranslateX((a.getxPos()+currentAtom.getxPos())/2);
					cylinder.setTranslateY((a.getyPos()+currentAtom.getyPos())/2);
					cylinder.setTranslateZ((a.getzPos()+currentAtom.getzPos())/2);
					this.getChildren().add(cylinder);
				}
//				if(a.getCharName() == 'O'){
//					Cylinder cylinder = new Cylinder(50/scale,100/scale);
//					cylinder.setMaterial(bond);
//					
//					this.getChildren().add(cylinder);
//				}
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
////	public void setScroll(){
////		this.setOnScroll(new ScrollEvent
////			
////		}
//	}
	
}
