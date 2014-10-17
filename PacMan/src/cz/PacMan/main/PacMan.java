package cz.PacMan.main;

import cz.PacMan.senzory.BSenzor;
import cz.PacMan.senzory.LSenzor;
import lejos.nxt.Button;

public class PacMan {

	public static void main(String[] args) {
		// Model model = new Model();
		// model.newMap();
		/*while(!Button.ESCAPE.isDown()){
		System.out.println(LSenzor.getLight());
		}*/
		
		System.out.println("Leve tlacitko: \n" + "podlaha");
			while (!Button.LEFT.isDown()) {
			}
			LSenzor.Lkalibrace();
			System.out.println(LSenzor.getLight());
			System.out.println("Prave tlacitko: \n" + "bod");
			while (!Button.RIGHT.isDown()) {
			}
			LSenzor.Hkalibrace();
			System.out.println(LSenzor.getLight());
			System.out.println("Stiskni ENTER pro start!");
			while (!Button.ENTER.isDown()) {
			}
			Controller control = new Controller();
			Thread c = new Thread(control);
			c.setDaemon(true);
			c.start();
			while(!Button.ESCAPE.isDown()&&!BSenzor.isPressed()){
			}
	}
}
