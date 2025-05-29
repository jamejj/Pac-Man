package models;

import models.powerups.PowerUp;
import models.powerups.PowerUpEnum;

import java.util.Random;

public class Enemy extends GameObject implements Runnable, Movable {
    private static final int POWER_UP_INTERVAL_MS = 5000;
    private static final int MOVE_INTERVAL_MS = 500;
    private static final double POWER_UP_PROBABILITY = 0.25;
    private long lastTimeCreate;
    private long lastTimeMove;
    private boolean running;

    public Enemy(int row, int col, GameState gameState, int speed, String imagePath) {
        super(row, col, gameState, speed, imagePath);
        lastTimeCreate = System.currentTimeMillis();
        lastTimeMove = System.currentTimeMillis();
        running = false;
    }

    public PowerUpEnum createPowerUp() {
        if(System.currentTimeMillis() - lastTimeCreate > POWER_UP_INTERVAL_MS) {
            lastTimeCreate = System.currentTimeMillis();
            Random rand = new Random();
            PowerUpEnum[] powerUpEnums = PowerUpEnum.values();

            if(rand.nextDouble() <= POWER_UP_PROBABILITY) {
                System.out.println("Power Up created");
                return powerUpEnums[rand.nextInt(powerUpEnums.length)];
            }
        }

        return null;
    }

    public void runThread() {
        if(running) {
            return;
        }
        running = true;
        new Thread(this).start();
    }

    public void stopThread() {
        running = false;
    }

    @Override
    public void run() {
        while(running) {
            randomMove();

            PowerUpEnum powerUp = createPowerUp();
            if(powerUp != null) {
                gameState.addPowerUp(new PowerUp(row, col, gameState, powerUp));
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean up() {
        if(gameState.canRunIntoPosition(row-1, col)) {
            row--;
            return true;
        }
        return false;
    }

    @Override
    public boolean down() {
        if(gameState.canRunIntoPosition(row+1, col)) {
            row++;
            return true;
        }
        return false;
    }

    @Override
    public boolean left() {
        if(gameState.canRunIntoPosition(row, col-1)) {
            col--;
            return true;
        }
        return false;
    }

    @Override
    public boolean right() {
        if(gameState.canRunIntoPosition(row, col+1)) {
            col++;
            return true;
        }
        return false;
    }

    public void randomMove() {
        if(System.currentTimeMillis() - lastTimeMove < MOVE_INTERVAL_MS * (long)speed) {
            return;
        }
        lastTimeMove = System.currentTimeMillis();

        Random rand = new Random();

        boolean done = false;
        while(!done) {
            int choice = rand.nextInt(4);
            done = switch (choice) {
                case 0 -> up();
                case 1 -> down();
                case 2 -> left();
                case 3 -> right();
                default -> done;
            };
        }
    }
}
