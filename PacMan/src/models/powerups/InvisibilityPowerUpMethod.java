package models.powerups;

import models.GameState;

public class InvisibilityPowerUpMethod extends PowerUpMethod {
    public InvisibilityPowerUpMethod(long timeEnd) {
        super(timeEnd);
    }

    @Override
    public void apply(GameState gameState) {
        gameState.getPlayer().setInvisible(true);
    }

    @Override
    public void reverse(GameState gameState) {
        gameState.getPlayer().setInvisible(false);
    }
}
