package Runner;

import Buttons.MakeButtons;
import Camera.Cam;
import InputHandler.MouseHandler;
import Math.CellGrid;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GUI extends Application {
	// Position variables
	static int x;
	static int x_max;
	static int x_min;
	static int y;
	static int y_max;
	static int y_min;
	static int z;
	static int z_max;
	static int z_min;
	private static int scale = 100;

	private boolean paused;
	boolean rotating = false;
	private int step;

	static Simulator simulator;
	// Scene objects
	private Painter mainFrame;
	Pane creations, statusBar;

	// Light, timer, mousehandler
	private AnimationTimer timer;

	MouseHandler mouseActions;
	// Cell lists for optimization
	int numberOfcellLists;
	CellGrid cellGrid;

	public GUI() {
	}

	@Override
	/*
	 * Pseudo constructor init
	 * 
	 * @see javafx.application.Application#init()
	 */
	public void init() {

		setWindowSize(800, 600, 800);
		// Cell lists for optimization
		cellGrid = new CellGrid(x_max - x_min, y_max - y_min, z_max - z_min);
		simulator = new Simulator(cellGrid);
		setStep(0);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Create stage
		primaryStage.setTitle("Cell simulator 2015");
		Group root = new Group();
		Scene scene = new Scene(root, x, y);

		// Canvas creation
		Canvas canvas = new Canvas(x_max, y_max);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		// Create nodes
		setMainFrame(new Painter(simulator));
		creations = new Pane();
		statusBar = new Pane();
		BorderPane border = new BorderPane();

		// Create node buttons
		MakeButtons buttons = new MakeButtons();
		buttons.makeButton(border, simulator, this);

		setInitialChildrenToRoot(border, canvas, root);

		startShow(root, scene, primaryStage, gc);

	}

	public void launchAnimation(GraphicsContext gc) {
		setTimer(new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				// System.out.println("1");
				simulator.objectExecutor();
				// System.out.println("2");
				// cellGrid.updateCellGrid(simulator);
				// System.out.println("3");
				getMainFrame().paint();
				// System.out.println("4");
				setStep(getStep() + 1);
			}
		});
		getTimer().start();
		setPaused(false);
	}

	public void setInitialChildrenToRoot(BorderPane border, Canvas canvas, Group root) {
		getMainFrame().getChildren().add(canvas);
		border.setCenter(getMainFrame());
		root.getChildren().add(border);
	}

	public void startShow(Group root, Scene scene, Stage primaryStage, GraphicsContext gc) {
		// start show
		Cam cam = new Cam(getMainFrame(), root, this);
		scene.setCamera(cam);
		mouseActions = new MouseHandler(getMainFrame(), cam);
		primaryStage.setScene(scene);
		primaryStage.show();
		launchAnimation(gc);
	}

	@SuppressWarnings("static-access")
	public void setWindowSize(int x, int y, int z) {
		// Width
		this.x = x;
		x_max = x - (getScale() * 2);
		x_min = getScale();
		// Height
		this.y = y;
		y_max = y - (getScale() * 2);
		y_min = getScale();
		// Length
		this.z = z;
		z_max = z - (getScale() * 2);
		z_min = getScale();
	}

	public void setXYZ(int a, int b, int c) {
		x = a;
		y = b;
		z = c;
	}

	public static void main(String[] args) {
		Application.launch();
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public void restart() {
		simulator.restart(x_max, y_max, z_max, x_min, y_min, z_min);
	}

	public AnimationTimer getTimer() {
		return timer;
	}

	public void setTimer(AnimationTimer timer) {
		this.timer = timer;
	}

	public Painter getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(Painter mainFrame) {
		this.mainFrame = mainFrame;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public static int getScale() {
		return scale;
	}

	public static void setScale(int scale) {
		GUI.scale = scale;
	}

}
