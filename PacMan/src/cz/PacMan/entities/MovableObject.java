package cz.PacMan.entities;




public class MovableObject extends GameEntity{
	int dx, dy;
	public MovableObject(int x, int y){
		super (x,y);
	}
	public void move(){
		x += dx;
		y += dy;
	}
	public void setDx(int dx){
		this.dx = dx;
	}
	public void setDy(int dy){
		this.dy = dy;
	}
	public int getDx(){
		return dx;
	}
	public int getDy(){
		return dy;
	}
}
