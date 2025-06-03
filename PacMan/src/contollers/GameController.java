package contollers;

import models.GameState;
import models.scores.GameResult;
import models.scores.ScoreManager;
import views.GameView;
import views.MainMenuView;

import javax.swing.*;

public class GameController {
    private GameView gameView;
    private GameState gameState;
    private ScoreManager scoreManager;

    public void start() {
        gameState = new GameState();
        gameState.getCollisionManager().setOnGameOver(this::stopGame);

        gameView = new GameView(this);

        scoreManager = new ScoreManager();
        scoreManager.load();

        gameState.getPlayer().setOnNewScore(this.gameView::setScoreOnTitle);
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
        if(username != null) {
            scoreManager.addScore(new GameResult(username, gameState.getPlayer().getScore()));
            scoreManager.save();
        }
        gameView.dispose();
        new MainMenuView();
    }
}
