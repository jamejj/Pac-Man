package views;

import contollers.GameController;
import models.GameState;
import models.powerups.PowerUpManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

public class GameView extends JFrame implements Runnable, KeyEventDispatcher {
    private int boardSize;
    private JTable gameTable;
    private boolean running;
    private final GameController gameController;

    public GameView(GameController gameController) {
        this.gameController = gameController;
        running = false;
        initFrame();
        boolean isCreated = selectBoardSize();
        if (isCreated) {
            initGame();
            KeyboardFocusManager.getCurrentKeyboardFocusManager()
                    .addKeyEventDispatcher(this);
            pack();
            initRefreshThread();
            setVisible(true);
        }
    }

    public void initFrame() {
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

            if (input == null) {
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

    private void initGame() {
        if (boardSize <= 0) {
            System.out.println("Board size must be greater than 0");
            return;
        }

        gameController.startGame(boardSize);
        gameTable = new JTable(gameController.getGameState().getModel());
        add(gameTable);
    }

    private void initRefreshThread() {
        running = true;
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (running) {
            gameTable.revalidate();
            gameTable.repaint();

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        GameState gameState = gameController.getGameState();

        Map<Integer, Runnable> actions = Map.of(
                KeyEvent.VK_UP, gameState.getPlayer()::up,
                KeyEvent.VK_DOWN, gameState.getPlayer()::down,
                KeyEvent.VK_LEFT, gameState.getPlayer()::left,
                KeyEvent.VK_RIGHT, gameState.getPlayer()::right
        );

        if(actions.containsKey(keyEvent.getKeyCode())) {
            actions.get(keyEvent.getKeyCode()).run();
            return true;
        }

        return false;
    }

    public void stopKeyListener() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .removeKeyEventDispatcher(this);
    }

    public String getUsername() {
        return JOptionPane.showInputDialog(
                this,
                "Enter your name:",
                "Game over",
                JOptionPane.QUESTION_MESSAGE
        );
    }
}
