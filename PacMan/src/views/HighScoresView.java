package views;

import models.scores.GameResult;
import models.scores.ScoreManager;

import javax.swing.*;
import java.util.Comparator;

public class HighScoresView extends JFrame {

    public HighScoresView() {
        initFrame();
        pack();
        setVisible(true);
    }

    public void initFrame() {
        setTitle("High Scores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null);

        ScoreManager scoreManager = new ScoreManager();
        scoreManager.load();

        DefaultListModel<GameResult> model = new DefaultListModel<>();
        scoreManager
                .getResultList()
                .stream()
                .sorted(Comparator.comparing(GameResult::getScore).reversed())
                .forEach(model::addElement);

        JList<GameResult> list = new JList<>(model);
        add(new JScrollPane(list));
        pack();
    }
}
