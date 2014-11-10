package cz.PacMan.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lejos.nxt.Button;
import cz.PacMan.pohyb.Podvozek;
import cz.PacMan.senzory.BSenzor;
import cz.PacMan.senzory.LSenzor;
import cz.PacMan.senzory.USenzor;

public class Controller implements Runnable {

	private int value90 = 1200;
	private int podlaha, bod;
	private int cooldown = 700;
	private List<Integer> pozice = Arraye.asList(5, 4);
	private int direction = Podvozek.getDirection();
	private View view;

	public Controller(int podlaha, int bod) {
		this.podlaha = podlaha;
		this.bod = bod;
		view = new View();
	}

	public void zapnout() {
		Model.newMap();
		view.paint();
		Model.putToMap(pozice, "S");
		Podvozek.dopredu();
		while (!Button.ESCAPE.isDown()) {
			cooldown -= 1;

			if (BSenzor.isPressed()) {
				cooldown = 700;
				Podvozek.stop();
				setPoint("#", "FRONT");
				Podvozek.dozadu();
				while (!(getLValue() > 15)) {
				}
				cekej(400);
				Podvozek.stop();
				if(Podvozek.getSDirection()==1){
					Podvozek.SDoleva();
					cekej(500);
				}
				if(USenzor.getDistance()>25) {
					Podvozek.doleva();
					cekej(value90);
					Podvozek.stop();
					cekej(50);
					Podvozek.dopredu();
				}else{
					setPoint("#", "LEFT");
					if(Podvozek.getSDirection()==0){
						Podvozek.SDoprava();
						cekej(500);
					}
					if(USenzor.getDistance()>25){
						Podvozek.doprava();
						cekej(value90);
						Podvozek.stop();
						cekej(50);
						Podvozek.dopredu();
					}else{
						setPoint("#", "RIGHT");
						doBack();
					}
				}
			}

			if (getLValue() > 20 && cooldown <= 0) {
				cooldown = 700;
				Podvozek.stop();
				setPoint(".", "DEFAULT");
				ArrayList<String> mapValue = new ArrayList<String>();
				mapValue = checkValue(Podvozek.getDirection(), pozice);
				if (mapValue.get(0).equals(" ")) {
					if(Podvozek.getSDirection()==1){
					Podvozek.SDoleva();
					cekej(500);
					}
					if (USenzor.getDistance() > 25) {
						doLeft();
					} else {
						setPoint("#", "LEFT");
						if (mapValue.get(1).equals(" ")) {
							Podvozek.dopredu();
						} else if (mapValue.get(2).equals(" ")) {
							if(Podvozek.getSDirection()==0){
								Podvozek.SDoprava();
								cekej(500);
							}
							if (USenzor.getDistance() > 30) {
								doRight();
							}
						} else {
							setPoint("#", "RIGHT");
							ArrayList<Integer> options = new ArrayList<Integer>();
							if (mapValue.get(0).equals(".")) {
								options.add(0);
							}
							if (mapValue.get(1).equals(".")) {
								options.add(1);
							}
							if (mapValue.get(2).equals(".")) {
								options.add(2);
							}
							choosePath(options);
						}
					}
				} else if (mapValue.get(1).equals(" ")) {
					Podvozek.dopredu();
				} else if (mapValue.get(2).equals(" ")) {
					if(Podvozek.getSDirection()==0){
					Podvozek.SDoprava();
					cekej(500);
					}
					if (USenzor.getDistance() > 30) {
						doRight();
					} else {
						setPoint("#", "RIGHT");
						ArrayList<Integer> options = new ArrayList<Integer>();
						if (mapValue.get(0).equals(".")) {
							options.add(0);
						}
						if (mapValue.get(1).equals(".")) {
							options.add(1);
						}
						if (mapValue.get(2).equals(".")) {
							options.add(2);
						}
						choosePath(options);
					}
				} else {
					ArrayList<Integer> options = new ArrayList<Integer>();
					if (mapValue.get(0).equals(".")) {
						options.add(0);
					}
					if (mapValue.get(1).equals(".")) {
						options.add(1);
					}
					if (mapValue.get(2).equals(".")) {
						options.add(2);
					}
					choosePath(options);
				}
			}
		}
		Thread.yield();
	}

