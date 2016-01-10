package Atoms;

import java.util.ArrayList;
import java.util.Random;

import LowerObjects.Bond;
import Math.Calculations;
import Runner.Simulator;
import javafx.scene.paint.PhongMaterial;

public class atom {
	//Standard constants
	int isotope;
	protected String name;
	char charName;
	String color;
	int weight;
	int covalentRadius;
	int vdwRadius;
	static int scale = 10;
	int electronAffinity;
	//divisor for scale
	static int d = 40*40*40;
	
	//Arraylists for bound and affinity electrons
	private ArrayList<atom> affinityElectrons = new ArrayList<atom>(2);
	ArrayList<atom> boundAtoms = new ArrayList<atom>();
	
	//Standard variables
	private boolean bound = false;
	private int NumberOfValenceElectrons;
	private int charge;
	private int bindNumber;
	private int maxBindNumber;
	//Position variables
	int xPos;
	int yPos;
	int zPos;

	private int lastXPos;
	private int lastYPos;
	private int lastZPos;
	private int dv;
	
	
	//Booleans
	boolean moleculeBelowMinMaxDist = false;
	boolean colition = false;
	//Extra objects
	Random random = new Random();
	PhongMaterial materialColor;
	
	
	/*
	 * Checks if the atom can bind another atom
	 */
	public boolean canBind(atom that){
		return Bond.canBind(this, that);
	}
	public void bind(atom that){
		Bond.BindSingle(this,that);
	}
	public void unBind(atom that){
		Bond.BindSingleRemove(this,that);
	}
	
	public boolean isBound() {
		return bound;
	}
	@Override
	public String toString(){
		return name;
	}

	public int getCharge() {
		return charge;
	}

