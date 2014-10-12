package cz.PacMan.main;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import cz.PacMan.entities.Zed;

public class Model {
	private static ArrayList<String> lines = new ArrayList<String>();
	private static ArrayList<Zed> zdi = new ArrayList<Zed>();
	private String line;
	private static ArrayList<int[]> enemyPositions = new ArrayList<int[]>();
	private static Hashtable<List<Integer>, String> map = new Hashtable<List<Integer>, String>();
	private List<Integer> souradnice = null;
	private Enumeration<?> objectsInMap;
	
	public Model() {
	}
	
	public void newMap() {
		for (int y = 0; y < 8-1; y++) {
			for (int x = 0; x < 11; x++) {
				if (y == 0 || y == 8 - 2 || x == 0
						|| x == 11 - 1) {
					souradnice.clear();
					souradnice.add(x);
					souradnice.add(y);
					map.put(souradnice, "#");
				} else {
					souradnice.clear();
					souradnice.add(x);
					souradnice.add(y);
					map.put(souradnice, " ");
				}
			}
		}
		objectsInMap = map.keys();
		while(objectsInMap.hasMoreElements()){
			@SuppressWarnings("unchecked")
			List<Integer> sourky = ((List<Integer>) objectsInMap.nextElement());
			int x = sourky.get(0);
			int y = sourky.get(1);		
			System.out.println(x +" "+y+": "+map.get(sourky));
		}
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
	
	public Hashtable<List<Integer>, String> getMap(){
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
