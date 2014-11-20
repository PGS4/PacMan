package cz.PacMan.processThreads;

import java.util.List;

import cz.PacMan.main.Arraye;
import cz.PacMan.main.Model;

public class ThreadOne implements Runnable {
	private int left;
	private int front;
	private int right;
	private List<Double> vlefts, vfronts, vrights;
	private int direction;
	private List<Integer> pozice;
	private List<String> options;
	private boolean hasFinished = false;
	int from, to;

	public ThreadOne(int left, int front, int right, List<Double> vlefts,
			List<Double> vfronts, List<Double> vrights, int direction,
			List<Integer> pozice, List<String> options, int from, int to) {
		this.left = left;
		this.front = front;
		this.right = right;
		this.vlefts = vlefts;
		this.vfronts = vfronts;
		this.vrights = vrights;
		this.direction = direction;
		this.pozice = pozice;
		this.options = options;
		this.from = from;
		this.to = to;
		hasFinished = false;
	}

	private void doLoop() {
		for (int y = from; y < to; y++) {
			for (int x = 2; x < 11; x++) {
				if (getMValue(x, y).equals(" ")) {
					String pSide = "YEP", pSide2 = "YEP", pSide3 = "YEP", cSide = "BACK";
					double vzdalenost = 10000;
					for (int i = 0; i < options.size(); i += 3) {
						int x0 = pozice.get(0);
						int y0 = pozice.get(1);
						if (i <= options.size() - 1) {
							pSide = options.get(i);
						}
						if (i < options.size() - 1) {
							pSide2 = options.get(i + 1);
						}
						if (i < options.size() - 2) {
							pSide3 = options.get(i + 2);
						}
						if (direction == 0) {
							if (pSide.equals("LEFT") || pSide2.equals("LEFT")
									|| pSide3.equals("LEFT")) {
								x0 -= 1;
								double nVzdalenost = Math.sqrt((Math.pow(
										x - x0, 2)) + (Math.pow(y - y0, 2)));
								if (vzdalenost > nVzdalenost) {
									vzdalenost = nVzdalenost;
									cSide = "LEFT";
								} else if (vzdalenost == nVzdalenost) {
									cSide = "BACK";
								}
								x0 += 1;
							}
							if (pSide.equals("BACK") || pSide2.equals("BACK")
									|| pSide3.equals("BACK")) {
								y0 += 1;
								double nVzdalenost = Math.sqrt((Math.pow(
										x - x0, 2)) + (Math.pow(y - y0, 2)));
								if (vzdalenost > nVzdalenost) {
									vzdalenost = nVzdalenost;
									cSide = "BACK";
								} else if (vzdalenost == nVzdalenost) {
									cSide = "BACK";
								}
								y0 -= 1;
							}
							if (pSide.equals("FRONT") || pSide2.equals("FRONT")
									|| pSide3.equals("FRONT")) {
								y0 -= 1;
								double nVzdalenost = Math.sqrt((Math.pow(
										x - x0, 2)) + (Math.pow(y - y0, 2)));
								if (vzdalenost > nVzdalenost) {
									vzdalenost = nVzdalenost;
									cSide = "FRONT";
								} else if (vzdalenost == nVzdalenost) {
									cSide = "BACK";
								}
								y0 += 1;
							}
							if (pSide.equals("RIGHT") || pSide2.equals("RIGHT")
									|| pSide3.equals("RIGHT")) {
								x0 += 1;
								double nVzdalenost = Math.sqrt((Math.pow(
										x - x0, 2)) + (Math.pow(y - y0, 2)));
								if (vzdalenost > nVzdalenost) {
									vzdalenost = nVzdalenost;
									cSide = "RIGHT";
								} else if (vzdalenost == nVzdalenost) {
									cSide = "BACK";
								}
								x0 -= 1;
							}
						} else if (direction == 1) {
							if (pSide.equals("BACK") || pSide2.equals("BACK")
									|| pSide3.equals("BACK")) {
								x0 -= 1;
								double nVzdalenost = Math.sqrt((Math.pow(
										x - x0, 2)) + (Math.pow(y - y0, 2)));
								if (vzdalenost >= nVzdalenost) {
									vzdalenost = nVzdalenost;
									cSide = "BACK";
								}
								x0 += 1;
							}
							if (pSide.equals("LEFT") || pSide2.equals("LEFT")
									|| pSide3.equals("LEFT")) {
								y0 -= 1;
								double nVzdalenost = Math.sqrt(Math.pow(x - x0,
										2) + Math.pow(y - y0, 2));
								if (vzdalenost > nVzdalenost) {
									vzdalenost = nVzdalenost;
									cSide = "LEFT";
								} else if (vzdalenost == nVzdalenost) {
									cSide = "BACK";
								}
								y0 += 1;
							}
							if (pSide.equals("FRONT") || pSide2.equals("FRONT")
									|| pSide3.equals("FRONT")) {
								x0 += 1;
								double nVzdalenost = Math.sqrt(Math.pow(x - x0,
										2) + Math.pow(y - y0, 2));
								if (vzdalenost > nVzdalenost) {
									vzdalenost = nVzdalenost;
									cSide = "FRONT";
								} else if (vzdalenost == nVzdalenost) {
									cSide = "BACK";
								}
								x0 -= 1;
							}
							if (pSide.equals("RIGHT") || pSide2.equals("RIGHT")
									|| pSide3.equals("RIGHT")) {
								y0 += 1;
								double nVzdalenost = Math.sqrt(Math.pow(x - x0,
										2) + Math.pow(y - y0, 2));
								if (vzdalenost > nVzdalenost) {
									vzdalenost = nVzdalenost;
									cSide = "RIGHT";
								} else if (vzdalenost == nVzdalenost) {
									cSide = "BACK";
								}
								y0 -= 1;
							}
						} else if (direction == 2) {
							if (pSide.equals("BACK") || pSide2.equals("BACK")
									|| pSide3.equals("BACK")) {
								y0 -= 1;
								double nVzdalenost = Math.sqrt((Math.pow(
										x - x0, 2)) + (Math.pow(y - y0, 2)));
								if (vzdalenost > nVzdalenost) {
									vzdalenost = nVzdalenost;
									cSide = "BACK";
								} else if (vzdalenost == nVzdalenost) {
									cSide = "BACK";
								}
								y0 += 1;
							}
							if (pSide.equals("LEFT") || pSide2.equals("LEFT")
									|| pSide3.equals("LEFT")) {
								x0 += 1;
								double nVzdalenost = Math.sqrt(Math.pow(x - x0,
										2) + Math.pow(y - y0, 2));
								if (vzdalenost > nVzdalenost) {
									vzdalenost = nVzdalenost;
									cSide = "LEFT";
								} else if (vzdalenost == nVzdalenost) {
									cSide = "BACK";
								}
								x0 -= 1;
							}
							if (pSide.equals("FRONT") || pSide2.equals("FRONT")
									|| pSide3.equals("FRONT")) {
								y0 += 1;
								double nVzdalenost = Math.sqrt(Math.pow(x - x0,
										2) + Math.pow(y - y0, 2));
								if (vzdalenost > nVzdalenost) {
									vzdalenost = nVzdalenost;
									cSide = "FRONT";
								} else if (vzdalenost == nVzdalenost) {
									cSide = "BACK";
								}
								y0 -= 1;
							}
							if (pSide.equals("RIGHT") || pSide2.equals("RIGHT")
									|| pSide3.equals("RIGHT")) {
								x0 -= 1;
								double nVzdalenost = Math.sqrt(Math.pow(x - x0,
										2) + Math.pow(y - y0, 2));
								if (vzdalenost > nVzdalenost) {
									vzdalenost = nVzdalenost;
									cSide = "RIGHT";
								} else if (vzdalenost == nVzdalenost) {
									cSide = "BACK";
								}
								x0 += 1;
							}
						} else if (direction == 3) {
							if (pSide.equals("BACK") || pSide2.equals("BACK")
									|| pSide3.equals("BACK")) {
								x0 += 1;
								double nVzdalenost = Math.sqrt((Math.pow(
										x - x0, 2)) + (Math.pow(y - y0, 2)));
								if (vzdalenost > nVzdalenost) {
									vzdalenost = nVzdalenost;
									cSide = "BACK";
								} else if (vzdalenost == nVzdalenost) {
									cSide = "BACK";
								}
								x0 -= 1;
							}
							if (pSide.equals("LEFT") || pSide2.equals("LEFT")
									|| pSide3.equals("LEFT")) {
								y0 += 1;
								double nVzdalenost = Math.sqrt(Math.pow(x - x0,
										2) + Math.pow(y - y0, 2));
								if (vzdalenost > nVzdalenost) {
									vzdalenost = nVzdalenost;
									cSide = "LEFT";
								} else if (vzdalenost == nVzdalenost) {
									cSide = "BACK";
								}
								y0 -= 1;
							}
							if (pSide.equals("FRONT") || pSide2.equals("FRONT")
									|| pSide3.equals("FRONT")) {
								x0 -= 1;
								double nVzdalenost = Math.sqrt(Math.pow(x - x0,
										2) + Math.pow(y - y0, 2));
								if (vzdalenost > nVzdalenost) {
									vzdalenost = nVzdalenost;
									cSide = "FRONT";
								} else if (vzdalenost == nVzdalenost) {
									cSide = "BACK";
								}
								x0 += 1;
							}
							if (pSide.equals("RIGHT") || pSide2.equals("RIGHT")
									|| pSide3.equals("RIGHT")) {
								y0 -= 1;
								double nVzdalenost = Math.sqrt(Math.pow(x - x0,
										2) + Math.pow(y - y0, 2));
								if (vzdalenost > nVzdalenost) {
									vzdalenost = nVzdalenost;
									cSide = "RIGHT";
								} else if (vzdalenost == nVzdalenost) {
									cSide = "BACK";
								}
								y0 += 1;
							}
						}
					}
					if (cSide.equals("LEFT")) {
						left += 1;
						vlefts.add(vzdalenost);
					}
					if (cSide.equals("FRONT")) {
						front += 1;
						vfronts.add(vzdalenost);
					}
					if (cSide.equals("RIGHT")) {
						right += 1;
						vrights.add(vzdalenost);
					}
				}
				Thread.yield();
			}
		}
		hasFinished = true;
	}

	public int getLeft() {
		return left;
	}

	public int getRight() {
		return right;
	}

	public int getFront() {
		return front;
	}

	public List<Double> getVLefts() {
		return vlefts;
	}

	public List<Double> getVRights() {
		return vrights;
	}

	public List<Double> getVFronts() {
		return vfronts;
	}

	public boolean hasFinished() {
		return hasFinished;
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

	@Override
	public void run() {
		// TODO Auto-generated method stub
		doLoop();
	}

}
