package models.powerups;

import models.GameState;

public class StopEnemyPowerUpMethod extends PowerUpMethod {
    public StopEnemyPowerUpMethod(long timeEnd) {
        super(timeEnd);
    }

    @Override
    public void apply(GameState gameState) {
        gameState.getEnemies().forEach(e -> e.setSpeed(100000000));
    }

    @Override
    public void reverse(GameState gameState) {
        gameState.getEnemies().forEach(e -> e.setSpeed(1));
    }
}
