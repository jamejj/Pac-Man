package contollers;

import models.GameState;
import views.GameView;
import views.MainMenuView;

import javax.swing.*;

public class GameController {
    private GameView gameView;
    private GameState gameState;

    public void start() {
        gameState = new GameState();
        gameState.getCollisionManager().setOnGameOver(this::stopGame);
        gameView = new GameView(this);
    }

    public void startGame(int boardSize) {
        gameState.init(boardSize);
        gameState.startGame();
    }

    public GameState getGameState() {
        return gameState;
    }

    public void stopGame() {
        gameState.stopGame();
        gameView.stopKeyListener();
        String username = gameView.getUsername();
        gameView.dispose();
        new MainMenuView();
    }
}
