package Atoms;

import java.util.ArrayList;

import LowerObjects.Bond;
import Math.VdwCalculations;
import javafx.scene.paint.PhongMaterial;

public class atom {
	// Standard constants
	int isotope;
	int weight;
	private int covalentRadius;
	int vdwRadius;
	int electronAffinity;
	public String type = "atom";
	private static int scale = 10;

	char charName;

	String color;
	protected String name;

	// Arraylists for bound and affinity electrons
	private ArrayList<atom> affinityElectrons = new ArrayList<atom>(2);
	ArrayList<atom> boundAtoms = new ArrayList<atom>();

	// Standard variables
	private boolean bound = false;
	private int NumberOfValenceElectrons;
	private int charge;
	private int bindNumber;
	private int maxBindNumber;
	// Position variables
	int xPos;
	int yPos;
	int zPos;

	private int lastXPos;
	private int lastYPos;
	private int lastZPos;
	// private int dv;

	// Booleans
	private boolean colition = false;
	// Extra objects

	PhongMaterial materialColor;

	/*
	 * Checks if the atom can bind another atom
	 */
	public boolean canBind(atom that) {
		return Bond.canBind(this, that);
	}

	public void bind(atom that) {
		Bond.BindSingle(this, that);
	}

	public void unBind(atom that) {
		Bond.BindSingleRemove(this, that);
	}

	public boolean isBound() {
		return bound;
	}

	@Override
	public String toString() {
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

	public void updateOldPositions() {
		lastXPos = xPos;
		lastYPos = yPos;
		lastZPos = zPos;
	}

	public boolean hasCollided(atom that) {
		if (this == that)
			return false;
		return VdwCalculations.isCurrentDistanceLessWdv(this,that);
	}

	public void setStartPositions(int xPos, int yPos, int zPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.zPos = zPos;
		updateOldPositions();
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

	public int getCovalentRadius() {
		return covalentRadius;
	}

	public void setCovalentRadius(int covalentRadius) {
		this.covalentRadius = covalentRadius;
	}

	public boolean isColided() {
		return colition;
	}

	public void setColition(boolean colition) {
		this.colition = colition;
	}

	public static int getScale() {
		return scale;
	}

	public static void setScale(int scale) {
		atom.scale = scale;
	}

}
