package views;

import models.GameState;
import models.GameTableModel;

import javax.swing.*;

public class GameView extends JFrame{
    private int boardSize;
    private GameState gameState;
    private JTable gameTable;

    public GameView(){
        initFrame();
        boolean isCreated = selectBoardSize();
        if(isCreated) {
            pack();
            initGameBoard();
            setVisible(true);
        }
    }

    public void initFrame(){
        setTitle("Pacman-Gameplay");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);


    }

    public boolean selectBoardSize() {
        int size = 0;
        boolean validInput = false;

        while (!validInput) {
            String input = JOptionPane.showInputDialog(
                    this,
                    "Enter board size (10-100):",
                    "New Game",
                    JOptionPane.QUESTION_MESSAGE
            );

            if (input==null) {
                this.dispose();
                new MainMenuView().setVisible(true);
                return false;
            }

            try {
                size = Integer.parseInt(input);
                if (size >= 10 && size <= 100) {
                    validInput = true;
                    boardSize = size;
                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "Size must be between 10 and 100",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a number",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        }
        return true;
    }

    private void initGameBoard() {
        if(boardSize <= 0) {
            System.out.println("Board size must be greater than 0");
            return;
        }

        gameState = new GameState();
        gameState.init(boardSize);
        gameTable = new JTable(gameState.getModel());
        add(gameTable);
    }

}
