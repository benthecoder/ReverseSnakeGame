package Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class StartPage implements ActionListener {
    JFrame frame = new JFrame();
    JButton myButton = new JButton("PLAY NOW");

    public StartPage() {

        Border border = BorderFactory.createLineBorder(Color.green, 3);

        myButton.setBounds(100, 160, 200, 40);
        myButton.setFocusable(false);
        myButton.addActionListener(this);


        frame.add(myButton);
        frame.setTitle("ENAKS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        if (e.getSource() == myButton) {
            GameWindow gwindow = new GameWindow();
            frame.setVisible(false);
        }
    }
}