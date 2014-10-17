package cz.PacMan.main;

import cz.PacMan.pohyb.Podvozek;
import cz.PacMan.senzory.BSenzor;
import cz.PacMan.senzory.LSenzor;
import cz.PacMan.senzory.USenzor;

public class Controller implements Runnable {
	private int cooldown = 0;

	public Controller() {
	}

	public void zapnout() {
		Podvozek.podvozekDopredu(450);
		while (!BSenzor.isPressed()) {
			if (LSenzor.getLight() > 80 && cooldown <= 0) {
				cooldown = 1000;
				Podvozek.podvozekStop();
				cekej(100);
				if (USenzor.getDistance() < 50) {
					Podvozek.doprava();
					cekej(1373);
					Podvozek.podvozekStop();
					Podvozek.podvozekDopredu(450);
				} else {
					Podvozek.doleva();
					cekej(1373);
					Podvozek.podvozekStop();
					Podvozek.podvozekDopredu(450);
				}
			}
			cooldown -= 1;
			Thread.yield();
		}
	}

	public void cekej(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		zapnout();
	}
}
