package Game;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener
{
	private static final long serialVersionUID = 1L;
	private final int WIDTH = 500;
	private final int HEIGHT = 500;
	private int xcoordinate = 10;
	private int ycoordinate = 10;
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
	private int numberOfTicks = 0;
	private Block block;
	private Random rand;
	private int Scores;
	private int times = 0;
	private int nextLevel = 60;
	private Snake snake;
	private ArrayList<ArrayList<Snake>> allSnakes;
	public GamePanel()
	{
		setFocusable(true);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		addKeyListener(this);
		allSnakes = new ArrayList<ArrayList<Snake>>();
		rand = new Random();
		block = new Block(xcoordinate, ycoordinate, 10);
		begin();
		Timer timer = new Timer();
		timer.schedule(new TimerTask() 
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
		for (int i = 0; i < allSnakes.size(); ++i)
		{
			allSnakes.get(i).get(0).draw(background);
		}
		block .draw(background);
	}

	public void randomSnakeMovement()
	{
		Random ran = new Random();
		int randomDirection = ran.nextInt(4);

		if(randomDirection==0 && !Left)
		{
			Right = true;
			Up = false;
			Down=false;
		}
		if(randomDirection==1&& !Right)
		{
			Left = true;
			Up = false;
			Down = false;
		}
		if(randomDirection==2&& !Down)
		{
			Up = true;
			Left = false;
			Right = false;
		}
		if(randomDirection==3&& !Up)
		{
			Down = true;
			Left = false;
			Right = false;
		}
	}

	public void ticks()
	{
		ArrayList<Snake> numberSnakes = new ArrayList<Snake>();
		if (allSnakes.size() == 0)
		{
			int x = rand.nextInt(49);
			int y = rand.nextInt(49);
			int randomnumbersnake = rand.nextInt(10);
			snake = new Snake(x, y, 10);
			numberSnakes.add(snake);
			for (int i = 0; i < randomnumbersnake; ++i)
			{
				snake = new Snake(x, y + i, 10);
				numberSnakes.add(snake);
			}
			allSnakes.add(numberSnakes);
		}
		++numberOfTicks;
		if (numberOfTicks > 1000000)
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
			times += 1;
			numberOfTicks = 0;
			block = new Block(xcoordinate, ycoordinate, 10);
		}
		if(xcoordinate < 0)
		{
			Right = true;
			Up = false;
			Down=false;
		}

		if( xcoordinate > 99)
		{
			Left = true;
			Up = false;
			Down=false;
		}

		if(ycoordinate <0)
		{
			Down = true;
			Left = false;
			Right = false;
		}

		if(ycoordinate > 99)
		{
			Up = true;
			Left = false;
			Right = false;
		}
		//		if (times > nextLevel)
		//		{
		//			++Scores;
		//			nextLevel *= 2;
		//			int x = rand.nextInt(49) + 1;
		//			int y = rand.nextInt(49) + 1;
		//			for (int i = 0; i < 5; ++i)
		//			{
		//				x += 1;
		//				y += 1;
		//				snake = new Snake(x, y, 10);
		//				numberSnakes.add(snake);
		//			}
		//			allSnakes.add(numberSnakes);
		//		}

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
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub

	}
}
