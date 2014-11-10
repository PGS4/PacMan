package cz.PacMan.main;

import java.util.Hashtable;
import java.util.List;

public class Model {
	private static Hashtable<List<Integer>, String> map = new Hashtable<List<Integer>, String>();

	public Model() {
	}

	public static void newMap() {
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 11; x++) {
				if (y == 0 || y == 8 - 1 || x == 0 || x == 11 - 1) {
					List<Integer> souradnice = Arraye.asList(x, y);
					map.put(souradnice, "#");
				} else {
					List<Integer> souradnice = Arraye.asList(x, y);
					map.put(souradnice, " ");
				}
			}
		}
	}
	
	public static Hashtable<List<Integer>, String> getMap(){
		return map;
	}
	
	public static void putToMap(List<Integer> key, String value){
		map.put(key, value);
	}
}