	private ArrayList<String> checkValue(int direction, List<Integer> position) {
		ArrayList<String> key = new ArrayList<String>();
		int x = position.get(0);
		int y = position.get(1);
		if (direction == 0) {
			key.add(getMValue(x - 1, y));
			key.add(getMValue(x, y - 1));
			key.add(getMValue(x + 1, y));
		} else if (direction == 1) {
			key.add(getMValue(x, y - 1));
			key.add(getMValue(x + 1, y));
			key.add(getMValue(x, y + 1));
		} else if (direction == 2) {
			key.add(getMValue(x + 1, y));
			key.add(getMValue(x, y + 1));
			key.add(getMValue(x - 1, y));
		} else if (direction == 3) {
			key.add(getMValue(x, y + 1));
			key.add(getMValue(x - 1, y));
			key.add(getMValue(x, y - 1));
		}
		return key;
	}
	
	private void doBack(){
		Podvozek.stop();
		cekej(250);
		Podvozek.doprava();
		cekej(value90);
		Podvozek.stop();
		cekej(50);
		Podvozek.stop();
		cekej(200);
		Podvozek.doprava();
		cekej(value90);
		Podvozek.stop();
		cekej(50);
	}

	private void doLeft() {
		Podvozek.dozadu();
		cekej(250);
		Podvozek.stop();
		cekej(200);
		Podvozek.doleva();
		cekej(value90);
		Podvozek.stop();
		cekej(50);
		Podvozek.dopredu();
	}

	private void doRight() {
		Podvozek.dozadu();
		cekej(200);
		Podvozek.stop();
		cekej(200);
		Podvozek.doprava();
		cekej(value90);
		Podvozek.stop();
		cekej(50);
		Podvozek.dopredu();
	}

	private String getMValue(int x, int y) {
		return Model.getMap().get(Arraye.asList(x, y));
	}

	private void choosePath(ArrayList<Integer> options) {
		Random rand = new Random();
		if (options.size() != 0) {
			int direction = options.get(rand.nextInt(options.size()));
			if (direction == 0) {
				doLeft();
			}
			if (direction == 1) {
				Podvozek.dopredu();
			}
			if (direction == 2) {
				doRight();
			}
		} else {
			Podvozek.dozadu();
			cekej(250);
			doBack();
		}
	}

	private int getLValue() {
		if (podlaha < bod) {
			return LSenzor.getLight();
		} else {
			return 100 - LSenzor.getLight();
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

	/**
	 * Nastaví smìr robota
	 * 
	 * @param direction
	 *            Smìr robota
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}

	/**
	 * Dosazení souøadnice do Hash tabulky
	 * 
	 * @param objekt
	 *            '#' = zeï, ' ' = neprojetý ètverec, '.' = projetý ètverec
	 */
	public void setPoint(String objekt, String position) {
		int x = pozice.get(0);
		int y = pozice.get(1);
		direction = Podvozek.getDirection();
		if (position.equals("DEFAULT")) {
			if (direction == 0) {
				y -= 1;
			} else if (direction == 2) {
				y += 1;
			} else if (direction == 3) {
				x -= 1;
			} else if (direction == 1) {
				x += 1;
			}

			pozice = Arraye.asList(x, y);

			Model.putToMap(pozice, objekt);
		}
		if (position.equals("LEFT")) {
			if (direction == 0) {
				x -= 1;
			}
			if (direction == 1) {
				y -= 1;
			}
			if (direction == 2) {
				x += 1;
			}
			if (direction == 3) {
				y += 1;
			}

			Model.putToMap(Arraye.asList(x, y), objekt);
		}
		if (position.equals("FRONT")) {
			if (direction == 0) {
				y -= 1;
			} else if (direction == 2) {
				y += 1;
			} else if (direction == 3) {
				x -= 1;
			} else if (direction == 1) {
				x += 1;
			}

			Model.putToMap(Arraye.asList(x, y), objekt);
		}
		view.paint();
	}

	/*
	 * public void paint() { for(int i = 5; i < 80; i++) { LCD.setPixel(i, 5,
	 * 1); }
	 * 
	 * for(int i = 5; i < 80; i++) { LCD.setPixel(i, 60, 1); }
	 * 
	 * for(int i = 5; i < 60; i++) { LCD.setPixel(5, i, 1); }
	 * 
	 * for(int i = 5; i < 60; i++) { LCD.setPixel(80, i, 1); } String message =
	 * "ahoj"; LCD.refresh(); LCD.drawString(message, 10, 10); LCD.refresh(); }
	 */

	@Override
	public void run() {
		// TODO Auto-generated method stub
		zapnout();
	}
}