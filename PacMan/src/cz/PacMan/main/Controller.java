package cz.PacMan.main;

import lejos.nxt.Button;
import cz.PacMan.pohyb.Podvozek;
import cz.PacMan.senzory.BSenzor;
import cz.PacMan.senzory.LSenzor;
import cz.PacMan.senzory.USenzor;

public class Controller implements Runnable {

	private int value90 = 2900;
	private boolean podminka = true;
	
	public Controller() {
	}

	public void zapnout() {
		Podvozek.dopredu(450);

		while (!Button.ESCAPE.isDown()) {

			System.out.println(LSenzor.getLight());

			if (BSenzor.isPressed()) {
				Podvozek.dozadu(300);
				cekej(400);

				if (USenzor.getDistance() < 25) {
					Podvozek.doprava();
					cekej(value90);
					Podvozek.stop();
					cekej(50);
					Podvozek.dopredu(450);
				}

				else {

					Podvozek.doleva();
					cekej(value90);
					Podvozek.stop();
					cekej(50);
					Podvozek.dopredu(450);

				}

			} else {
				if (LSenzor.getLight() > 30 && podminka) {
					Podvozek.stop();
					Podvozek.dopredu(50);
					
				} else {
					Podvozek.stop();
					Podvozek.dopredu(300);
					
				}

			}
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