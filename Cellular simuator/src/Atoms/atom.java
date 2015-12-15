package Atoms;

import java.util.ArrayList;
import java.util.Random;

import Math.Calculations;
import Runner.Simulator;
import javafx.scene.paint.PhongMaterial;

public class atom {
	int isotope;
	protected String name;
	char charName;
	String color;
	int weight;
	int covalentRadius;
	int vdwRadius;
	int NumberOfValenceElectrons;
	int electronAffinity;
	ArrayList<atom> affinityElectrons = new ArrayList<atom>(2);
	private int charge;
	Random random = new Random();
	String bildeNavn;
	PhongMaterial materialColor;
	

	boolean bound = false;
	int bindNumber;
	int maxBindNumber;
	int xPos;
	int yPos;
	int zPos;
	int dv;

	boolean moleculeBelowMinMaxDist = false;
	ArrayList<atom> boundAtoms = new ArrayList<atom>();
	boolean colition = false;
	/*
	 * Checks if the atom can bind another atom
	 */
	public boolean canBind(atom that){
		
		if(this.getCharge() + that.getCharge() == 0 || 
				this.getCharge() + that.getCharge() > this.getCharge() ||
				this.getCharge() - that.getCharge() == 0 ||
				this.boundAtoms.contains(that)){
			if(bindNumber < maxBindNumber && that.bindNumber < that.maxBindNumber)
				return true;
		}
		return false;
	}
	public void bind(atom that){
		bound = true;
		that.bound = true;
		
		bindNumber++;
		that.bindNumber++;
		
		boundAtoms.add(that);
		that.boundAtoms.add(this);
		
		NumberOfValenceElectrons++;
		that.NumberOfValenceElectrons++;
		
		int oldThatCharge = that.charge;
		this.charge += that.charge;
		that.charge = oldThatCharge + charge;
	}
	public void unBind(atom that){
		bound = false;
		that.bound = false;
		
		bindNumber--;
		that.bindNumber--;
		
		boundAtoms.remove(that);
		that.boundAtoms.remove(this);
		
		NumberOfValenceElectrons--;
		that.NumberOfValenceElectrons--;
		
		int oldThatCharge = that.charge;
		this.charge -= that.charge;
		that.charge = oldThatCharge - charge;
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
		
		CheckMoleculeMinMaxBorder();
		
		if(!moleculeBelowMinMaxDist){
			
			atom that = null;
			int min = 35;
			for(atom a : simulator.getAtoms()){
				if(!boundAtoms.contains(a) && hasCollided(a)){
					colition = true;
				
					if(canBind(a)){
						bind(a);
					}
					else{
						if(Calculations.senterAvstand(this, a) < min ){
							min = Calculations.senterAvstand(this, a);
							that = a;
							}
						}
				}
			}
			if(!colition){
				xPos = xPos +(random.nextInt(3)-1);
				yPos = yPos +(random.nextInt(3)-1);
				zPos = zPos +(random.nextInt(3)-1);
			}
			else if(that != null){
				if(compareElectronAffinity(that)
						&& this.isBound() && that.isBound() && !this.boundAtoms.contains(that)){
					moveTowards(that);
				}
				else
					moveAway(that);
			}
			
			else{
				xPos = xPos +(random.nextInt(3)-1);
				yPos = yPos +(random.nextInt(3)-1);
				zPos = zPos +(random.nextInt(3)-1);
			}
		}
		ElectronAffinityAction();
		checkBorder();
		moleculeBelowMinMaxDist = false;
		colition = false;

	}
	public void checkBorder(){
		if(xPos < 110){
			xPos +=2;
		}
		else if(xPos > 590){
			xPos -=2;
		}
		else if(yPos < 110){
			yPos +=2;
		}
		else if(yPos > 390){
			yPos -=2;
		}
		else if(zPos < 110){
			zPos +=2;
		}
		else if(zPos > 490){
			zPos -=2;
		}
		
	}

	public void CheckMoleculeMinMaxBorder(){
		
		if(bound){
			int rangeMin = 0;
			int rangeMax = 0;
			int max = 70;
			int min = 35;
			atom thatMin = null;
			atom thatMax = null;
			for(atom a : boundAtoms){
//				System.out.println(Calculations.senterAvstand(this, a));
				if(Calculations.senterAvstand(this, a) < min){
					min = Calculations.senterAvstand(this, a);
					thatMin = a;
					moleculeBelowMinMaxDist = true;
				}
				else if(Calculations.senterAvstand(this, a) > max){
					max = Calculations.senterAvstand(this, a);
					thatMax = a;
					moleculeBelowMinMaxDist = true;
				}
			}
			if(moleculeBelowMinMaxDist){
				if(thatMin != null){
//					System.out.println("min "+thatMin);
					rangeMin = 35-min;
					if(thatMax != null){
						rangeMax = max -70;
						if(rangeMax > rangeMin)
							moveTowards(thatMax);
						else
							moveAway(thatMin);
						
					}
					else
						moveAway(thatMin);
				}
				else if(thatMax != null){
//					System.out.println("max "+thatMax);
						moveTowards(thatMax);
				}
				
			}
		}
	}
	
	public boolean hasCollided(atom that){
		if(this == that) return false;
		return Calculations.senterAvstand(this,that) <= 50;
	}
	public void moveAway(atom that){
		boolean xg = Calculations.xRetning(this, that) > 0;
		boolean yg = Calculations.yRetning(this, that) > 0;
		boolean zg = Calculations.zRetning(this, that) > 0;
	
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
	
	public int getVdwRadius() {
		return vdwRadius;
	}
	public int getElectronAffinity() {
		return electronAffinity;
	}
	public void setElectronAffinity(int electronAffinity) {
		this.electronAffinity = electronAffinity;
	}
	public boolean compareElectronAffinity(atom that) {
		if(this.electronAffinity - that.electronAffinity > 3){
			if(this.affinityElectrons.size() < 2 &&  that.affinityElectrons.size() < 2){
				this.affinityElectrons.add(that);
				that.affinityElectrons.add(this);
				return true;
			}
		}
		return false;
	}
	public void ElectronAffinityAction() {
		if(!affinityElectrons.isEmpty()){
			atom currentAtom = null;
			for(atom a: affinityElectrons){
				if(random.nextInt(30) == 4){
					currentAtom = a;
				}
			}
			if(currentAtom != null)
				affinityElectrons.remove(currentAtom);
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

	public String getBildeNavn() {
		return bildeNavn;
	}

	public void setBildeNavn(String bildeNavn) {
		this.bildeNavn = bildeNavn;
	}
	public PhongMaterial getMaterialColor() {
		return materialColor;
	}
	public void setMaterialColor(PhongMaterial materialColor) {
		this.materialColor = materialColor;
	}
	
}
