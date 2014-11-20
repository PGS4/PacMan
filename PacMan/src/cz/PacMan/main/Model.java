package cz.PacMan.main;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Model {
	private static Hashtable<List<Integer>, String> map = new Hashtable<List<Integer>, String>();
	
	public Model() {
	}
	
	public static void initMaps() {
	/*	
		String line;
		
		line = "#############";
		konfigurace1.add(line);
		line = "#############";
		konfigurace1.add(line);
		line = "##         ##";
		konfigurace1.add(line);
		line = "## ## # ## ##";
		konfigurace1.add(line);
		line = "## #     # ##";
		konfigurace1.add(line);
		line = "## # #S# # ##";
		konfigurace1.add(line);
		line = "## #     # ##";
		konfigurace1.add(line);
		line = "##         ##";
		konfigurace1.add(line);
		line = "#############";
		konfigurace1.add(line);
		line = "#############";
		konfigurace1.add(line);*/
		
		
	}

	public static void newMap(int konfigurace) {

		ArrayList<String> finalKonfigurace = new ArrayList<String>();
		
		String line;
		
		
		// Konfigurace è. 1 ------------------- KONFIGURACE è. 3
		ArrayList<String> konfigurace1 = new ArrayList<String>();
		
		line = "#############";
		konfigurace1.add(line);
		line = "#############";
		konfigurace1.add(line);
		line = "##         ##";
		konfigurace1.add(line);
		line = "## ## # ## ##";
		konfigurace1.add(line);
		line = "## #     # ##";
		konfigurace1.add(line);
		line = "## # #S# # ##";
		konfigurace1.add(line);
		line = "## #     # ##";
		konfigurace1.add(line);
		line = "##         ##";
		konfigurace1.add(line);
		line = "#############";
		konfigurace1.add(line);
		line = "#############";
		konfigurace1.add(line);
		
		// Konfigurace è. 2 ------------------- KONFIGURACE è. 11
		ArrayList<String> konfigurace2 = new ArrayList<String>();
		
		line = "#############";
		konfigurace2.add(line);
		line = "#############";
		konfigurace2.add(line);
		line = "##         ##";
		konfigurace2.add(line);
		line = "##  ###### ##";
		konfigurace2.add(line);
		line = "##       # ##";
		konfigurace2.add(line);
		line = "##   #S### ##";
		konfigurace2.add(line);
		line = "##       # ##";
		konfigurace2.add(line);
		line = "##       # ##";
		konfigurace2.add(line);
		line = "#############";
		konfigurace2.add(line);
		line = "#############";
		konfigurace2.add(line);
		
		// Konfigurace è. 3 ------------------- KONFIGURACE è. 8
		ArrayList<String> konfigurace3 = new ArrayList<String>();
		
		line = "#############";
		konfigurace3.add(line);
		line = "#############";
		konfigurace3.add(line);
		line = "##         ##";
		konfigurace3.add(line);
		line = "## #     # ##";
		konfigurace3.add(line);
		line = "##         ##";
		konfigurace3.add(line);
		line = "## ###S######";
		konfigurace3.add(line);
		line = "##         ##";
		konfigurace3.add(line);
		line = "## # # # # ##";
		konfigurace3.add(line);
		line = "#############";
		konfigurace3.add(line);
		line = "#############";
		konfigurace3.add(line);
		
		// Konfigurace è. 4 ------------------- KONFIGURACE è. 19
		ArrayList<String> konfigurace4 = new ArrayList<String>();
		
		line = "#############";
		konfigurace4.add(line);
		line = "#############";
		konfigurace4.add(line);
		line = "##         ##";
		konfigurace4.add(line);
		line = "## # # # # ##";
		konfigurace4.add(line);
		line = "##         ##";
		konfigurace4.add(line);
		line = "### ##S## ###";
		konfigurace4.add(line);
		line = "##         ##";
		konfigurace4.add(line);
		line = "## #  #  # ##";
		konfigurace4.add(line);
		line = "#############";
		konfigurace4.add(line);
		line = "#############";
		konfigurace4.add(line);
		
		// Konfigurace è. 5 ------------------- KONFIGURACE è. 7
		ArrayList<String> konfigurace5 = new ArrayList<String>();
		
		line = "#############";
		konfigurace5.add(line);
		line = "#############";
		konfigurace5.add(line);
		line = "##    #    ##";
		konfigurace5.add(line);
		line = "## # # # # ##";
		konfigurace5.add(line);
		line = "##         ##";
		konfigurace5.add(line);
		line = "## # #S# # ##";
		konfigurace5.add(line);
		line = "##         ##";
		konfigurace5.add(line);
		line = "## # # # # ##";
		konfigurace5.add(line);
		line = "#############";
		konfigurace5.add(line);
		line = "#############";
		konfigurace5.add(line);
		
		// Konfigurace è. 6 ------------------- KONFIGURACE è. 18
		ArrayList<String> konfigurace6 = new ArrayList<String>();
		
		line = "#############";
		konfigurace6.add(line);
		line = "#############";
		konfigurace6.add(line);
		line = "##   # #  ###";
		konfigurace6.add(line);
		line = "## #     # ##";
		konfigurace6.add(line);
		line = "##  #      ##";
		konfigurace6.add(line);
		line = "###  #S#   ##";
		konfigurace6.add(line);
		line = "##  # # #  ##";
		konfigurace6.add(line);
		line = "##       # ##";
		konfigurace6.add(line);
		line = "#############";
		konfigurace6.add(line);
		line = "#############";
		konfigurace6.add(line);
		
		// Konfigurace è. 7 ------------------- KONFIGURACE è. 20
		ArrayList<String> konfigurace7 = new ArrayList<String>();
		
		line = "#############";
		konfigurace7.add(line);
		line = "#############";
		konfigurace7.add(line);
		line = "##         ##";
		konfigurace7.add(line);
		line = "## # # # # ##";
		konfigurace7.add(line);
		line = "## # # # # ##";
		konfigurace7.add(line);
		line = "##  ##S##  ##";
		konfigurace7.add(line);
		line = "##         ##";
		konfigurace7.add(line);
		line = "##    #    ##";
		konfigurace7.add(line);
		line = "#############";
		konfigurace7.add(line);
		line = "#############";
		konfigurace7.add(line);
		
		// Konfigurace è. 8 ------------------- KONFIGURACE è. 13
		ArrayList<String> konfigurace8 = new ArrayList<String>();
		
		line = "#############";
		konfigurace8.add(line);
		line = "#############";
		konfigurace8.add(line);
		line = "##         ##";
		konfigurace8.add(line);
		line = "## #     # ##";
		konfigurace8.add(line);
		line = "##         ##";
		konfigurace8.add(line);
		line = "## # #S# # ##";
		konfigurace8.add(line);
		line = "## #   # # ##";
		konfigurace8.add(line);
		line = "## # # # # ##";
		konfigurace8.add(line);
		line = "#############";
		konfigurace8.add(line);
		line = "#############";
		konfigurace8.add(line);
		
		

		if (konfigurace == 1) {
			finalKonfigurace = konfigurace1;
		} else if (konfigurace == 2) {
			finalKonfigurace = konfigurace2;
		} else if (konfigurace == 3) {
			finalKonfigurace = konfigurace3;
		} else if (konfigurace == 4) {
			finalKonfigurace = konfigurace4;
		} else if (konfigurace == 5) {
			finalKonfigurace = konfigurace5;
		} else if (konfigurace == 6) {
			finalKonfigurace = konfigurace6;
		} else if (konfigurace == 7) {
			finalKonfigurace = konfigurace7;
		} else if (konfigurace == 8) {
			finalKonfigurace = konfigurace8;
		}
		
		for (int y = 0; y < finalKonfigurace.size(); y++) {
			for (int x = 0; x < finalKonfigurace.get(y).length(); x++) {
				/*if (y == 0 || y == 8 - 1 || x == 0 || x == 11 - 1) {
					List<Integer> souradnice = Arraye.asList(x, y);
					map.put(souradnice, "#");
				} else {
					List<Integer> souradnice = Arraye.asList(x, y);
					map.put(souradnice, " ");
				}*/
				map.put(Arraye.asList(x,y), String.valueOf(finalKonfigurace.get(y).charAt(x)));
			}
		}
	}

	public static Hashtable<List<Integer>, String> getMap() {
		return map;
	}

	public static void putToMap(List<Integer> key, String value) {
		map.put(key, value);
	}
}