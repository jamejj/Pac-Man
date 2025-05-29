package models.powerups;

import models.GameState;
import models.Player;

public class SpeedPowerUpMethod extends PowerUpMethod {
    public SpeedPowerUpMethod(long timeEnd) {
        super(timeEnd);
    }

    @Override
    public void apply(GameState gameState) {
        Player player = gameState.getPlayer();
        player.setSpeed(player.getSpeed() / 2);
    }

    @Override
    public void reverse(GameState gameState) {
        Player player = gameState.getPlayer();
        player.setSpeed(player.getSpeed() * 2);
    }
}
