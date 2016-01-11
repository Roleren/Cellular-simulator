package Runner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import AtomInteractions.AtomActions;
import Atoms.Carbon;
import Atoms.Hydrogen;
import Atoms.Nitrogen;
import Atoms.Oxygen;
import Atoms.atom;
import Math.CellGrid;
public class Simulator {
	public CopyOnWriteArrayList<atom> atom = new CopyOnWriteArrayList<atom>();
	Random random = new Random();
	
	CellGrid cellGrid;
	ArrayList<ArrayList<atom>> process1;
	ArrayList<ArrayList<atom>> process2;
	ArrayList<ArrayList<atom>> process3;
	ArrayList<ArrayList<atom>> process4;
	Thread thread1;
	Thread thread2;
	Thread thread3;
	Thread thread4;
	
	List<Future<Runnable>> futures = new ArrayList<Future<Runnable>>();
	Simulator This;
	public  Simulator(CellGrid cellGrid){
		This = this;
		this.cellGrid = cellGrid;
		gridOranizer();
	}
	
	

	



	public Simulator(int xMax,int yMax,int zMax,int xMin,int yMin,int zMin ){
		testStart(xMax,yMax,zMax, xMin,yMin, zMin);
		
	}
	public void nesteSteg(){
		objectExecutor();
	}
	public void objectExecutor(){
		objectExecutorMultiThreaded();
//		for(atom currentAtom : atom){
//			currentAtom.updateAtom(this);
//		}
	}
	
	public CopyOnWriteArrayList<Atoms.atom> getAtoms(){
		return atom;
	}
	public CellGrid getCellGrid() {
		return cellGrid;
	}
	public void restart(int xMax,int yMax,int zMax, int xMin,int yMin,int zMin ){
		atom = new CopyOnWriteArrayList<atom>();
		System.out.println(atom.size());
		testStart(xMax,yMax,zMax, xMin,yMin, zMin);
	}
	public void testStart(int xMax,int yMax,int zMax, int xMin,int yMin,int zMin){
		atom.add(new Carbon(random.nextInt(xMax-xMin)+xMin, random.nextInt(yMax-yMin)+ yMin, random.nextInt(zMax-zMin)+zMin));
		atom.add(new Carbon(random.nextInt(xMax-xMin)+xMin, random.nextInt(yMax-yMin)+ yMin, random.nextInt(zMax-zMin)+zMin));
		atom.add(new Hydrogen(random.nextInt(xMax-xMin)+xMin, random.nextInt(yMax-yMin)+ yMin, random.nextInt(zMax-zMin)+zMin));
		atom.add(new Hydrogen(random.nextInt(xMax-xMin)+xMin, random.nextInt(yMax-yMin)+ yMin, random.nextInt(zMax-zMin)+zMin));
		atom.add(new Hydrogen(random.nextInt(xMax-xMin)+xMin, random.nextInt(yMax-yMin)+ yMin, random.nextInt(zMax-zMin)+zMin));
		atom.add(new Hydrogen(random.nextInt(xMax-xMin)+xMin, random.nextInt(yMax-yMin)+ yMin, random.nextInt(zMax-zMin)+zMin));
		atom.add(new Hydrogen(random.nextInt(xMax-xMin)+xMin, random.nextInt(yMax-yMin)+ yMin, random.nextInt(zMax-zMin)+zMin));
	}
	private void gridOranizer() {
		int n = cellGrid.getNumberOfcellLists()/4;	
//		System.out.println(0+" "+(n-1)+" "+n+" "+((n*2)-1)+" "+(n*2)+" "+((n*3)-1)+" "+(n*3)+" "+(cellGrid.getNumberOfcellLists()-1));
		process1 = new ArrayList<ArrayList<atom>>(cellGrid.getCellLists().subList(0, n-1));
		process2 = new ArrayList<ArrayList<atom>>(cellGrid.getCellLists().subList(n, (n*2)-1));
		process3 = new ArrayList<ArrayList<atom>>(cellGrid.getCellLists().subList(n*2, (n*3)-1));
		process4 = new ArrayList<ArrayList<atom>>(cellGrid.getCellLists().subList((n*3), cellGrid.getNumberOfcellLists()-1));
		}
	/**
	 * Multithreaded execution that divides atom operations into 4 lists, that works 
	 * cuncurrent. 
	 */
	public void objectExecutorMultiThreaded(){
		ExecutorService service = Executors.newFixedThreadPool(4);
		futures = new ArrayList<Future<Runnable>>();
		thread1 = new Thread(){
			public void run(){
				doTasks(process1);
			}
		};
		Future f1 = service.submit(thread1);
		futures.add(f1);

		thread2 = new Thread(){
			public void run(){
				doTasks(process2);
			}
		};
		Future f2 = service.submit(thread2);
		futures.add(f2);

		thread3 = new Thread(){
			public void run(){
				doTasks(process3);
			}
		};
		Future f3 = service.submit(thread3);
		futures.add(f3);
		
		thread4 = new Thread(){
			public void run(){
				doTasks(process4);
			}
		};
		Future f4 = service.submit(thread4);
		futures.add(f4);
		
		//wait for threads to finish
		for(Future<Runnable> f : futures){
			try {
				f.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		service.shutdown();
	}
	
	public void doTasks(ArrayList<ArrayList<atom>> process){
		//Update interactions and positions
		int i = 0;
		for(ArrayList<atom> currentList : process){
			for(atom currentAtom : currentList){
//				System.out.println(currentAtom);
				i++;
				AtomActions.Action(currentAtom, this);
			}
		}
		System.out.println("i is: "+i);
		
	}
}
