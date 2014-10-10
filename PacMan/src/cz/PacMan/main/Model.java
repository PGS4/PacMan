package cz.PacMan.main;

import java.util.ArrayList;

import java.util.Hashtable;

import cz.PacMan.entities.Zed;

public class Model {
	private static ArrayList<String> lines = new ArrayList<String>();
	private static ArrayList<Zed> zdi = new ArrayList<Zed>();
	private String line;
	private static ArrayList<int[]> enemyPositions = new ArrayList<int[]>();
	private static Hashtable<ArrayList<Integer>, String> map = new Hashtable<ArrayList<Integer>, String>();

	public Model() {
	}

	// InputStream
	public void levels() {
		line = "###########";
		lines.add(line);
		line = "#.........#";
		lines.add(line);
		line = "#.##.#.##.#";
		lines.add(line);
		line = "#.#.....#.#";
		lines.add(line);
		line = "#...#S#...#";
		lines.add(line);
		line = "#.#.....#.#";
		lines.add(line);
		line = "#...#.#...#";
		lines.add(line);
		line = "###########";
		lines.add(line);
	}

	public void initLevel() {
		for (int y = 0; y < lines.size(); y++) {
			for (int x = 0; x < lines.get(y).length(); x++) {
				String brick = String.valueOf(lines.get(y).charAt(x));
				if (brick.equals("#")) {
					int x2 = (x * 32);
					int y2 = (y * 32);
					zdi.add(new Zed(x2, y2));
				}
				ArrayList<Integer> souradnice = new ArrayList<Integer>();
				souradnice.add(x);
				souradnice.add(y);
				map.put(souradnice, brick);
			}
		}
	}
	
	public Hashtable<ArrayList<Integer>, String> getMap(){
		return map;
	}

	/*
	 * public void endGame() { zdi.clear(); enemies.clear(); lines.clear(); }
	 */

	public void startNewGame() {
		// player.setX(40);
		// player.setY(40);
		zdi.clear();
		lines.clear();
		enemyPositions.clear();
		levels();
	}
}
