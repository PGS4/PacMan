package cz.PacMan.senzory;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;

public class LSenzor {
	private static LightSensor senzor = new LightSensor(SensorPort.S2);
	

	public static void zapnout() {
		senzor.setFloodlight(true);
	}
	public static void Lkalibrace() {
		senzor.calibrateLow();
		
	}

	public static void Hkalibrace() {
		senzor.calibrateHigh();
	}

	public static int getLight() {
		return senzor.getLightValue();
	}
}