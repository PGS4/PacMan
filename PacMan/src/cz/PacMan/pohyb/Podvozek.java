package cz.PacMan.pohyb;
import lejos.nxt.Motor;


public class Podvozek {
	public static void podvozekDopredu(int rychlost){
		Motor.A.forward();
		Motor.B.forward();
		Motor.A.setSpeed(rychlost);
		Motor.B.setSpeed(rychlost);
	}
	public static void podvozekStop(){
		Motor.A.stop();
		Motor.B.stop();
	}
	public static void doleva(int rychlost){
		Motor.A.forward();
		Motor.B.forward();
		Motor.A.setSpeed(rychlost);
		Motor.B.setSpeed(0);
	}
	public static void doprava(int rychlost){
		Motor.A.forward();
		Motor.B.forward();
		Motor.A.setSpeed(0);
		Motor.B.setSpeed(rychlost);
	}
	public static void podvozekDozadu(int rychlost){
		Motor.A.backward();
		Motor.B.backward();
		Motor.A.setSpeed(rychlost);
		Motor.B.setSpeed(rychlost);
	}
	public static void ramenoDolu(int rychlost, int uhel){
		Motor.C.setSpeed(rychlost);
		Motor.C.rotate(uhel);
		Motor.C.stop();
	}
	public static void ramenoNahoru(int rychlost, int uhel){
		Motor.C.setSpeed(-rychlost);
		Motor.C.rotate(-uhel);
		Motor.C.stop();
	}
}