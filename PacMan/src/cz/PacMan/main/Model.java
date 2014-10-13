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
	private Enumeration<?> objectsInMap;
	
	public Model() {
	}
	
	public void newMap() {
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 11; x++) {
				if (y == 0 || y == 8 - 1 || x == 0
						|| x == 11 - 1) {
					List<Integer> souradnice = Arraye.asList(x, y);
					map.put(souradnice, "#");
				} else {
					List<Integer> souradnice = Arraye.asList(x, y);
					map.put(souradnice, " ");
				}
			}
		}
	}
}
