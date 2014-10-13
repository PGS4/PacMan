package cz.PacMan.main;

import cz.PacMan.pohyb.Podvozek;
import cz.PacMan.senzory.BSenzor;

public class Controller {
	public Controller(){
		
	}
	
	public void start(int LSenzorHodnota){
		Podvozek.podvozekDozadu(450);
		while(!BSenzor.isPressed()){
			
		}
	}
}
