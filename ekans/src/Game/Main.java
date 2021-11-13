package Game;

import javax.swing.JFrame;

public class Main 
{
	public Main()
	{
		JFrame frame = new JFrame();
		GamePanel newpanel = new GamePanel();
		frame.add(newpanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("EKANS");
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
	
	public static void main(String[] args)
	{
		new Main();
	}
}
