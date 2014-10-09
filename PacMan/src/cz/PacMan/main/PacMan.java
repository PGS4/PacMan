package cz.PacMan.main;

import java.util.ArrayList;

import lejos.nxt.Button;
import cz.PacMan.pohyb.Podvozek;
import cz.PacMan.senzory.LSenzor;
import cz.PacMan.senzory.USenzor;

public class PacMan {
	public static void main(String[] args) {
		Model model = new Model();
		ArrayList<Integer> sourky = new ArrayList<Integer>();
		sourky.add(5);
		sourky.add(4);
		System.out.println(model.getMap().get(sourky));
	}
}
