package cz.PacMan.main;

import lejos.nxt.Button;
import cz.PacMan.pohyb.Podvozek;
import cz.PacMan.senzory.LSenzor;
import cz.PacMan.senzory.USenzor;

public class PacMan {
	public static void main(String[] args) {
		// Podvozek.podvozekDopredu(250);
			System.out.println(LSenzor.getLight());
			System.out.println(USenzor.getDistance());
	}
}
