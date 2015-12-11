package Atoms;

import java.util.ArrayList;
import java.util.Random;

import Math.Calculations;
import Runner.Simulator;

public class atom {
	int isotope;
	protected String name;
	char charName;
	String color;
	int weight;
	int covalentRadius;
	int vdwRadius;
	int NumberOfValenceElectrons;
	private int charge;
	Random random = new Random();
	String bildeNavn;
	boolean bound = false;
	int bindNumber;
	int maxBindNumber;
	int xPos;
	int yPos;
	boolean moleculeBelowMinDist;
	ArrayList<atom> boundAtoms = new ArrayList<atom>();
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

	public void updateAtom(Simulator simulator){
//		int min = Integer.MAX_VALUE;
		boundAtomBehavior();
		for(atom a : simulator.getAtoms()){
			if(hasCollided(a)){
				if(canBind(a)){
					bind(a);	
				}
				else
					moveAway(a);
			}

		}
		if(bound){
			if(moleculeBelowMinDist){
				
			}
		}
		else{
			xPos = xPos +(random.nextInt(3)-1);
			yPos = yPos +(random.nextInt(3)-1);
			checkBorder();
		}
		moleculeBelowMinDist = false;

	}
	public void checkBorder(){
		if(xPos < 110){
			xPos +=1;
		}
		else if(xPos > 490){
			xPos -=1;
		}
		else if(yPos < 110){
			yPos +=1;
		}
		else if(xPos > 490){
			yPos -=1;
		}
	}
	
	public void boundAtomBehavior(){
		int i = 0;
		if(bound){
			if(boundAtoms.size() == 1){
				pairMoleculeBehavior(0);
			}
			else if(boundAtoms.size() == 2){
				tripletMoleculeBehavior();
			}
			else if(boundAtoms.size() == 3){
				quadrupletMoleculeBehavior();
			}
			else if(boundAtoms.size() == 4){
				pentupletMoleculeBehavior();
			}
			
		}
		
	}
	
	
	public void pairMoleculeBehavior(int numb){
		//g = greater , l = less, xg = x is greater on this. 
		atom that = boundAtoms.get(numb);
//		System.out.println(Calculations.xRetning(this, that ));
//		System.out.println(Calculations.yRetning(this, that));
		boolean xg = Calculations.xRetning(this, that) > 13;
		boolean xl = Calculations.xRetning(this, that) < -13;
		boolean yl = Calculations.yRetning(this, that) > 13;
		boolean yg = Calculations.yRetning(this, that) < -13;
		//If they are the same place.
		if (!xg && !xl && !yg && !yl) checkMoleculeBorder(that);
		
		else{
			//This atom to the right
			if(xg) 
				xPos = xPos -1;
				
			//This atom to the left of the other
			else if(xl)
				xPos = xPos + 1;
				
			else 
				xPos = xPos +(random.nextInt(3)-1);
				
			//This atom over the other
			if(yg)
				yPos = yPos + 1;
			
			//This atom under the other
			else if (yl)
				yPos = yPos - 1;
			
			else	
				yPos = yPos +(random.nextInt(3)-1);
			}
	}
	public void tripletMoleculeBehavior(){
		atom that0 = boundAtoms.get(0);
		atom that1 = boundAtoms.get(1);
		
		int dist0 = Calculations.senterAvstand(this,that0);
		int dist1 = Calculations.senterAvstand(this,that1);
		
		if(dist0 <= dist1) 
			pairMoleculeBehavior(0);
		else
			pairMoleculeBehavior(1);
	}
	
	public void quadrupletMoleculeBehavior(){
		atom that0 = boundAtoms.get(0);
		atom that1 = boundAtoms.get(1);
		atom that2 = boundAtoms.get(2);
		
		int dist0 = Calculations.senterAvstand(this,that0);
		int dist1 = Calculations.senterAvstand(this,that1);
		int dist2 = Calculations.senterAvstand(this,that2);
		if(dist0 <= dist1 && dist0 <= dist2) 
			pairMoleculeBehavior(0);
		else if(dist1 <= dist0 && dist1 <= dist2) 
			pairMoleculeBehavior(1);
		else
			pairMoleculeBehavior(2);
	}
	public void pentupletMoleculeBehavior(){
		atom that0 = boundAtoms.get(0);
		atom that1 = boundAtoms.get(1);
		atom that2 = boundAtoms.get(2);
		atom that3 = boundAtoms.get(3);
		
		int dist0 = Calculations.senterAvstand(this,that0);
		int dist1 = Calculations.senterAvstand(this,that1);
		int dist2 = Calculations.senterAvstand(this,that2);
		int dist3 = Calculations.senterAvstand(this,that3);
		if(dist0 <= dist1 && dist0 <= dist2) 
			pairMoleculeBehavior(0);
		else if(dist1 <= dist0 && dist1 <= dist2) 
			pairMoleculeBehavior(1);
		else if(dist2 <= dist0 && dist2 <= dist1 && dist2 <= dist3) 
			pairMoleculeBehavior(1);
		else
			pairMoleculeBehavior(3);
	}
	
	public void checkMoleculeBorder(atom that){
		if(Calculations.senterAvstand(this, that) < 5){
			moveAway(that);
			moleculeBelowMinDist = true;
			}
		else{
			xPos = xPos +(random.nextInt(3)-1);
			yPos = yPos +(random.nextInt(3)-1);
//			System.out.println("molecule");
		}
	}
	public boolean hasCollided(atom that){
		if(this == that) return false;
		return Calculations.senterAvstand(this,that) < 13;
	}
	public void moveAway(atom that){
		xPos = xPos + (Calculations.xRetning(this, that));
		yPos = yPos + (Calculations.yRetning(this, that));
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
	
}
