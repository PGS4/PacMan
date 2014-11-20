package cz.PacMan.main;

import cz.PacMan.senzory.LSenzor;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.util.TextMenu;

public class PacMan {
	private static int v1, v2, svetlo1, svetlo2;
	private static int konfigurace = 1;

	public static void main(String[] args) {
		/*
		 * while(!Button.ESCAPE.isDown()){
		 * System.out.println(LSenzor.getLight()); }
		 */
		LSenzor.zapnout();
		System.out.println("Podlaha <LEFT>");
		while (!Button.LEFT.isDown()) {
		}
		v1 = LSenzor.getNormLight();
		svetlo1 = LSenzor.getLight();
		//System.out.println(LSenzor.getLight());
		System.out.println("Bod <RIGHT>");
		while (!Button.RIGHT.isDown()) {
		}
		v2 = LSenzor.getNormLight();
		svetlo2 = LSenzor.getLight();
		//System.out.println(LSenzor.getLight());
		
		LCD.clear();
		

		String[] items = { "1", "2", "3", "4", "5", "6", "7", "8" };
		TextMenu menu = new TextMenu(items, 0);
		menu.setTitle("Menu konfigurace");

		konfigurace = menu.select(2)+1;

		LCD.clear();
		LCD.refresh();
		System.out.println("Podlaha : "+svetlo1);
		System.out.println("Bod : "+svetlo2);
		System.out.println("Konfigurace : " + konfigurace);
		System.out.println("Vohul to!<ENTER>");
		
		Controller.cekej(1000);
		
		while (!Button.ENTER.isDown()) {

		}
		LCD.clear();
		
		if (konfigurace > 0) {
			LSenzor.kalibrovat(v1, v2);
			Controller control = new Controller(v1, v2, konfigurace);
			Thread c = new Thread(control);
			c.setDaemon(true);
			c.start();
			while (!Button.ESCAPE.isDown()) {
			}
			
		}
		

	}
}