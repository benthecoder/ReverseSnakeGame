package Game;

import java.awt.Color;
import java.awt.Graphics;

public class Snake 
{

	private int xcoordinate;
	private int ycoordinate;
	private int width;
	private int height;
	private boolean snakeKeyRight = true;
	private boolean snakeKeyLeft = false;
	private boolean snakeKeyUp = false;
	private boolean snakeKeyDown = false;
	
	public Snake(int x, int y, int tileSize)
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
	
	public boolean getSnakeKeyRight()
	{
		return snakeKeyRight;
	}

	public boolean getSnakeKeyLeft()
	{
		return snakeKeyLeft;
	}

	public boolean getSnakeKeyUp()
	{
		return snakeKeyUp;
	}

	public boolean getSnakeKeyDown()
	{
		return snakeKeyDown;
	}
	
	public void draw(Graphics background)
	{
		background.setColor(Color.GREEN);
		background.fillRect(xcoordinate * width, ycoordinate * height, width, height);
	}
}
