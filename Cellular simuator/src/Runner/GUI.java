package Runner;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;

import Atoms.Carbon;
import Atoms.Hydrogen;
import Atoms.Nitrogen;
import Atoms.Oxygen;
import Atoms.Phosphorus;
import Atoms.atom;
import Molecules.CH4;
import Molecules.H2O;
import Molecules.Phosphate;
import Molecules.Ribose;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class GUI extends Application{
	//Position variables
	static int x;
	static int x_max;
	static int x_min;
	static int y;
	static int y_max;
	static int y_min;
	static int z;
	static int z_max;
	static int z_min;
	static int scale = 100;
	
	boolean paused;
	boolean rotating = false;
	int step;
	Random random = new Random();
	//Objects
	static ArrayList<atom> possibleObjects;
	static Simulator simulator;
	Painter mainFrame;
	Pane creations, statusBar;
	Button restart, pause,rotate, exit;
//	Timeline timer;
	AnimationTimer timer;
	PointLight light;
	RotateTransition rotation;
	MouseHandler mouseActions;

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
		x_max = x-(scale*2);
		x_min = scale;
		//Høyde
		y = 600;
		y_max = y-(scale*2);
		y_min = scale;
		//Innover
		z = 800;
		z_max = z-(scale*2);
		z_min = scale;
		
		System.out.println("This is a cell simualtion");
	    //Atoms
		possibleObjects = new ArrayList<atom>();
		possibleObjects.add(new Carbon(0,0,0));
		possibleObjects.add(new Hydrogen(0,0,0));
		possibleObjects.add(new Oxygen(0,0,0));
		possibleObjects.add(new Phosphorus(0,0,0));
		possibleObjects.add(new Nitrogen(0,0,0));
		//Molecules
		possibleObjects.add(new H2O());
		possibleObjects.add(new CH4());
		possibleObjects.add(new Phosphate());
		possibleObjects.add(new Ribose());
		
//		simulator = new Simulator(x_max,y_max,z_max,x_min,y_min,z_min);
		simulator = new Simulator();
		step = 0;
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//Create stage
		primaryStage.setTitle("Cell simulator 2015");
		Group root = new Group();
		Scene scene = new Scene(root,x,y);
		//Canvas creation
		
		Canvas canvas = new Canvas(x_max,y_max);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		//Create nodes
		mainFrame = new Painter(simulator);
		creations = new Pane();
		statusBar = new Pane();
		BorderPane border = new BorderPane();
		GridPane grid = new GridPane();
		//Create node buttons
		makeButton(border);
		
		//Add to parrent scene
		grid.add(restart, 1, 0);
		grid.add(pause, 2, 0);
		grid.add(rotate, 3, 0);
		grid.add(exit, 4, 0);
		border.setTop(grid);
		mainFrame.getChildren().add(canvas);
		border.setCenter(mainFrame);
		
		
		root.getChildren().add(border);
		
		//Lights
		light = new PointLight();
		light.setTranslateX(0);
		light.setTranslateY(0);
		light.setTranslateZ(-100000/scale);
		root.getChildren().add(light);
		
		//start show
		scene.setCamera(new PerspectiveCamera());
		mouseActions = new MouseHandler(mainFrame);
		primaryStage.setScene(scene);
		primaryStage.show();
		launchAnimation(gc);

	}
	public void launchAnimation(GraphicsContext gc){
		timer = new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				simulator.objectExecutor();
				mainFrame.getChildren().clear();
				mainFrame.paint();
				step++;
			}
			
		};
		timer.start();
		paused = false;
	}
	
	public void makeButton(BorderPane border){
		//Buttons
		restart = new Button("Restart");
		restart.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override public void handle(ActionEvent e){
				System.out.println("Restart");
				simulator.restart(x_max,y_max,z_max,x_min,y_min,z_min);
				paused = false;
				step = 0;
			}
		});
		
		pause = new Button("Pause");
		pause.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override public void handle(ActionEvent e){
				if(!paused){
					pause.setText("fortsett");
					timer.stop();
					paused = true;
				}
				else{
					pause.setText("Pause");
					timer.start();
					paused = false;
				}
			}
		});
		
		rotate = new Button("Rotate");
		rotate.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override public void handle(ActionEvent e){
				rotateAroundYAxis();
				
				
			}
		});
		
		exit = new Button("Exit");
		exit.setOnAction(new EventHandler<ActionEvent>() {
			
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
					createAtom(currentAtom);
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
	
	public void createAtom(atom currentAtom){
		String name = currentAtom.toString();
		if(name.equals("hydrogen")){
			
			Hydrogen hydrogen = new Hydrogen
					(random.nextInt(x_max- x_min)+x_min,
							random.nextInt(y_max-y_min)+ y_min,
								random.nextInt(z_max-z_min)+ z_min);
			simulator.atom.add(hydrogen);
		}
		else if(name.equals("carbon")){
			
			Carbon carbon = new Carbon
					(random.nextInt(x_max- x_min)+x_min,
							random.nextInt(y_max-y_min)+ y_min,
								random.nextInt(z_max-z_min)+ z_min);
			simulator.atom.add(carbon);
		}
		else if(name.equals("oxygen")){
			
			Oxygen oxygen = new Oxygen
					(random.nextInt(x_max- x_min)+x_min,
							random.nextInt(y_max-y_min)+ y_min,
								random.nextInt(z_max-z_min)+ z_min);
			simulator.atom.add(oxygen);
		}
		else if(name.equals("phosphorus")){
		
			Phosphorus phosporus = new Phosphorus
					(random.nextInt(x_max- x_min)+x_min,
							random.nextInt(y_max-y_min)+ y_min,
								random.nextInt(z_max-z_min)+ z_min);
			simulator.atom.add(phosporus);
		}
		else if(name.equals("nitrogen")){
			
			Nitrogen nitrogen = new Nitrogen
					(random.nextInt(x_max- x_min)+x_min,
							random.nextInt(y_max-y_min)+ y_min,
								random.nextInt(z_max-z_min)+ z_min);
			simulator.atom.add(nitrogen);
		}
		else if(name.equals("CH4")){
			
			CH4 cH4 = new CH4();
			ArrayList<atom> atoms = cH4.getAtomsOfMolecule();
			for(atom a : atoms){
				simulator.atom.add(a);
			}
		}
		else if(name.equals("phosphate")){
			
			Phosphate phosphate = new Phosphate();
			ArrayList<atom> atoms = phosphate.getAtomsOfMolecule();
			for(atom a : atoms){
				simulator.atom.add(a);
			}
		}
		else if(name.equals("ribose")){
				
				Ribose ribose = new Ribose();
				ArrayList<atom> atoms = ribose.getAtomsOfMolecule();
				for(atom a : atoms){
					simulator.atom.add(a);
				}
			}
		else if(name.equals("H2O")){
			
			H2O h2o = new H2O();
			ArrayList<atom> atoms = h2o.getAtomsOfMolecule();
			for(atom a : atoms){
				simulator.atom.add(a);
			}
		}
	}
	
	public void rotateAroundYAxis(){
		rotation = new RotateTransition(new javafx.util.Duration(10000),mainFrame);
		rotation.setFromAngle(0);
		rotation.setToAngle(360);
		rotation.setAxis(new Point3D(x/2, y/2, z/2));
		rotation.setCycleCount(1);
		rotation.setInterpolator(Interpolator.LINEAR);
		rotation.play();
	}
	
	public void setXYZ(int a,int b, int c){
		x = a;
		y = b;
		z = c;
	}

}
