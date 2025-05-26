package views;

import javax.swing.*;

public class HighScoresView extends JFrame {

    public HighScoresView() {
        initFrame();
        pack();
        setVisible(true);
    }

    public void initFrame() {
        setTitle("High Scores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);
    }
}
