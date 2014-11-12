package cz.PacMan.main;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import lejos.nxt.Button;
import cz.PacMan.pohyb.Podvozek;
import cz.PacMan.senzory.BSenzor;
import cz.PacMan.senzory.LSenzor;
import cz.PacMan.senzory.USenzor;

public class Controller implements Runnable {

	private int value90 = 1500;
	private int podlaha, bod;
	private int cooldown = 600;
	private List<Integer> pozice = Arraye.asList(5, 4);
	private int direction = Podvozek.getDirection();
	private View view;
	private int priority = 0;

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
				cekej(150);
				Podvozek.stop();
				String side;
				do {
					side = choosePath();
				} while (move(side, priority) == false);
				/*
				 * Podvozek.SDoleva(); if (USenzor.getDistance() > 25) {
				 * Podvozek.doleva(); cekej(value90); Podvozek.stop();
				 * cekej(50); Podvozek.dopredu(); } else { setPoint("#",
				 * "LEFT"); Podvozek.SDoprava(); if (USenzor.getDistance() > 25)
				 * { Podvozek.doprava(); cekej(value90); Podvozek.stop();
				 * cekej(50); Podvozek.dopredu(); } else { setPoint("#",
				 * "RIGHT"); doBack(); } }
				 */
			}

			if (getLValue() > 20 && cooldown <= 0) {
				cooldown = 600;
				Podvozek.stop();
				setPoint(".", "DEFAULT");
				String side;
				do {
					side = choosePath();
					System.out.println(side);
				} while (move(side, priority) == false);
				// choosePath();
				/*
				 * ArrayList<String> mapValue = new ArrayList<String>();
				 * mapValue = checkValue(Podvozek.getDirection(), pozice); if
				 * (mapValue.get(0).equals(" ")) { Podvozek.SDoleva(); if
				 * (USenzor.getDistance() > 25) { doLeft(); } else {
				 * setPoint("#", "LEFT"); if (mapValue.get(1).equals(" ")) {
				 * Podvozek.dopredu(); } else if (mapValue.get(2).equals(" ")) {
				 * Podvozek.SDoprava(); if (USenzor.getDistance() > 30) {
				 * doRight(); } } else { setPoint("#", "RIGHT");
				 * ArrayList<Integer> options = new ArrayList<Integer>(); if
				 * (mapValue.get(0).equals(".")) { options.add(0); } if
				 * (mapValue.get(1).equals(".")) { options.add(1); } if
				 * (mapValue.get(2).equals(".")) { options.add(2); }
				 * choosePath(options); } } } else if
				 * (mapValue.get(1).equals(" ")) { Podvozek.dopredu(); } else if
				 * (mapValue.get(2).equals(" ")) { Podvozek.SDoprava(); if
				 * (USenzor.getDistance() > 30) { doRight(); } else {
				 * setPoint("#", "RIGHT"); ArrayList<Integer> options = new
				 * ArrayList<Integer>(); if (mapValue.get(0).equals(".")) {
				 * options.add(0); } if (mapValue.get(1).equals(".")) {
				 * options.add(1); } if (mapValue.get(2).equals(".")) {
				 * options.add(2); } choosePath(options); } } else {
				 * ArrayList<Integer> options = new ArrayList<Integer>(); if
				 * (mapValue.get(0).equals(".")) { options.add(0); } if
				 * (mapValue.get(1).equals(".")) { options.add(1); } if
				 * (mapValue.get(2).equals(".")) { options.add(2); }
				 * choosePath(options); }
				 */
			}
		}
		Thread.yield();
	}

	/**
	 * Vr�t� hodnoty nejbli���ch 2 bod� ve t�ech sm�rech kolem robota
	 * 
	 * @param direction
	 *            Sm�r robota
	 * 
	 * @param position
	 *            pozice robota
	 */

	private ArrayList<String> checkValue(int direction, List<Integer> position) {
		ArrayList<String> key = new ArrayList<String>();
		int x = position.get(0);
		int y = position.get(1);
		if (direction == 0) {
			key.add(getMValue(x - 1, y));
			key.add(getMValue(x, y - 1));
			key.add(getMValue(x + 1, y));
			if (!getMValue(x - 2, y).equals(null)) {
				key.add(getMValue(x - 2, y));
			}
			if (!getMValue(x, y - 2).equals(null)) {
				key.add(getMValue(x, y - 2));
			}
			if (!getMValue(x + 2, y).equals(null)) {
				key.add(getMValue(x + 2, y));
			}
		} else if (direction == 1) {
			key.add(getMValue(x, y - 1));
			key.add(getMValue(x + 1, y));
			key.add(getMValue(x, y + 1));
			if (!getMValue(x, y - 2).equals(null)) {
				key.add(getMValue(x, y - 2));
			}
			if (!getMValue(x + 2, y).equals(null)) {
				key.add(getMValue(x + 2, y));
			}
			if (!getMValue(x, y + 2).equals(null)) {
				key.add(getMValue(x, y + 2));
			}
		} else if (direction == 2) {
			key.add(getMValue(x + 1, y));
			key.add(getMValue(x, y + 1));
			key.add(getMValue(x - 1, y));
			if (!getMValue(x + 2, y).equals(null)) {
				key.add(getMValue(x + 2, y));
			}
			if (!getMValue(x, y + 2).equals(null)) {
				key.add(getMValue(x, y + 2));
			}
			if (!getMValue(x - 2, y).equals(null)) {
				key.add(getMValue(x - 2, y));
			}
		} else if (direction == 3) {
			key.add(getMValue(x, y + 1));
			key.add(getMValue(x - 1, y));
			key.add(getMValue(x, y - 1));
			if (!getMValue(x, y + 2).equals(null)) {
				key.add(getMValue(x, y + 2));
			}
			if (!getMValue(x - 2, y).equals(null)) {
				key.add(getMValue(x - 2, y));
			}
			if (!getMValue(x, y - 2).equals(null)) {
				key.add(getMValue(x, y - 2));
			}
		}
		return key;
	}

	/**
	 * Vr�t� priority 3 sm�r� pohybu pro rozhodov�n� v�b�ru sm�ru
	 * 
	 * @return Hashtable<Integer, List<String>> priority sm�r� Integer =
	 *         priorita List = list sm�r� v dan� priorit�
	 */

	private Hashtable<Integer, List<String>> getPathPriorities() {
		Hashtable<Integer, List<String>> priorities = new Hashtable<Integer, List<String>>();
		List<String> p1, p2, p3;
		p1 = new ArrayList<String>();
		p2 = new ArrayList<String>();
		p3 = new ArrayList<String>();
		ArrayList<String> points = new ArrayList<String>();
		points = checkValue(Podvozek.getDirection(), pozice);
		if (points.get(0).equals(" ")) {
			if (points.get(3).equals(" ")) {
				p1.add("LEFT");
			} else {
				p2.add("LEFT");
			}
		} else if (points.get(0).equals(".")) {
			p3.add("LEFT");
		}
		if (points.get(1).equals(" ")) {
			if (points.get(4).equals(" ")) {
				p1.add("FRONT");
			} else {
				p2.add("FRONT");
			}
		} else if (points.get(1).equals(".")) {
			p3.add("FRONT");
		}
		if (points.get(2).equals(" ")) {
			if (points.get(5).equals(" ")) {
				p1.add("RIGHT");
			} else {
				p2.add("RIGHT");
			}
		} else if (points.get(2).equals(".")) {
			p3.add("RIGHT");
		}
		priorities.put(1, p1);
		priorities.put(2, p2);
		priorities.put(3, p3);
		return priorities;
	}

	/**
	 * Oto�� robota o 180 stup��
	 */

	private void doBack() {
		cekej(100);
		Podvozek.dozadu();
		while (!(getLValue() > 15)) {
		}
		cekej(200);
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

	/**
	 * Oto�� robota o 90 stup�� doleva
	 */

	private void doLeft() {
		cekej(100);
		Podvozek.dozadu();
		while (!(getLValue() > 15)) {
		}
		cekej(200);
		Podvozek.stop();
		cekej(200);
		Podvozek.doleva();
		cekej(value90);
		Podvozek.stop();
		cekej(50);
		Podvozek.dopredu();
	}

	/**
	 * Oto�� robota o 90 stup�� doprava
	 */

	private void doRight() {
		cekej(100);
		Podvozek.dozadu();
		while (!(getLValue() > 15)) {
		}
		cekej(150);
		Podvozek.stop();
		cekej(200);
		Podvozek.doprava();
		cekej(value90);
		Podvozek.stop();
		cekej(50);
		Podvozek.dopredu();
	}

	/**
	 * Vr�t� hodnotu bodu na hrac� plo�e
	 * 
	 * @param x
	 *            sou�adnice x bodu
	 * @param y
	 *            sou�adnice y bodu
	 * @return Hodnota bodu [x,y]
	 */

	private String getMValue(int x, int y) {
		return Model.getMap().get(Arraye.asList(x, y));
	}

	/**
	 * Vybere kudy pojede robot
	 * 
	 * @return vybran� sm�r j�zdy robota
	 */

	private String choosePath() {
		Hashtable<Integer, List<String>> options = getPathPriorities();
		Random rand = new Random();
		String side = null;
		List<String> p1, p2, p3;
		p1 = new ArrayList<String>();
		p2 = new ArrayList<String>();
		p3 = new ArrayList<String>();
		p1 = options.get(1);
		p2 = options.get(2);
		p3 = options.get(3);
		System.out.println("p1: " + p1.size());
		System.out.println("p2: " + p2.size());
		System.out.println("p3: " + p3.size());
		if (p1.size() != 0) {
			side = p1.get(rand.nextInt(p1.size()));
			priority = 1;
		} else if (p2.size() != 0) {
			side = p2.get(rand.nextInt(p2.size()));
			priority = 2;
		} else if (p3.size() != 0) {
			side = p3.get(rand.nextInt(p3.size()));
			priority = 3;
		}
		/*
		 * if (p1.size() != 0) { String side; side =
		 * p1.get(rand.nextInt(p1.size())); while (move(side, 1) == false &&
		 * p1.size() != 0) { p1.clear(); options = getPathPriorities(); p1 =
		 * options.get(1); p2 = options.get(2); p3 = options.get(3); side =
		 * p1.get(rand.nextInt(p1.size())); } if (p1.size() == 0) { if
		 * (p2.size() != 0) { side = p2.get(rand.nextInt(p1.size())); while
		 * (move(side, 2) == false && p2.size() != 0) { p2.clear(); options =
		 * getPathPriorities(); p1 = options.get(1); p2 = options.get(2); p3 =
		 * options.get(3); side = p2.get(rand.nextInt(p2.size())); } if
		 * (p2.size() == 0) { if (p3.size() != 0) { side =
		 * p3.get(rand.nextInt(p3.size())); move(side, 3); } else { doBack(); }
		 * } } else if (p3.size() != 0) { do { side =
		 * p3.get(rand.nextInt(p3.size())); } while (move(side, 3) == false); }
		 * else { doBack(); } } } if (p2.size() != 0) { String side =
		 * p2.get(rand.nextInt(p1.size())); ; while (move(side, 2) == false &&
		 * p2.size() != 0) { p2.clear(); options = getPathPriorities(); p1 =
		 * options.get(1); p2 = options.get(2); p3 = options.get(3); side =
		 * p2.get(rand.nextInt(p2.size())); } if (p2.size() == 0) { if
		 * (p3.size() != 0) { side = p3.get(rand.nextInt(p3.size())); move(side,
		 * 3); } else { doBack(); } } } else if (p3.size() != 0) { String side;
		 * do { side = p3.get(rand.nextInt(p3.size())); } while (move(side, 3)
		 * == false); }
		 */
		else {
			side = "BACK";
		}
		return side;
		/*
		 * Random rand = new Random(); if (options.size() != 0) { int direction
		 * = options.get(rand.nextInt(options.size())); if (direction == 0) {
		 * doLeft(); } if (direction == 1) { Podvozek.dopredu(); } if (direction
		 * == 2) { doRight(); } } else { Podvozek.dozadu(); cekej(250);
		 * doBack(); }
		 */
	}

	/**
	 * Provede ur�en� pohyb robota
	 * 
	 * @param side
	 *            sm�r j�zdy robota
	 * @param priority
	 *            priorita cesty
	 * @return true pokud je mo�n� jet false pokud neni mo�n� jet
	 */

	private boolean move(String side, int priority) {
		if (priority == 1 || priority == 2) {
			if (side.equals("LEFT")) {
				//Podvozek.SDoleva();
				//cekej(100);
				//if (USenzor.getDistance() > 25) {
					doLeft();
					return true;
				/*} else {
					setPoint("#", "LEFT");
					return false;
				}*/
			} else if (side.equals("FRONT")) {
				Podvozek.dopredu();
				return true;
			} else if (side.equals("RIGHT")) {
				/*Podvozek.SDoprava();
				cekej(100);
				if (USenzor.getDistance() > 30) {*/
					doRight();
					return true;
				/*} else {
					setPoint("#", "RIGHT");
					return false;
				}*/
			} else {
				doBack();
				return true;
			}
		} else {
			if (side.equals("LEFT")) {
				doLeft();
			} else if (side.equals("FRONT")) {
				Podvozek.dopredu();
			} else if (side.equals("RIGHT")) {
				doRight();
			} else {
				doBack();
				return true;
			}
		}
		return true;
	}

	/**
	 * Z�sk� upravenou hodnotu sv�teln�ho senzoru
	 * 
	 * @return Hodnota nam��en� sv�teln�m senzorem
	 */

	private int getLValue() {
		if (podlaha < bod) {
			return LSenzor.getLight();
		} else {
			return 100 - LSenzor.getLight();
		}
	}

	/**
	 * Usp� vl�kno
	 * 
	 * @param ms
	 *            Jak dlouho m� b�t vl�kno neaktivn� v milisekund�ch
	 */

	public void cekej(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Nastav� sm�r robota
	 * 
	 * @param direction
	 *            Sm�r robota
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}

	/**
	 * Dosazen� sou�adnice do Hash tabulky
	 * 
	 * @param objekt
	 *            '#' = ze�, ' ' = neprojet� �tverec, '.' = projet� �tverec
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
		if (position.equals("RIGHT")) {
			if (direction == 0) {
				x += 1;
			} else if (direction == 2) {
				x -= 1;
			} else if (direction == 3) {
				y -= 1;
			} else if (direction == 1) {
				y += 1;
			}

			Model.putToMap(Arraye.asList(x, y), objekt);
		}
		view.paint();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		zapnout();
	}
}