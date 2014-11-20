package cz.PacMan.pohyb;

import cz.PacMan.main.Controller;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;

public class Podvozek {
	private static int direction = 0;
	private static int sDirection = 0;
	private static DifferentialPilot dp = new DifferentialPilot(4.32, 4.302, 16.9, Motor.A, Motor.B, false);

	public static void dopredu(double regulator) {
		/*Motor.A.setAcceleration(1800);
		Motor.B.setAcceleration(1800);
		Motor.A.setSpeed(500);
		Motor.B.setSpeed((int) (502+regulator));
		Motor.A.forward();
		Motor.B.forward();*/
		dp.setTravelSpeed(20);
		dp.forward();
	}

	public static void stop() {
		Motor.B.stop(true);
		Motor.A.stop(true);
	}

	public static void doleva(double regulator) {
		if(direction == 0){
			direction = 3;
		}else {
			direction -= 1;
		}
		/*Motor.A.setAcceleration(800);
		Motor.B.setAcceleration(800);
		Motor.A.rotate((int) (-332+regulator), true);
		Motor.B.rotate((int) (331+regulator), true);*/
		dp.setRotateSpeed(100);
		dp.rotate(85.8);
	}

	public static void doprava(double regulator) {
		if(direction == 3){
			direction = 0;
		}else {
			direction += 1;
		}
		/*Motor.A.setAcceleration(800);
		Motor.B.setAcceleration(800);
		Motor.A.rotate((int) (339-regulator), true);
		Motor.B.rotate((int) (-338-regulator),true);*/
		dp.setRotateSpeed(100);
		dp.rotate(-86);
	}

	public static void dozadu() {
		/*Motor.A.setAcceleration(2500);
		Motor.B.setAcceleration(2500);
		Motor.A.setSpeed(500);
		Motor.B.setSpeed(502);
		Motor.A.backward();
		Motor.B.backward();*/
		dp.setTravelSpeed(19);
		dp.backward();
	}
	
	public static void SDoprava(){
		if(sDirection == 0){
			sDirection =1;
			Motor.C.rotate(-180);
		}
	}
	
	public static void SDoleva(){
		if(sDirection == 1){
			sDirection =0;
			Motor.C.rotate(180);
		}
	}

	public static int getDirection(){
		return direction;
	}
	
	public static int getSDirection(){
		return sDirection;
	}
}