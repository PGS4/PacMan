package cz.PacMan.main;

import cz.PacMan.senzory.BSenzor;
import cz.PacMan.senzory.LSenzor;
import lejos.nxt.Button;

public class PacMan {
	private static int v1,v2;
	
	public static void main(String[] args) {
		/*
		 * while(!Button.ESCAPE.isDown()){
		 * System.out.println(LSenzor.getLight()); }
		*/
		LSenzor.zapnout();
		System.out.println("Leve tlacitko: \n" + "podlaha");
		while (!Button.LEFT.isDown()) {
		}
		v1 = LSenzor.getNormLight();
		System.out.println(LSenzor.getLight());
		System.out.println("Prave tlacitko: \n" + "bod");
		while (!Button.RIGHT.isDown()) {
		}
		v2 = LSenzor.getNormLight();
		System.out.println(LSenzor.getLight());
		System.out.println("Stiskni ENTER pro start!");
		while (!Button.ENTER.isDown()) {
		}
		LSenzor.kalibrovat(v1, v2);
		Controller control = new Controller(v1, v2);
		Thread c = new Thread(control);
		c.setDaemon(true);
		c.start();
		while (!Button.ESCAPE.isDown()) {
		}
	}
}