package models;

import java.util.Random;

public class Enemy extends GameObject {
    private static final int POWER_UP_INTERVAL_S = 5;
    private static final double POWER_UP_PROBABILITY = 0.25;
    private long lastTimeCreate;

    public Enemy(int row, int col, int speed, String imagePath) {
        super(row, col, speed, imagePath);
        lastTimeCreate = System.currentTimeMillis();
    }

    public PowerUp createPowerUp() {
        if(System.currentTimeMillis() - lastTimeCreate > POWER_UP_INTERVAL_S) {
            lastTimeCreate = System.currentTimeMillis();
            Random rand = new Random();
            PowerUp[] powerUps = PowerUp.values();

            if(rand.nextDouble() <= POWER_UP_PROBABILITY) {
                return powerUps[rand.nextInt(powerUps.length)];
            }
        }

        return null;
    }
}
