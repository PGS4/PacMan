package cz.PacMan.main;

import cz.PacMan.senzory.LSenzor;
import lejos.nxt.Button;

public class PacMan {
	private static int LSenzorHodnota;
	private static int count = 0;

	public static void main(String[] args) {
		// Model model = new Model();
		// model.newMap();
		
		System.out.println("Leve tlacitko: \n" + "Svetelny senzor");
		while (!Button.ESCAPE.isDown()) {
			if (Button.LEFT.isDown()&&count == 0) {
				count = 1;
				LSenzorHodnota = LSenzor.getLight();
				System.out.println("Hodnota je: " + LSenzorHodnota);
				System.out.println("Stiskni ENTER pro start!");
			}
			if (Button.ENTER.isDown()&&count == 1) {
				Controller control = new Controller();
				control.start(LSenzorHodnota);
			}
		}
	}
}
