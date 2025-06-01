package models.powerups;

import models.GameState;
import models.powerups.methods.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PowerUpManager implements Runnable {
    private static final int DEFAULT_POWER_UP_TIME_MS = 5000;

    final private Map<PowerUpEnum, PowerUpMethod> powerUpMethods;
    final private GameState gameState;
    private boolean running;
    private Thread thread;

    public PowerUpManager(GameState gameState) {
        this.gameState = gameState;
        powerUpMethods = new HashMap<>();
        running = false;
    }

    public void addPowerUp(PowerUpEnum powerUp) {
        synchronized (powerUpMethods) {
            PowerUpMethod powerUpMethod = powerUpMethods.get(powerUp);
            if (powerUpMethod == null) {
                long timeToEnd = System.currentTimeMillis() + DEFAULT_POWER_UP_TIME_MS;
                powerUpMethod = switch (powerUp) {
                    case SPEED_50 -> new SpeedPowerUpMethod(timeToEnd);
                    case STOP_ENEMY -> new StopEnemyPowerUpMethod(timeToEnd);
                    case SLOW_ENEMY -> new SlowEnemyPowerUpMethod(timeToEnd);
                    case INVISIBILITY -> new InvisibilityPowerUpMethod(timeToEnd);
                    case TELEPORT -> new TeleportPowerUpMethod();
                    default -> null;
                };

                powerUpMethod.apply(gameState);
                powerUpMethods.put(powerUp, powerUpMethod);
                System.out.println("PowerUp " + powerUp + " added");
            } else {
                powerUpMethod.addTime(DEFAULT_POWER_UP_TIME_MS);
                System.out.println("PowerUp " + powerUp + " extended");
            }
        }
    }

    public void updatePowerUpMethods() {
        synchronized (powerUpMethods) {
            Set<PowerUpEnum> currentPowerUps = new HashSet<>(powerUpMethods.keySet());

            for (PowerUpEnum powerUp : currentPowerUps) {
                PowerUpMethod powerUpMethod = powerUpMethods.get(powerUp);
                if(powerUpMethod.isExpired()) {
                    powerUpMethod.reverse(gameState);
                    powerUpMethods.remove(powerUp);
                    System.out.println("Power up expired " + powerUp);
                }
            }
        }
    }

    @Override
    public void run() {
        while (running) {
            updatePowerUpMethods();

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void runThread() {
        if(running) {
            return;
        }

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stopThread() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        thread = null;
    }
}
