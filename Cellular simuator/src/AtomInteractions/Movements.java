package AtomInteractions;

import java.util.Random;

import Atoms.atom;
import Math.Calculations;

public class Movements {
	static Random random = new Random();
	
	public static void moveAway(atom one,atom two){
		boolean xg = Calculations.xRetning(one, two) > 0;
		boolean yg = Calculations.yRetning(one, two) > 0;
		boolean zg = Calculations.zRetning(one, two) > 0;
//		if(that.getBindNumber() <= 1){
			if(xg)
				one.setxPos(one.getxPos() + 1);
			else
				one.setxPos(one.getxPos() - 1);
			if(yg)
				one.setyPos(one.getyPos() + 1);
			else
				one.setyPos(one.getyPos() - 1);
			if(zg)
				one.setzPos(one.getzPos() + 1);
			else
				one.setzPos(one.getzPos() - 1);
		
	}
	public static void moveTowards(atom one,atom two){
		boolean xg = Calculations.xRetning(one, two) > 0;
		boolean yg = Calculations.yRetning(one, two) > 0;
		boolean zg = Calculations.zRetning(one, two) > 0;
//		if(that.getBindNumber() <= 1){
			if(xg)
				one.setxPos(one.getxPos() - 1);
			else
				one.setxPos(one.getxPos() + 1);
			if(yg)
				one.setyPos(one.getyPos() - 1);
			else
				one.setyPos(one.getyPos() + 1);
			if(zg)
				one.setzPos(one.getzPos() - 1);
			else
				one.setzPos(one.getzPos() + 1);
		
	
	}
	public static void moveRandomly(atom one){
		one.setxPos(one.getxPos() +(random.nextInt(3)-1));
		one.setyPos(one.getyPos() +(random.nextInt(3)-1));
		one.setzPos(one.getzPos() +(random.nextInt(3)-1));
	}
}
