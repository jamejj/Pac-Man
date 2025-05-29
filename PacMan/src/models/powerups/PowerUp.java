package models.powerups;

import models.GameObject;
import models.GameState;

public class PowerUp extends GameObject {
    private PowerUpEnum powerUpEnum;

    public PowerUp(int row, int col, GameState gameState, PowerUpEnum powerUpEnum) {
        super(row, col, gameState, 0, "./images/powerup.jpg");
        this.powerUpEnum = powerUpEnum;
    }

    public PowerUpEnum getPowerUpEnum() {
        return powerUpEnum;
    }
}
