package views;

import contollers.GameController;

import javax.swing.*;
import java.awt.*;

public class MainMenuView extends JFrame {


    public MainMenuView() {
        initFrame();
        frameComponents();
        pack();
        setVisible(true);
    }


    public void initFrame() {
        setSize(700, 250);
        setTitle("Pacman");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }


    public void frameComponents(){

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.BLACK);

        JPanel topPanel = createTopPanel();
        topPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        topPanel.setBackground(Color.BLACK);


        JPanel middlePanel = createMiddlePanel();
        middlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE,200));
        middlePanel.setBackground(Color.BLACK);

        JPanel bottomPanel = createBottomPanel();
        bottomPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE,200));
        bottomPanel.setBackground(Color.BLACK);

        mainPanel.add(topPanel);
        mainPanel.add(middlePanel);
        mainPanel.add(bottomPanel);

        add(mainPanel);
    }


    public JPanel createTopPanel(){
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.BLACK);
        JLabel title = new JLabel("Pacman", SwingConstants.CENTER);
        title.setFont(new Font("Bauhaus 93", Font.BOLD, 60));
        title.setForeground(Color.YELLOW);
        topPanel.add(title, BorderLayout.CENTER);
        return topPanel;
    }


    public JPanel createMiddlePanel(){
        Dimension buttonDimension = new Dimension(200, 40);

        JPanel middlePanel = new JPanel(new GridLayout(1,3,0,20));
//======================================================================================================================
        JButton newGame = new JButton("New Game");
        middlePanel.add(newGame);
        buttonsLook(newGame, buttonDimension);

        newGame.addActionListener(e ->  {
            new GameController().start();
            dispose();
        });


//======================================================================================================================
        JButton highScores = new JButton("High Scores");
        middlePanel.add(highScores);
        buttonsLook(highScores, buttonDimension);

        highScores.addActionListener(e -> {new HighScoresView();});
//======================================================================================================================
        JButton exit = new JButton("Exit");
        middlePanel.add(exit);
        buttonsLook(exit, buttonDimension);

        exit.addActionListener(e -> System.exit(0));
//======================================================================================================================

        return middlePanel;
    }


    public JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new GridLayout(1, 8, 10, 0)); 
        bottomPanel.setOpaque(false);

        int imageWidth = 50;
        int imageHeight = 50;

        bottomPanel.add(createScaledImage("./images/PacmanImg1.png", imageWidth, imageHeight));
        bottomPanel.add(createScaledImage("./images/PacmanImg2.png", imageWidth, imageHeight));
        bottomPanel.add(createScaledImage("./images/PacmanImg3.png", imageWidth, imageHeight));
        bottomPanel.add(createScaledImage("./images/PacmanImg1.png", imageWidth, imageHeight));
        bottomPanel.add(createScaledImage("./images/PacmanImg2.png", imageWidth, imageHeight));
        bottomPanel.add(createScaledImage("./images/PacmanImg3.png", imageWidth, imageHeight));
        bottomPanel.add(createScaledImage("./images/PacmanImg1.png", imageWidth, imageHeight));
        bottomPanel.add(createScaledImage("./images/PacmanImg2.png", imageWidth, imageHeight));

        return bottomPanel;
    }


    public JLabel createScaledImage(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path);
        Image scaled = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(scaled));
    }
    

    public void buttonsLook(JButton button,Dimension buttonDimension){
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBackground(Color.BLACK);
        button.setPreferredSize(buttonDimension);
        button.setFont(new Font("Bauhaus 93", Font.BOLD, 30));
        button.setForeground(Color.YELLOW);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);

    }

}
