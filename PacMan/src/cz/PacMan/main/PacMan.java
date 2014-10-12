package cz.PacMan.main;

import java.util.ArrayList;

import lejos.nxt.Button;
import cz.PacMan.pohyb.Podvozek;
import cz.PacMan.senzory.GSenzor;
import cz.PacMan.senzory.LSenzor;
import cz.PacMan.senzory.USenzor;

public class PacMan {
	public static void main(String[] args) {
		Model model = new Model();
		ArrayList<Integer> sourky = new ArrayList<Integer>();
		//model.levels();
		//model.initLevel();
		System.out.println(model.getMap().get(sourky));
		//Podvozek.podvozekDopredu(900);
		//Button.waitForAnyPress();
		/*while(Button.ESCAPE.isDown() != true){
			System.out.println(GSenzor.getAngle());
		}*/
		//Podvozek.doleva(180);
		model.newMap();
	}
}
