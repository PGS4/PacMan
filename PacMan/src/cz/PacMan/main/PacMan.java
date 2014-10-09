package cz.PacMan.main;

import lejos.nxt.Button;
import cz.PacMan.pohyb.Podvozek;

public class PacMan {
	public static void main(String[] args) {
		Podvozek.podvozekDopredu(250);
		Button.waitForAnyPress();
	}
}
