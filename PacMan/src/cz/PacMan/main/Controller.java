package cz.PacMan.main;

import cz.PacMan.pohyb.Podvozek;
import cz.PacMan.senzory.BSenzor;
import cz.PacMan.senzory.LSenzor;

public class Controller {
	private int LSenzorDefault;
	public Controller(){
		
	}
	
	public void start(int LSenzorHodnota){
		LSenzorDefault = LSenzorHodnota;
		Podvozek.podvozekDopredu(450);
		while(!BSenzor.isPressed()){
			if(!(Math.abs(LSenzorDefault-LSenzor.getLight())<10)){
				Podvozek.podvozekStop();
				Podvozek.doleva();
				try {
					wait(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Podvozek.podvozekStop();
				Podvozek.podvozekDopredu(450);
			}
		}
	}
}
