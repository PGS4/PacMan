package cz.PacMan.senzory;

import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;

public class BSenzor {
	private static TouchSensor senzor  = new TouchSensor(SensorPort.S3);
	private static TouchSensor senzor2 = new TouchSensor(SensorPort.S4);
	public static boolean isPressed(){
		if(senzor.isPressed()||senzor2.isPressed()){
			return true;
		}
		return false;
	}
}
