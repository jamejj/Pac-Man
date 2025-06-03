package models.collisions;

import models.Enemy;
import models.GameObject;
import models.GameState;
import models.Player;

public class CollisionManager implements Runnable {
    final private GameState gameState;
    private boolean running;
    private Runnable onGameOver;

    public CollisionManager(GameState gameState) {
        this.gameState = gameState;
        running = false;
    }

    public void runThread() {
        if(running) {
            return;
        }
        running = true;

        Thread thread = new Thread(this);
        thread.start();
    }

    public void stopThread() {
        running = false;
    }

    @Override
    public void run() {
        while(running) {
            updateCollision();

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void updateCollision() {
        if(gameState.isGameOver()) {
            return;
        }

        Player player = gameState.getPlayer();
        if(player.isInvisible()) {
            return;
        }

        for(GameObject object : gameState.findAllByPosition(player.getRow(), player.getCol())) {
            if(object instanceof Enemy) {
                gameState.gameOver();
                if(onGameOver != null) {
                    onGameOver.run();
                }
                break;
            }
        }
    }

    public void setOnGameOver(Runnable onGameOver) {
        this.onGameOver = onGameOver;
    }
}
