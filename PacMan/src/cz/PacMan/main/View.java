package cz.PacMan.main;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import lejos.nxt.LCD;

public class View {

	private Hashtable<List<Integer>, String> map = new Hashtable<List<Integer>, String>();
	private Enumeration<?> objectsInMap;

	public View() {
		LCD.clear();
		LCD.setAutoRefresh(true);
		map = Model.getMap();
	}

	public void paint() {
		/*map = Model.getMap();
		objectsInMap = map.keys();
		while (objectsInMap.hasMoreElements()) {
			@SuppressWarnings("unchecked")
			List<Integer> souradnice = (List<Integer>) objectsInMap
					.nextElement();
			int x = (souradnice.get(0) * 8) + 5;
			int y = (souradnice.get(1) * 8);
			if (map.get(souradnice).equals("#")) {
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						LCD.setPixel(x + j, y + i, 1);
					}
				}
			}

			if (map.get(souradnice).equals(".")) {
				for (int i = 0; i < 8; i++) {
					LCD.setPixel(x + i, y + i, 1);
					LCD.setPixel(x +i, (y + 8) - i, 1);
				}
			}

		}
		LCD.refresh();*/
	}
}
