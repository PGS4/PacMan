package cz.PacMan.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import cz.PacMan.entities.Zed;

public class Model {
	private static ArrayList<String> lines = new ArrayList<String>();
	private static ArrayList<Zed> zdi = new ArrayList<Zed>();;
	private static int levels = 1;
	private String line;
	private Reader in;
	private BufferedReader br;
	private static ArrayList<int[]> enemyPositions = new ArrayList<int[]>();

	public Model() {
	}

	// InputStream
	public void levels() {
		try {
			if (levels < 4) {
				//in = new InputStreamReader();
				br = new BufferedReader(in);
				while ((line = br.readLine()) != null) {
					lines.add(line);
				}
				initLevel();
				in.close();
			}

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}

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
			}
		}
	}

	/*
	 * public void endGame() { zdi.clear(); enemies.clear(); lines.clear(); }
	 */

	public void startNewGame() {
		levels = 1;
		//player.setX(40);
		//player.setY(40);
		zdi.clear();
		lines.clear();
		enemyPositions.clear();
		levels();
	}
}
