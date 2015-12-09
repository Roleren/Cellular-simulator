package Runner;

import java.util.ArrayList;

import Atoms.Carbon;
import Atoms.Hydrogen;
import Atoms.atom;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GUI extends Application{
	static int x;
	static int y;
	static ArrayList<atom> possibleObjects;
	static Simulator simulator;
	Painter mainFrame;
	Pane creations, statusBar;
	Button restart, pause, exit;
//	Timeline timer;
	AnimationTimer timer;
	int step;
	

	public GUI() {
	}
	
	@Override
	/*
	 * Pseudo constructor init
	 * @see javafx.application.Application#init()
	 */
	public void init(){
		//Bredde
		x = 800;
		//Høyde
		y = 600;
		System.out.println("This is a cell simualtion");
	    possibleObjects = new ArrayList<atom>();
		possibleObjects.add(new Carbon(0,0));
		possibleObjects.add(new Hydrogen(0,0));
		simulator = new Simulator(x,y);
//		timer = new Timeline(10);
//		timer.setCycleCount(Integer.MAX_VALUE);
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//Create stage
		primaryStage.setTitle("Cell simulator 2015");
		Group root = new Group();
		Scene scene = new Scene(root,x,y);
		//Canvas creation
		Canvas canvas = new Canvas(x,y);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		//Create nodes
		mainFrame = new Painter(simulator,gc);
		creations = new Pane();
		statusBar = new Pane();
		BorderPane border = new BorderPane();
		GridPane grid = new GridPane();
		//Create node buttons
		makeButton(border);
		
		//Add to parrent scene
		grid.add(restart, 1, 0);
		grid.add(pause, 2, 0);
		grid.add(exit, 3, 0);
		border.setTop(grid);
		border.setCenter(mainFrame);
		
		
		
		
		root.getChildren().add(border);
//		root.getChildren().add(canvas);
		
		//start show
		primaryStage.setScene(scene);
		primaryStage.show();
//		timer.play();
		
		
	}
	
	public void makeButton(BorderPane border){
		//Buttons
		restart = new Button("Restart");
		restart.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override public void handle(ActionEvent e){
				System.out.println("Restart");
			}
		});
		
		pause = new Button("Pause");
		pause.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override public void handle(ActionEvent e){
//				if(timer.getStatus() == Animation.Status.RUNNING){
//					pause.setText("fortsett");
//					timer.pause();
//				}
//				else{
//					pause.setText("Pause");
//					timer.play();
//				}
			}
		});
		
		exit = new Button("Exit");
		pause.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override public void handle(ActionEvent e){
				System.out.println("Exit");
				Platform.exit();
			}
		});
		//creations buttons
		GridPane grid1 = new GridPane();
		int i = 1;
		Button currentButton;
		for(atom currentAtom : possibleObjects){
			currentButton = new Button(currentAtom.toString());
			
			currentButton.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override public void handle(ActionEvent e){
					System.out.println(currentAtom.toString());
				}
			});
			
			grid1.add(currentButton, 0, i);
			i++;
		}
		border.setLeft(grid1);
	}
	
	public static void main(String[] args) {
		Application.launch();
	}

	

	
	

}
