package cz.PacMan.entities;


public class GameEntity{
	int x, y;
	int width, height;
	int direction, blockX, blockY;
	public GameEntity(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
