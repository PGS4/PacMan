package cz.PacMan.senzory;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;

public class LSenzor {
	private static LightSensor senzor = new LightSensor(SensorPort.S2);

	public static void zapnout() {
		senzor.setFloodlight(true);
	}
	
	public static void lKalibrace(int value) {
		senzor.setLow(value);
		
	}

	public static void hKalibrace(int value) {
		senzor.setHigh(value);
	}

	public static int getLight() {
		return senzor.getLightValue();
	}
	
	public static void kalibrovat(int v1, int v2) {
		if(v1<v2){
			lKalibrace(v1);
			hKalibrace(v2);
		}else{
			lKalibrace(v2);
			hKalibrace(v1);
		}
	}
	
	public static int getNormLight(){
		return senzor.getNormalizedLightValue();
	}
}