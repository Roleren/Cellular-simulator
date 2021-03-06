package Runner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import Atoms.atom;
import javafx.scene.Node;
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
	//Used to make sure the drawing are correct size compared to screen. 
	//ig. 120 vdw = 12 vdw on screen if scale = 10
	private int scale = 10;
	
	// Booleans 
	boolean showCovalentBonds = true;
	boolean showAffinityBonds = true;
	
	Image backGround;
	// material for drawings
	PhongMaterial bond = new PhongMaterial();
	PhongMaterial hydrogenBond = new PhongMaterial();

	// Threads for multithreading
	Thread thread1;
	Thread thread2;
	Thread thread3;
	Thread thread4;

	List<Future<Runnable>> futures = new ArrayList<Future<Runnable>>();

	ArrayList<Node> list1;
	ArrayList<Node> list2;
	ArrayList<Node> list3;
	ArrayList<Node> list4;

	public Painter(Simulator simulator) {
		super();
		this.simulator = simulator;

		setColorsOfBonds();

		// setStyle("-fx-background-color: lightgrey; -fx-border-color: blue;");

	}

	public void paint() {
		
		// Single threaded
		doTasks(simulator.getAtoms());
		// MultiThreaded
		// paintMultiThreaded();
	}

	public void paintMultiThreaded() {
		ExecutorService service = Executors.newFixedThreadPool(4);
		futures = new ArrayList<Future<Runnable>>();

		thread1 = new Thread() {
			public void run() {
				setNodesForList1(doTasksMT(simulator.process1));
			}
		};
		Future f1 = service.submit(thread1);
		futures.add(f1);

		thread2 = new Thread() {
			public void run() {
				setNodesForList2(doTasksMT(simulator.process2));
			}
		};
		Future f2 = service.submit(thread2);
		futures.add(f2);

		thread3 = new Thread() {
			public void run() {
				setNodesForList3(doTasksMT(simulator.process3));
			}
		};
		Future f3 = service.submit(thread3);
		futures.add(f3);

		thread4 = new Thread() {
			public void run() {
				setNodesForList4(doTasksMT(simulator.process4));
			}
		};
		Future f4 = service.submit(thread4);
		futures.add(f4);

		// wait for threads to finish
		for (Future<Runnable> f : futures) {
			try {
				f.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		service.shutdown();

		getChildren().clear();
		addChildren();
		// System.out.println("Children: "+this.getChildren().size());
		// for(Node e : this.getChildren()){
		// System.out.println("Child: "+e.toString());
		// }
	}

	public void setBackGround(String name) {
		backGround = new Image(getClass().getResourceAsStream(name));
		ImageView bg = new ImageView(backGround);
		this.getChildren().add(bg);
	}

	public Image getBackGround() {
		return backGround;
	}

	/**
	 * Single threaded version
	 * 
	 * @param currentAtoms
	 */
	public void doTasks(CopyOnWriteArrayList<atom> currentAtoms) {
		// Update painting
		getChildren().clear();
		for (atom a : currentAtoms) {

			Sphere sphere = new Sphere((double) a.getVdwRadius() / (getScale()));
			sphere.setMaterial(a.getMaterialColor());
			sphere.setTranslateX(a.getxPos());
			sphere.setTranslateY(a.getyPos());
			sphere.setTranslateZ(a.getzPos());
			this.getChildren().add(sphere);

			if (a.isBound()) {
				
				// Covalent bonds
				if(showCovalentBonds){
					for (atom currentAtom : a.getBoundAtoms()) {
						Cylinder cylinder = new Cylinder(50 / getScale(), 100 / getScale());
						cylinder.setMaterial(bond);
						cylinder.setTranslateX((a.getxPos() + currentAtom.getxPos()) / 2);
						cylinder.setTranslateY((a.getyPos() + currentAtom.getyPos()) / 2);
						cylinder.setTranslateZ((a.getzPos() + currentAtom.getzPos()) / 2);
						this.getChildren().add(cylinder);
					}
				}
				// HydrogenBonds
				if(showAffinityBonds){
					if (!a.getAffinityElectrons().isEmpty()) {
						for (atom b : a.getAffinityElectrons()) {
							Cylinder cylinder = new Cylinder(50 / getScale(), 100 / getScale());
							cylinder.setMaterial(hydrogenBond);
							cylinder.setTranslateX((a.getxPos() + b.getxPos()) / 2);
							cylinder.setTranslateY((a.getyPos() + b.getyPos()) / 2);
							cylinder.setTranslateZ((a.getzPos() + b.getzPos()) / 2);
							this.getChildren().add(cylinder);
						}
					}
				}
			}
		}

	}

	/**
	 * Multithreaded version
	 * 
	 * @param currentAtoms
	 */
	public ArrayList<Node> doTasksMT(ArrayList<ArrayList<atom>> currentList) {
		// Update painting
		ArrayList<Node> list = new ArrayList<Node>();
		for (ArrayList<atom> currentAtoms : currentList) {
			for (atom a : currentAtoms) {
				createAtomicSphere(a, list);

				if (a.isBound()) {
					paintCovalentBond(a, list);

					paintAffinityBond(a, list);
				}
			}
		}
		return list;
	}

	public void createAtomicSphere(atom a, ArrayList<Node> list) {
		Sphere sphere = new Sphere(a.getVdwRadius() / (getScale()));
		sphere.setMaterial(a.getMaterialColor());
		sphere.setTranslateX(a.getxPos());
		sphere.setTranslateY(a.getyPos());
		sphere.setTranslateZ(a.getzPos());
		list.add(sphere);
	}

	public void paintCovalentBond(atom a, ArrayList<Node> list) {
		// Covalent bonds
		for (atom currentAtom : a.getBoundAtoms()) {
			Cylinder cylinder = new Cylinder(50 / getScale(), 100 / getScale());
			cylinder.setMaterial(bond);
			cylinder.setTranslateX((a.getxPos() + currentAtom.getxPos()) / 2);
			cylinder.setTranslateY((a.getyPos() + currentAtom.getyPos()) / 2);
			cylinder.setTranslateZ((a.getzPos() + currentAtom.getzPos()) / 2);
			list.add(cylinder);
		}
	}

	public void paintAffinityBond(atom a, ArrayList<Node> list) {
		// HydrogenBonds
		boolean isAffinityBound = !a.getAffinityElectrons().isEmpty();
		if (isAffinityBound) {
			for (atom b : a.getAffinityElectrons()) {
				Cylinder cylinder = new Cylinder(50 / getScale(), 100 / getScale());
				cylinder.setMaterial(hydrogenBond);
				cylinder.setTranslateX((a.getxPos() + b.getxPos()) / 2);
				cylinder.setTranslateY((a.getyPos() + b.getyPos()) / 2);
				cylinder.setTranslateZ((a.getzPos() + b.getzPos()) / 2);
				list.add(cylinder);
			}
		}
	}

	private void setNodesForList1(ArrayList<Node> list) {
		list1 = list;
	}

	private void setNodesForList2(ArrayList<Node> list) {
		list2 = list;
	}

	private void setNodesForList3(ArrayList<Node> list) {
		list3 = list;
	}

	private void setNodesForList4(ArrayList<Node> list) {
		list4 = list;
	}

	private void addChildren() {
		if (list1 != null)
			this.getChildren().addAll(list1);
		if (list2 != null)
			this.getChildren().addAll(list2);
		if (list3 != null)
			this.getChildren().addAll(list3);
		if (list4 != null)
			this.getChildren().addAll(list4);
	}

	private void setColorsOfBonds() {
		bond.setSpecularColor(Color.rgb(30, 30, 200));
		bond.setDiffuseColor(Color.rgb(60, 60, 250));

		hydrogenBond.setSpecularColor(Color.rgb(200, 100, 80));
		hydrogenBond.setDiffuseColor(Color.rgb(230, 130, 110));
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}
	
	public void switchCovalentBondVisibility(){
		System.out.println("switch");
		if(showCovalentBonds)
			showCovalentBonds = false;
		else 
			showCovalentBonds = true;
	}
	
	public void switchAffinityBondVisibility(){
		if(showAffinityBonds)
			showAffinityBonds = false;
		else 
			showAffinityBonds = true;
	}

	//// public void setScroll(){
	//// this.setOnScroll(new ScrollEvent
	////
	//// }
	// }

}
