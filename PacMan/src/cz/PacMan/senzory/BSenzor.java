package cz.PacMan.senzory;

import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;

public class BSenzor {
	private static TouchSensor senzorL = new TouchSensor(SensorPort.S3);
	private static TouchSensor senzorR = new TouchSensor(SensorPort.S4);

	public static boolean isPressed() {
		if (senzorL.isPressed() || senzorR.isPressed()) {
			return true;
		}
		return false;
	}
}