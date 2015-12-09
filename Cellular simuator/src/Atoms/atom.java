package Atoms;

public class atom {
	int isotope;
	String name;
	char charName;
	String color;
	private int weight;
	int covalentRadius;
	int vdwRadius;
	int NumberOfValenceElectrons;
	private int charge;
	String bildeNavn;
	

	int xPos;
	int yPos;
	/*
	 * Checks if the atom can bind another atom
	 */
	public boolean canBind(atom that){
		if(this.getCharge() + that.getCharge() == 0){
			return true;
		}
		return false;
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

	public void updateAtom(){
		xPos = xPos + 1;
		yPos = yPos + 1;
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
