package cz.PacMan.main;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import lejos.nxt.Button;
import cz.PacMan.pohyb.Podvozek;
import cz.PacMan.senzory.BSenzor;
import cz.PacMan.senzory.LSenzor;

public class Controller implements Runnable {

	private int value90 = 1350;
	private int podlaha, bod;
	private int cooldown = 600;
	private List<Integer> pozice = Arraye.asList(6, 5);
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
		// Model.putToMap(pozice, "S");
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
				Podvozek.stop();
				String side;
				do {
					side = choosePath();
				} while (move(side, priority) == false);
			}

			if (getLValue() > 20 && cooldown <= 0) {
				cooldown = 600;
				Podvozek.stop();
				setPoint(".", "DEFAULT");
				String side;
				do {
					side = choosePath();
				} while (move(side, priority) == false);
			}
		}
		Thread.yield();
	}

	/**
	 * Vrátí hodnoty nejbližších 2 bodù ve tøech smìrech kolem robota
	 * 
	 * @param direction
	 *            Smìr robota
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
			key.add(getMValue(x - 2, y));
			key.add(getMValue(x, y - 2));
			key.add(getMValue(x + 2, y));
			key.add(getMValue(x, y + 1));
		} else if (direction == 1) {
			key.add(getMValue(x, y - 1));
			key.add(getMValue(x + 1, y));
			key.add(getMValue(x, y + 1));
			key.add(getMValue(x, y - 2));
			key.add(getMValue(x + 2, y));
			key.add(getMValue(x, y + 2));
			key.add(getMValue(x - 1, y));
		} else if (direction == 2) {
			key.add(getMValue(x + 1, y));
			key.add(getMValue(x, y + 1));
			key.add(getMValue(x - 1, y));
			key.add(getMValue(x + 2, y));
			key.add(getMValue(x, y + 2));
			key.add(getMValue(x - 2, y));
			key.add(getMValue(x, y - 1));
		} else if (direction == 3) {
			key.add(getMValue(x, y + 1));
			key.add(getMValue(x - 1, y));
			key.add(getMValue(x, y - 1));
			key.add(getMValue(x, y + 2));
			key.add(getMValue(x - 2, y));
			key.add(getMValue(x, y - 2));
			key.add(getMValue(x + 1, y));
		}
		return key;
	}

	/**
	 * Vrátí priority 3 smìrù pohybu pro rozhodování výbìru smìru
	 * 
	 * @return Hashtable<Integer, List<String>> priority smìrù Integer =
	 *         priorita List = list smìrù v dané prioritì
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
				if (points.get(3).equals(".")) {
					p1.add("LEFT");
				} else {
					p1.add("LEFT");
				}
			}
		} else if (points.get(0).equals(".")) {
			p3.add("LEFT");
		}
		if (points.get(1).equals(" ")) {
			if (points.get(4).equals(" ")) {
				p1.add("FRONT");
			} else {
				if (points.get(4).equals(".")) {
					p1.add("FRONT");
				} else {
					p1.add("FRONT");
				}
			}
		} else if (points.get(1).equals(".")) {
			p3.add("FRONT");
		}
		if (points.get(2).equals(" ")) {
			if (points.get(5).equals(" ")) {
				p1.add("RIGHT");
			} else {
				if (points.get(5).equals(".")) {
					p1.add("RIGHT");
				} else {
					p1.add("RIGHT");
				}
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
	 * Otoèí robota o 180 stupòù
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
		Podvozek.doprava();
		cekej(value90);
		Podvozek.stop();
		cekej(50);
		Podvozek.dopredu();
	}

	/**
	 * Otoèí robota o 90 stupòù doleva
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
	 * Otoèí robota o 90 stupòù doprava
	 */

	private void doRight() {
		cekej(100);
		Podvozek.dozadu();
		while (!(getLValue() > 15)) {
		}
		cekej(200);
		Podvozek.stop();
		cekej(200);
		Podvozek.doprava();
		cekej(value90);
		Podvozek.stop();
		cekej(50);
		Podvozek.dopredu();
	}

	/**
	 * Vrátí hodnotu bodu na hrací ploše
	 * 
	 * @param x
	 *            souøadnice x bodu
	 * @param y
	 *            souøadnice y bodu
	 * @return Hodnota bodu [x,y]
	 */

	private String getMValue(int x, int y) {
		return Model.getMap().get(Arraye.asList(x, y));
	}

	/**
	 * Vybere kudy pojede robot
	 * 
	 * @return vybraný smìr jízdy robota
	 */

	private String choosePath() {
		Hashtable<Integer, List<String>> options = getPathPriorities();
		String side = null;
		List<String> p1, p2, p3;
		p1 = new ArrayList<String>();
		p2 = new ArrayList<String>();
		p3 = new ArrayList<String>();
		p1 = options.get(1);
		p2 = options.get(2);
		p3 = options.get(3);
		if (p1.size() != 0) {
			if (p1.size() > 1) {
				side = chooseBestSide(p1);
			} else {
				side = p1.get(0);
				ArrayList<String> keys = checkValue(Podvozek.getDirection(),
						pozice);
				if (keys.get(6).equals("x")) {
					setPoint("x", "MIDDLE");
				}
			}
			priority = 1;
		} else if (p2.size() != 0) {
			if (p2.size() > 1) {
				side = chooseBestSide(p2);
			} else {
				side = p2.get(0);
				ArrayList<String> keys = checkValue(Podvozek.getDirection(),
						pozice);
				if (keys.get(6).equals("x")) {
					setPoint("x", "MIDDLE");
				}
			}
			priority = 2;
		} else if (p3.size() != 0) {
			if (p3.size() > 1) {
				side = chooseBestSide(p3);
			} else {
				side = p3.get(0);
				ArrayList<String> keys = checkValue(Podvozek.getDirection(),
						pozice);
				if (keys.get(6).equals("x")) {
					setPoint("x", "MIDDLE");
				}
			}
			priority = 3;
		} else {
			side = "BACK";
			setPoint("x", "MIDDLE");
		}
		return side;
	}

	private String chooseBestSide(List<String> options) {
		int direction = Podvozek.getDirection();
		String side;
		int left, right, front;
		left = 0;
		right = 0;
		front = 0;
		// side = p1.get(rand.nextInt(p1.size()));
		for (int y = 2; y < 8; y++) {
			for (int x = 2; x < 11; x++) {
				if (getMValue(x, y).equals(" ")) {
					String pSide = "BACK";
					String cSide = "BACK";
					int vzdalenost = 100;
					for (int i = 0; i < options.size(); i++) {
						int x0 = pozice.get(0);
						int y0 = pozice.get(1);
						pSide = options.get(i);
						if (direction == 0) {
							if (pSide.equals("LEFT")) {
								x0 -= 1;
								if (vzdalenost > Math.sqrt((x - x0) ^ 2
										+ (y - y0) ^ 2)) {
									vzdalenost = (int) Math.round(Math
											.sqrt((x - x0) ^ 2 + (y - x0) ^ 2));
									cSide = "LEFT";
								}
							}
							if (pSide.equals("FRONT")) {
								y0 -= 1;
								if (vzdalenost > Math.sqrt((x - x0) ^ 2
										+ (y - y0) ^ 2)) {
									vzdalenost = (int) Math.round(Math
											.sqrt((x - x0) ^ 2 + (y - x0) ^ 2));
									cSide = "FRONT";
								}
							}
							if (pSide.equals("RIGHT")) {
								x0 += 1;
								if (vzdalenost > Math.sqrt((x - x0) ^ 2
										+ (y - y0) ^ 2)) {
									vzdalenost = (int) Math.round(Math
											.sqrt((x - x0) ^ 2 + (y - x0) ^ 2));
									cSide = "RIGHT";
								}
							}
						} else if (direction == 1) {
							if (pSide.equals("LEFT")) {
								y0 -= 1;
								if (vzdalenost > Math.sqrt((x - x0) ^ 2
										+ (y - y0) ^ 2)) {
									vzdalenost = (int) Math.round(Math
											.sqrt((x - x0) ^ 2 + (y - x0) ^ 2));
									cSide = "LEFT";
								}
							}
							if (pSide.equals("FRONT")) {
								x0 += 1;
								if (vzdalenost > Math.sqrt((x - x0) ^ 2
										+ (y - y0) ^ 2)) {
									vzdalenost = (int) Math.round(Math
											.sqrt((x - x0) ^ 2 + (y - x0) ^ 2));
									cSide = "FRONT";
								}
							}
							if (pSide.equals("RIGHT")) {
								y0 += 1;
								if (vzdalenost > Math.sqrt((x - x0) ^ 2
										+ (y - y0) ^ 2)) {
									vzdalenost = (int) Math.round(Math
											.sqrt((x - x0) ^ 2 + (y - x0) ^ 2));
									cSide = "RIGHT";
								}
							}
						} else if (direction == 2) {
							if (pSide.equals("LEFT")) {
								x0 += 1;
								if (vzdalenost > Math.sqrt((x - x0) ^ 2
										+ (y - y0) ^ 2)) {
									vzdalenost = (int) Math.round(Math
											.sqrt((x - x0) ^ 2 + (y - x0) ^ 2));
									cSide = "LEFT";
								}
							}
							if (pSide.equals("FRONT")) {
								y0 += 1;
								if (vzdalenost > Math.sqrt((x - x0) ^ 2
										+ (y - y0) ^ 2)) {
									vzdalenost = (int) Math.round(Math
											.sqrt((x - x0) ^ 2 + (y - x0) ^ 2));
									cSide = "FRONT";
								}
							}
							if (pSide.equals("RIGHT")) {
								x0 -= 1;
								if (vzdalenost > Math.sqrt((x - x0) ^ 2
										+ (y - y0) ^ 2)) {
									vzdalenost = (int) Math.round(Math
											.sqrt((x - x0) ^ 2 + (y - x0) ^ 2));
									cSide = "RIGHT";
								}
							}
						} else if (direction == 3) {
							if (pSide.equals("LEFT")) {
								y0 += 1;
								if (vzdalenost > Math.sqrt((x - x0) ^ 2
										+ (y - y0) ^ 2)) {
									vzdalenost = (int) Math.round(Math
											.sqrt((x - x0) ^ 2 + (y - x0) ^ 2));
									cSide = "LEFT";
								}
							}
							if (pSide.equals("FRONT")) {
								x0 -= 1;
								if (vzdalenost > Math.sqrt((x - x0) ^ 2
										+ (y - y0) ^ 2)) {
									vzdalenost = (int) Math.round(Math
											.sqrt((x - x0) ^ 2 + (y - x0) ^ 2));
									cSide = "FRONT";
								}
							}
							if (pSide.equals("RIGHT")) {
								y0 -= 1;
								if (vzdalenost > Math.sqrt((x - x0) ^ 2
										+ (y - y0) ^ 2)) {
									vzdalenost = (int) Math.round(Math
											.sqrt((x - x0) ^ 2 + (y - x0) ^ 2));
									cSide = "RIGHT";
								}
							}
						}
					}
					if (cSide.equals("LEFT")) {
						left += 1;
					}
					if (cSide.equals("FRONT")) {
						front += 1;
					}
					if (cSide.equals("RIGHT")) {
						right += 1;
					}
				}
			}
		}
		if (left >= right) {
			if (left >= front) {
				side = "LEFT";
			} else {
				side = "FRONT";
			}
		} else {
			if (right >= front) {
				side = "RIGHT";
			} else {
				side = "FRONT";
			}
		}
		return side;
	}

	/**
	 * Provede urèený pohyb robota
	 * 
	 * @param side
	 *            smìr jízdy robota
	 * @param priority
	 *            priorita cesty
	 * @return true pokud je možné jet false pokud neni možné jet
	 */

	private boolean move(String side, int priority) {
		if (priority == 1 || priority == 2) {
			if (side.equals("LEFT")) {
				// Podvozek.SDoleva();
				// cekej(100);
				// if (USenzor.getDistance() > 25) {
				doLeft();
				return true;
				/*
				 * } else { setPoint("#", "LEFT"); return false; }
				 */
			} else if (side.equals("FRONT")) {
				Podvozek.dopredu();
				return true;
			} else if (side.equals("RIGHT")) {
				/*
				 * Podvozek.SDoprava(); cekej(100); if (USenzor.getDistance() >
				 * 30) {
				 */
				doRight();
				return true;
				/*
				 * } else { setPoint("#", "RIGHT"); return false; }
				 */
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
	 * Získá upravenou hodnotu svìtelného senzoru
	 * 
	 * @return Hodnota namìøená svìtelným senzorem
	 */

	private int getLValue() {
		if (podlaha < bod) {
			return LSenzor.getLight();
		} else {
			return 100 - LSenzor.getLight();
		}
	}

	/**
	 * Uspí vlákno
	 * 
	 * @param ms
	 *            Jak dlouho má být vlákno neaktivní v milisekundách
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
		if (position.equals("MIDDLE")) {
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