	public void setCharge(int charge) {
		this.charge = charge;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	public int getzPos() {
		return zPos;
	}
	public void setzPos(int zPos) {
		this.zPos = zPos;
	}
	public void updateAtom(Simulator simulator){
		updateOldPositions();
		CheckMoleculeMinMaxBorder();
		
		if(!moleculeBelowMinMaxDist){
			
			atom that = null;
			double min = 100000000;
			//Checks each atom in this atoms cell list
//			System.out.println(getxPos()+" "+getyPos()+" "+getzPos());
			for(atom a : simulator.getCellGrid().getCellLists().
					get((getxPos()+getyPos()+getzPos())/d)){
				if(!boundAtoms.contains(a) && hasCollided(a)){
					colition = true;
				
					if(canBind(a)){
						bind(a);
						System.out.println("bind");
					}
					else{
						if(Calculations.senterAvstandDouble(this, a)/
								((this.vdwRadius+a.getVdwRadius())/scale) < min &&
								Calculations.senterAvstandDouble(this, a) <
									((this.vdwRadius+a.getVdwRadius()))/scale){
							min = Calculations.senterAvstandDouble(this, a)/((this.vdwRadius+a.getVdwRadius())/scale);
							that = a;
							}
						}
				}
			}
			if(!colition){
//				System.out.println("Random");
				moveRandomly();
			}
			else if(that != null){
				if(!this.boundAtoms.contains(that)
						&& this.isBound() && that.isBound() && compareElectronAffinity(that)){
					System.out.println("bind");
					bind(that);
				}
				
				else {
//					System.out.println("Colition away");
					moveAway(that);
				}
			}
			
			else{
//				System.out.println("With colition Random");
				moveRandomly();
			}
		}
//		ElectronAffinityAction();
		checkBorder();
		moleculeBelowMinMaxDist = false;
		colition = false;
	}
	public void updateOldPositions() {
		lastXPos = xPos;
		lastYPos = yPos;
		lastZPos = zPos;
	}
	public void checkBorder(){
		if(xPos < 110 ){
			xPos +=2;
		}
		else if(xPos > 490){
			xPos -=2;
		}
		else if(yPos < 110){
			yPos +=2;
		}
		else if(yPos > 295){
			yPos -=2;
		}
		else if(zPos < 110){
			zPos +=2;
		}
		else if(zPos > 490){
			zPos -=2;
		}
		
	}
	/**
	 * Behavior for atoms that are bound and the distance between two atoms
	 * in the bound atom is less or greater than min/max.
	 **/
	public void CheckMoleculeMinMaxBorder(){
		
		if(isBound()){
			double rangeMin = 0;
			double rangeMax = 0;
			double max = 0;
			double min = 100000000;
			atom thatMin = null;
			atom thatMax = null;
			for(atom a : boundAtoms){
				double currentDistance = Calculations.senterAvstandDouble(this, a);
				double covalentDistance = (this.covalentRadius+a.covalentRadius)/scale;
//				System.out.println("atom størrelse: "+this.vdwRadius/scale);
//				System.out.println("Faktisk avstand: "+Calculations.senterAvstand(this, a));
//				System.out.println("Minimum avstand: "+(int)((this.covalentRadius+a.covalentRadius)/1.2)/scale);
//				System.out.println("Maximum avstand: "+(int)((this.covalentRadius+a.covalentRadius)*1.2)/scale);
				if(currentDistance/
						(covalentDistance) < min &&
						currentDistance <
							((this.covalentRadius+a.covalentRadius)/1.2)/scale){
					//Check which ratio is least, of all.
					min = currentDistance/covalentDistance;
					thatMin = a;
					moleculeBelowMinMaxDist = true;
				}
				else if(currentDistance/
						covalentDistance > max && 
						currentDistance > 
							((this.covalentRadius+a.covalentRadius)*1.2)/scale ){
					//Check which ratio is highest, of all.
					max = currentDistance/covalentDistance;
					thatMax = a;
					moleculeBelowMinMaxDist = true;
				}
			}
			if(moleculeBelowMinMaxDist){
				if(thatMin != null){
//					System.out.println("min "+thatMin);
					rangeMin = (((this.covalentRadius+thatMin.covalentRadius)/1.2)/scale )
							-Calculations.senterAvstandDouble(this, thatMin);
					if(thatMax != null){
						rangeMax = Calculations.senterAvstandDouble(this, thatMax) 
								- (((this.covalentRadius+thatMax.covalentRadius)*1.2)/scale );
						if(rangeMax > rangeMin)
							moveTowards(thatMax);
						else
							moveAway(thatMin);
						
					}
					else
						moveAway(thatMin);
				}
				else{
//					System.out.println("max "+thatMax);
						moveTowards(thatMax);
				}
				
			}
		}
	}
	
	public boolean hasCollided(atom that){
		if(this == that) return false;
		return Calculations.senterAvstand(this,that) <= (this.getVdwRadius()+that.getVdwRadius())/scale;
	}
	public void moveAway(atom that){
		boolean xg = Calculations.xRetning(this, that) > 0;
		boolean yg = Calculations.yRetning(this, that) > 0;
		boolean zg = Calculations.zRetning(this, that) > 0;
//		if(that.getBindNumber() <= 1){
			if(xg)
				xPos = xPos + 1;
			else
				xPos = xPos - 1;
			if(yg)
				yPos = yPos + 1;
			else
				yPos = yPos - 1;
			if(zg)
				zPos = zPos + 1;
			else
				zPos = zPos - 1;
		
	}
	public void moveTowards(atom that){
		boolean xg = Calculations.xRetning(this, that) > 0;
		boolean yg = Calculations.yRetning(this, that) > 0;
		boolean zg = Calculations.zRetning(this, that) > 0;
		
		if(xg)
			xPos = xPos - 1;
		else
			xPos = xPos + 1;
		if(yg)
			yPos = yPos - 1;
		else
			yPos = yPos + 1;
		if(zg)
			zPos = zPos - 1;
		else
			zPos = zPos + 1;
		
	
	}
	public void moveRandomly(){
		xPos = xPos +(random.nextInt(3)-1);
		yPos = yPos +(random.nextInt(3)-1);
		zPos = zPos +(random.nextInt(3)-1);
	}
	
	public int getVdwRadius() {
		return this.vdwRadius;
	}
	public int getElectronAffinity() {
		return electronAffinity;
	}
	public void setElectronAffinity(int electronAffinity) {
		this.electronAffinity = electronAffinity;
	}
	public boolean compareElectronAffinity(atom that) {
		if(this.electronAffinity - that.electronAffinity > 3){
			if(this.getName().equals("hydrogen")){
				if(this.getAffinityElectrons().size() == 0 &&  that.getAffinityElectrons().size() < 2){
					this.getAffinityElectrons().add(that);
					that.getAffinityElectrons().add(this);
					return true;
				}
				else if(this.getAffinityElectrons().size() < 2 &&  that.getAffinityElectrons().size() == 0){
					this.getAffinityElectrons().add(that);
					that.getAffinityElectrons().add(this);
					return true;
				}
				
			}
		}
		return false;
	}
	public void ElectronAffinityAction() {
		if(!getAffinityElectrons().isEmpty()){
			atom currentAtom = null;
			for(atom a: getAffinityElectrons()){
				if(random.nextInt(5) == 4){
					currentAtom = a;
				}
			}
			if(currentAtom != null){
				getAffinityElectrons().remove(currentAtom);
				currentAtom.getAffinityElectrons().remove(this);
				this.unBind(currentAtom);
			}
		}
		
	}
	public void setVdwRadius(int vdwRadius) {
		this.vdwRadius = vdwRadius;
	}
	public ArrayList<atom> getBoundAtoms() {
		return boundAtoms;
	}
	public void setBoundAtoms(ArrayList<atom> boundAtoms) {
		this.boundAtoms = boundAtoms;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getCharName() {
		return charName;
	}

	public void setCharName(char charName) {
		this.charName = charName;
	}

	public PhongMaterial getMaterialColor() {
		return materialColor;
	}
	public void setMaterialColor(PhongMaterial materialColor) {
		this.materialColor = materialColor;
	}
	public void setBound(boolean bound) {
		this.bound = bound;
	}
	public int getNumberOfValenceElectrons() {
		return NumberOfValenceElectrons;
	}
	public void setNumberOfValenceElectrons(int numberOfValenceElectrons) {
		NumberOfValenceElectrons = numberOfValenceElectrons;
	}
	public int getBindNumber() {
		return bindNumber;
	}
	public void setBindNumber(int bindNumber) {
		this.bindNumber = bindNumber;
	}
	public int getMaxBindNumber() {
		return maxBindNumber;
	}
	public void setMaxBindNumber(int maxBindNumber) {
		this.maxBindNumber = maxBindNumber;
	}
	public ArrayList<atom> getAffinityElectrons() {
		return affinityElectrons;
	}
	public void setAffinityElectrons(ArrayList<atom> affinityElectrons) {
		this.affinityElectrons = affinityElectrons;
	}
	public int getLastXPos() {
		return lastXPos;
	}
	public void setLastXPos(int lastXPos) {
		this.lastXPos = lastXPos;
	}
	public int getLastYPos() {
		return lastYPos;
	}
	public void setLastYPos(int lastYPos) {
		this.lastYPos = lastYPos;
	}
	public int getLastZPos() {
		return lastZPos;
	}
	public void setLastZPos(int lastZPos) {
		this.lastZPos = lastZPos;
	}
	
}
