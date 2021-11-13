package Game;

import java.awt.Color;
import java.awt.Graphics;

public class Block 
{
	private int xcoordinate;
	private int ycoordinate;
	private int width;
	private int height;
	
	public Block(int x, int y, int tileSize)
	{
		xcoordinate = x;
		ycoordinate = y;
		this.width = tileSize;
		this.height = tileSize;
	}
	public int getXcoordinate()
	{
		return xcoordinate;
	}
	
	public void setXcoordinate(int xcoordinate) 
	{
		this.xcoordinate = xcoordinate;
	}
	public int getYcoordinate() {
		return ycoordinate;
	}
	
	public void setYcoordinate(int ycoordinate)
	{
		this.ycoordinate = ycoordinate;
	}
	
	public void draw(Graphics background)
	{
		background.setColor(Color.YELLOW);
		background.fillRect(xcoordinate * width, ycoordinate * height, width, height);
	}
}
