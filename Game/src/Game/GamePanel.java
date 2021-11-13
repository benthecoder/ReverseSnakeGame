package Game;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

import java.util.TimerTask;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class GamePanel extends JPanel implements Runnable, KeyListener
{
	private static final long serialVersionUID = 1L;
	private final int WIDTH = 800;
	private final int HEIGHT = 800;
	private int xcoordinate = 10;
	private int xcoordinatesnake = 1;
	private int xcoordinatesnake2 = 20;
	private int ycoordinate = 10;
	private int ycoordinatesnake = 1;
	private int ycoordinatesnake2 = 20;
	private Thread thread;
	private boolean isRunning;
	private boolean Right = true;
	private boolean Left = false;
	private boolean Up = false;
	private boolean Down = false;
	private boolean snakeKeyRight = true;
	private boolean snakeKeyLeft = false;
	private boolean snakeKeyUp = false;
	private boolean snakeKeyDown = false;
	private boolean snakeKeyRight2 = true;
	private boolean snakeKeyLeft2 = false;
	private boolean snakeKeyUp2 = false;
	private boolean snakeKeyDown2 = false;
	private int numberOfTicks = 0;
	private Block block;
	private Snake snake;
	private Snake snake2;
	private int Size = 30;
	private ArrayList<Snake> snakeBody;
	private ArrayList<Snake> snakeBody2;
	private javax.swing.Timer timer;

	public GamePanel(javax.swing.Timer timer)
	{
		this.timer = timer;
		setFocusable(true);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		addKeyListener(this);
		snakeBody = new ArrayList<Snake>();
		snakeBody2 = new ArrayList<Snake>();
		block = new Block(xcoordinate, ycoordinate, 10);
		begin();
		Timer timers = new Timer();
		timers.schedule(new TimerTask() 
		{
			@Override
			public void run() 
			{
				randomSnakeMovement();
			}
		}, 100, 180);//wait 0 ms before doing the action and do it every 1000ms (1second)
	}

	public void paint(Graphics background)
	{
		background.clearRect(0, 0, WIDTH, HEIGHT);
		background.setColor(Color.BLACK);
		background.fillRect(0, 0, WIDTH, HEIGHT);
		background.drawLine(0, 1000, 0, 0);
		for (int i = 0; i < snakeBody.size(); ++i)
		{
			snakeBody.get(i).draw(background);
		}
		for (int i = 0; i < snakeBody2.size(); ++i)
		{
			snakeBody2.get(i).draw(background);
		}
		block.draw(background);
	}

	public void randomSnakeMovement()
	{
		Random ran = new Random();
		int randomDirection = ran.nextInt(4);
		int randomDirection2 = ran.nextInt(4);

		if(randomDirection==0 && !snakeKeyLeft)
		{
			snakeKeyRight = true;
			snakeKeyUp = false;
			snakeKeyDown=false;
		}
		if(randomDirection2==0 && !snakeKeyLeft2)
		{
			snakeKeyRight2 = true;
			snakeKeyUp2 = false;
			snakeKeyDown2 =false;
		}
		if(randomDirection==1&& !snakeKeyRight)
		{
			snakeKeyLeft = true;
			snakeKeyUp = false;
			snakeKeyDown = false;
		}
		if(randomDirection2==1 && !snakeKeyRight2)
		{
			snakeKeyRight2 = true;
			snakeKeyUp2 = false;
			snakeKeyDown2=false;
		}
		if(randomDirection==2&& !snakeKeyDown)
		{
			snakeKeyUp = true;
			snakeKeyLeft = false;
			snakeKeyRight = false;
		}
		if(randomDirection2==2 && !snakeKeyDown2)
		{
			snakeKeyUp2 = true;
			snakeKeyLeft2 = false;
			snakeKeyRight2 = false;
		}
		if(randomDirection==3&& !snakeKeyUp)
		{
			snakeKeyDown = true;
			snakeKeyLeft = false;
			snakeKeyRight = false;
		}
		if(randomDirection2==3 && !snakeKeyUp2)
		{
			snakeKeyDown2 = true;
			snakeKeyLeft2 = false;
			snakeKeyRight2 = false;
		}
	}

	public void ticks()
	{
		if (snakeBody.size() == 0)
		{
			snake = new Snake(xcoordinatesnake, ycoordinatesnake, 10);
			snakeBody.add(snake);
			snake2 = new Snake(xcoordinatesnake2, ycoordinatesnake2, 10);
			snakeBody2.add(snake2);
		}
		++numberOfTicks;
		if (numberOfTicks > 200000) // lower number of ticks makes it faster 
		{
			if (Right)
			{
				++xcoordinate;
			}
			if (Left)
			{
				--xcoordinate;
			}
			if (Up)
			{
				--ycoordinate;
			}
			if (Down)
			{
				++ycoordinate;
			}
			if (snakeKeyRight)
			{
				++xcoordinatesnake;				
			}
			if (snakeKeyLeft)
			{
				--xcoordinatesnake;
			}
			if (snakeKeyUp)
			{
				--ycoordinatesnake;
			}
			if (snakeKeyDown)
			{
				++ycoordinatesnake;
			}
			if (snakeKeyRight2)
			{
				++xcoordinatesnake2;				
			}
			if (snakeKeyLeft2)
			{
				--xcoordinatesnake2;
			}
			if (snakeKeyUp2)
			{
				--ycoordinatesnake2;
			}
			if (snakeKeyDown2)
			{
				++ycoordinatesnake2;
			}
			numberOfTicks = 0;
			block = new Block(xcoordinate, ycoordinate, 10);
			snake = new Snake(xcoordinatesnake, ycoordinatesnake, 10);
			snake2 = new Snake(xcoordinatesnake2, ycoordinatesnake2, 10);
			snakeBody2.add(snake2);
			snakeBody.add(snake);
			if (snakeBody.size() > Size)
			{
				snakeBody.remove(0);
			}
			if (snakeBody2.size() > Size)
			{
				snakeBody2.remove(0);
			}			
		}

		for (int i = 0; i < snakeBody.size(); ++i)
		{
			if (xcoordinate == snakeBody.get(i).getXcoordinate() && ycoordinate == snakeBody.get(i).getYcoordinate())
			{
				if (i != snakeBody.size() - 1)
				{
					System.out.println("Game Over!");
					timer.stop();
					int age = JOptionPane.showConfirmDialog(null,"Do you want to play again?");
					if (age == 0)
					{
						Main.main(null);
					}
					else
					{
						if (age == 1)
						{
							System.exit(0);
						}
					}
					end();
				}
			}
		}
		for (int i = 0; i < snakeBody2.size(); ++i)
		{
			if (xcoordinate == snakeBody2.get(i).getXcoordinate() && ycoordinate == snakeBody2.get(i).getYcoordinate())
			{
				if (i != snakeBody2.size() - 1)
				{
					System.out.println("Game Over!");
					timer.stop();
					int age = JOptionPane.showConfirmDialog(null,"Do you want to play again?");
					if (age == 0)
					{
						Main.main(null);
					}
					else
					{
						if (age == 1)
						{
							System.exit(0);
						}
					}
					end();
				}
			}
		}
		if(xcoordinatesnake < 0)
		{
			snakeKeyRight = true;
			snakeKeyUp = false;
			snakeKeyDown=false;
		}

		if( xcoordinatesnake > 99)
		{
			snakeKeyLeft = true;
			snakeKeyUp = false;
			snakeKeyDown=false;
		}

		if(ycoordinatesnake <0)
		{
			snakeKeyDown = true;
			snakeKeyLeft = false;
			snakeKeyRight = false;
		}

		if(ycoordinatesnake > 99)
		{
			snakeKeyUp = true;
			snakeKeyLeft = false;
			snakeKeyRight = false;
		}
		if(xcoordinatesnake2 < 0)
		{
			snakeKeyRight2 = true;
			snakeKeyUp2 = false;
			snakeKeyDown2 =false;
		}

		if( xcoordinatesnake2 > 99)
		{
			snakeKeyLeft2 = true;
			snakeKeyUp2 = false;
			snakeKeyDown2=false;
		}

		if(ycoordinatesnake2 <0)
		{
			snakeKeyDown2 = true;
			snakeKeyLeft2 = false;
			snakeKeyRight2 = false;
		}

		if(ycoordinatesnake2 > 99)
		{
			snakeKeyUp2 = true;
			snakeKeyLeft2 = false;
			snakeKeyRight2 = false;
		}

		if (xcoordinate < 0 || ycoordinate < 0 || xcoordinate > 99 || ycoordinate > 99)
		{
			System.out.println("Game Over!");
			timer.stop();
			int age = JOptionPane.showConfirmDialog(null,"Do you want to play again?");
			if (age == 0)
			{
				Main.main(null);
			}
			else
			{
				if (age == 1)
				{
					System.exit(0);
				}
			}
			end();
		}
	}

	public void run() 
	{
		while(isRunning)
		{
			ticks();
			repaint();
		}	
	}

	public void begin()
	{
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	public void end()
	{
		isRunning = false;
		try
		{
			thread.join();
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO Auto-generated method stub

	}
	@Override
	public void keyPressed(KeyEvent e) 
	{
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_RIGHT && !Left)
		{
			Right = true;
			Up = false;
			Down = false;
		}
		if (key == KeyEvent.VK_LEFT && !Right)
		{
			Left = true;
			Up = false;
			Down = false;
		}
		if (key == KeyEvent.VK_UP && !Down)
		{
			Right = false;
			Left = false;
			Up = true;
		}
		if (key == KeyEvent.VK_DOWN && !Up)
		{
			Right = false;
			Left = false;
			Down = true;
		}
		if (key == KeyEvent.VK_ENTER)
		{
			end();
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub

	}
}
