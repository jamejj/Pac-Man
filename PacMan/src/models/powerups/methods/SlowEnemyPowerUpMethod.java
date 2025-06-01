package models.powerups.methods;

import models.GameState;

public class SlowEnemyPowerUpMethod extends PowerUpMethod {
    public SlowEnemyPowerUpMethod(long timeEnd) {
        super(timeEnd);
    }

    @Override
    public void apply(GameState gameState) {
        gameState.getEnemies().forEach(e -> e.setSpeed(2));
    }

    @Override
    public void reverse(GameState gameState) {
        gameState.getEnemies().forEach(e -> e.setSpeed(1));
    }
}
