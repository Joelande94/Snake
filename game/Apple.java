package game;

import java.awt.Color;
import java.awt.Graphics;

public class Apple {
	private int xCoor, yCoor, width, height;
	
	public Apple(int xCoor, int yCoor, int tileSize){
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		width = tileSize;
		height = tileSize;
	}
	
	public int getxCoor() {
		return xCoor;
	}
	public int getyCoor() {
		return yCoor;
	}
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(xCoor * width, yCoor * height, width, height);
		g.setColor(new Color(50, 5, 10));
		g.drawOval(xCoor * width, yCoor * height, width, height);
	}
}
