package cz.PacMan.main;

import cz.PacMan.senzory.LSenzor;
import lejos.nxt.Button;

public class PacMan {
	private static int LSenzorHodnota;

	public static void main(String[] args) {
		// Model model = new Model();
		// model.newMap();
		while (!Button.ESCAPE.isDown()) {
			System.out.println("Leve tlacitko: \n" + "Svetelny senzor");
			if (Button.LEFT.isDown()) {
				LSenzorHodnota = LSenzor.getLight();
				System.out.println("Hodnota je: " + LSenzorHodnota);
				System.out.println("Stiskni ENTER pro start!");
			}
			if (Button.ENTER.isDown()) {
				Controller control = new Controller();
				control.start(LSenzorHodnota);
			}
		}
	}
}
