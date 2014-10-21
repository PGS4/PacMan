package cz.PacMan.senzory;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;

public class LSenzor {
	private static LightSensor senzor = new LightSensor(SensorPort.S2);
	

	public static void zapnout() {
		senzor.setFloodlight(true);
	}
	public static void Lkalibrace() {
		senzor.setLow(senzor.getLightValue());
		
	}

	public static void Hkalibrace() {
		senzor.setHigh(senzor.getLightValue());
	}

	public static int getLight() {
		return senzor.getLightValue();
	}
}