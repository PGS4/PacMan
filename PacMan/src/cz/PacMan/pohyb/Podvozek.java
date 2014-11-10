package cz.PacMan.pohyb;

import lejos.nxt.Motor;

public class Podvozek {
	private static int direction = 0;
	private static int sDirection = 0;

	public static void dopredu() {
		Motor.A.setAcceleration(3000);
		Motor.B.setAcceleration(3000);
		Motor.A.setSpeed(500);
		Motor.B.setSpeed(500);
		Motor.A.forward();
		Motor.B.forward();
	}

	public static void stop() {
		Motor.B.stop(true);
		Motor.A.stop(true);
	}

	public static void doleva() {
		if(direction == 0){
			direction = 3;
		}else {
			direction -= 1;
		}
		Motor.A.setAcceleration(800);
		Motor.B.setAcceleration(800);
		Motor.A.rotate(-337, true);
		Motor.B.rotate(336, true);
	}

	public static void doprava() {
		if(direction == 3){
			direction = 0;
		}else {
			direction += 1;
		}
		Motor.A.setAcceleration(800);
		Motor.B.setAcceleration(800);
		Motor.A.rotate(336, true);
		Motor.B.rotate(-337, true);
	}

	public static void dozadu() {
		Motor.A.setAcceleration(3000);
		Motor.B.setAcceleration(3000);
		Motor.A.setSpeed(500);
		Motor.B.setSpeed(500);
		Motor.A.backward();
		Motor.B.backward();
	}
	
	public static void SDoprava(){
		if(sDirection == 0){
			sDirection =1;
			Motor.C.rotate(-190);
		}
	}
	
	public static void SDoleva(){
		if(sDirection == 1){
			sDirection =0;
			Motor.C.rotate(190);
		}
	}

	public static int getDirection(){
		return direction;
	}
	
	public static int getSDirection(){
		return sDirection;
	}
	
}