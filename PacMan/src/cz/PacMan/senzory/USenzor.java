package cz.PacMan.senzory;

import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

public class USenzor {
	private static UltrasonicSensor senzor = new UltrasonicSensor(SensorPort.S1);
	
	public static int getDistance(){
		return senzor.getDistance();
	}
}
