package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class GameWindow {
    Timer timer;
    int second, minute;
    String dSecond, dMinute;
    JLabel counterLabel;
    DecimalFormat dFormat = new DecimalFormat("00");

    public GameWindow() {
        // Starting Frame
        ImageIcon image = new ImageIcon("snake.png");

        JLabel label = new JLabel();
        label.setText("The Snake Game we all love, but REVERSED");
        label.setIcon(image);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setForeground(new Color(0x00FF00));
        label.setFont(new Font("MV Boli", Font.PLAIN, 20));
        label.setIconTextGap(-25);

        JFrame startFrame = new JFrame();
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setTitle("EKANS");
        startFrame.pack();
        startFrame.setVisible(true);
        startFrame.setLocationRelativeTo(null);

        // Timer JFrame
        JFrame window = new JFrame();
        counterLabel = new JLabel();
        Font font = new Font("Serif", Font.PLAIN, 45);

        window.setSize(200, 100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Time Survived (s)");

        counterLabel.setHorizontalAlignment(JLabel.CENTER);
        counterLabel.setFont(font);

        window.add(counterLabel);
        window.setVisible(true);

        counterLabel.setText("00:00");
        second = 0;
        minute = 0;
        simpleTimer();
        timer.start();

        // Snake Game JFrame
        JFrame frame = new JFrame();
        GamePanel panel = new GamePanel(timer);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("EKANS");
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public void simpleTimer() {
        timer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                second++;
                dSecond = dFormat.format(second);
                dMinute = dFormat.format(minute);

                counterLabel.setText(dMinute + ":" + dSecond);

                if (second == 60) {
                    second = 0;
                    minute++;

                    dSecond = dFormat.format(second);
                    dMinute = dFormat.format(minute);
                    counterLabel.setText(minute + ":" + dSecond);
                }
            }

        });

    }
}
