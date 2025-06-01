package models.powerups.methods;

import models.GameObject;
import models.GameState;

import java.util.Random;

public class TeleportPowerUpMethod extends PowerUpMethod {
    public TeleportPowerUpMethod() {
        super(0L);
    }

    @Override
    public void apply(GameState gameState) {
        GameObject obj;
        int row, col;

        do {
            Random rand = new Random();
            row = rand.nextInt(gameState.getBoardSize());
            col = rand.nextInt(gameState.getBoardSize());
            obj = gameState.findByPosition(row, col);
        } while(obj != null);

        gameState.getPlayer().setPosition(row, col);
    }

    @Override
    public void reverse(GameState gameState) {

    }
}
