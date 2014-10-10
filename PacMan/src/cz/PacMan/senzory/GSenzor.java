package cz.PacMan.senzory;

import lejos.nxt.SensorPort;
import lejos.nxt.addon.GyroSensor;

public class GSenzor {
	private static GyroSensor senzor = new GyroSensor(SensorPort.S3);
	
	public static int getAngle(){
		return senzor.readValue();
	}
	
	public static float getVelocity(){
		return senzor.getAngularVelocity();
	}
}
