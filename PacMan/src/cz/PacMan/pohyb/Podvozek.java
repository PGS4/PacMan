package cz.PacMan.pohyb;

import lejos.nxt.Motor;

public class Podvozek {
	public static void dozadu(int rychlost) {
		Motor.A.forward();
		Motor.B.forward();
		Motor.A.setSpeed(rychlost);
		Motor.B.setSpeed(rychlost);
	}

	public static void stop() {
		Motor.B.stop(true);
		Motor.A.stop(true);
	}

	public static void doprava() {
		Motor.A.backward();
		Motor.B.forward();
		Motor.A.setSpeed(100);
		Motor.B.setSpeed(100);
	}

	public static void doleva() {
		Motor.A.forward();
		Motor.B.backward();
		Motor.A.setSpeed(100);
		Motor.B.setSpeed(100);
	}

	public static void dopredu(int rychlost) {
		Motor.A.backward();
		Motor.B.backward();
		Motor.A.setSpeed(rychlost);
		Motor.B.setSpeed(rychlost);
	}
	
	public static void jenVpravo(int rychlost) {
		Motor.A.backward();
		Motor.A.setSpeed(rychlost);
		Motor.B.stop();
	}
	
	public static void jenVlevo(int rychlost) {
		
			Motor.B.backward();
			Motor.B.setSpeed(rychlost);
			Motor.A.stop();
		}
	
}