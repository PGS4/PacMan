package cz.PacMan.pohyb;

import lejos.nxt.Motor;

public class Podvozek {
	public static void podvozekDozadu(int rychlost) {
		Motor.A.forward();
		Motor.B.forward();
		Motor.A.setSpeed(rychlost);
		Motor.B.setSpeed(rychlost);
	}

	public static void podvozekStop() {
		Motor.B.stop(true);
		Motor.A.stop(true);
	}

	public static void doprava() {
		Motor.A.backward();
		Motor.B.forward();
		Motor.A.setSpeed(200);
		Motor.B.setSpeed(200);
	}

	public static void doleva() {
		Motor.A.forward();
		Motor.B.backward();
		Motor.A.setSpeed(200);
		Motor.B.setSpeed(200);
	}

	public static void podvozekDopredu(int rychlost) {
		Motor.A.backward();
		Motor.B.backward();
		Motor.A.setSpeed(rychlost);
		Motor.B.setSpeed(rychlost);
	}
}
