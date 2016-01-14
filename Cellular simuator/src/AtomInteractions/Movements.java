package AtomInteractions;

import java.util.Random;

import Atoms.atom;
import Math.Calculations;

public class Movements {
	static Random random = new Random();

	public static void moveAway(atom one, atom two) {
//		System.out.println("away");
		boolean xg = Calculations.xRetning(one, two) > 0;
		boolean yg = Calculations.yRetning(one, two) > 0;
		boolean zg = Calculations.zRetning(one, two) > 0;
		System.out.println(one.getxPos()+" "+one.getyPos()+" "+one.getzPos());
		if (xg)
			one.setxPos(one.getxPos() + 1);
		else
			one.setxPos(one.getxPos() - 1);
		if (yg)
			one.setyPos(one.getyPos() + 1);
		else
			one.setyPos(one.getyPos() - 1);
		if (zg)
			one.setzPos(one.getzPos() + 1);
		else
			one.setzPos(one.getzPos() - 1);
		System.out.println(one.getxPos()+" "+one.getyPos()+" "+one.getzPos());

	}

	public static void moveTowards(atom one, atom two) {
//		System.out.println("towards");
		boolean xg = Calculations.xRetning(one, two) > 0;
		boolean yg = Calculations.yRetning(one, two) > 0;
		boolean zg = Calculations.zRetning(one, two) > 0;
		
		if (xg)
			one.setxPos(one.getxPos() - 1);
		else
			one.setxPos(one.getxPos() + 1);
		if (yg)
			one.setyPos(one.getyPos() - 1);
		else
			one.setyPos(one.getyPos() + 1);
		if (zg)
			one.setzPos(one.getzPos() - 1);
		else
			one.setzPos(one.getzPos() + 1);

	}

	public static void moveRandomly(atom one) {
//		System.out.println("random");
		one.setxPos(one.getxPos() + (random.nextInt(3) - 1));
		one.setyPos(one.getyPos() + (random.nextInt(3) - 1));
		one.setzPos(one.getzPos() + (random.nextInt(3) - 1));
	}
	
	public static void isAtomOutsideBounds(atom one){
		int xPos = one.getxPos();
		int yPos = one.getyPos();
		int zPos = one.getzPos();
		
		if (xPos < 110) {
			one.setxPos(xPos+2);
		} else if (xPos > 490) {
			one.setxPos(xPos-2);
		} else if (yPos < 110) {
			one.setyPos(yPos+2);
		} else if (yPos > 295) {
			one.setyPos(yPos-2);
		} else if (zPos < 110) {
			one.setzPos(zPos+2);
		} else if (zPos > 490) {
			one.setzPos(zPos-2);
		}
		
	}
}
