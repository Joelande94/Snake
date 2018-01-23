package game;

import java.awt.Color;
import java.awt.Graphics;

public class BodyPart {
	private int xCoor, yCoor, width, height;
	
	public BodyPart(int xCoor, int yCoor, int tileSize) {
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		this.width = tileSize;
		this.height = tileSize;
	}
	
	public int getxCoor(){
		return xCoor;
	}
	public int getyCoor(){
		return yCoor;
	}
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(xCoor * width, yCoor * height, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(xCoor * width, yCoor * height, width, height);
	}
}