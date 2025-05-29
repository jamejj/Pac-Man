package models;

import models.powerups.PowerUp;
import models.powerups.PowerUpEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameState {
    private static final int ENEMIES_COUNT = 3;

    private List<Enemy> enemies;
    private List<PowerUp> powerUps;
    private List<Wall> walls;
    private Player player;
    private int boardSize;
    private GameTableModel model;

    public void init(int boardSize) {
        this.boardSize = boardSize;

        enemies = new ArrayList<>();
        powerUps = new ArrayList<>();
        walls = new ArrayList<>();

        int middlePos = boardSize / 2;
        String[] enemiesImages = {
                "./images/PacmanImg1.png",
                "./images/PacmanImg2.png",
                "./images/PacmanImg3.png",
        };
        for(int i = 0; i < ENEMIES_COUNT; i++) {
            enemies.add(new Enemy(middlePos, middlePos, this, 2, enemiesImages[i]));
        }

        player = new Player(0, 0, this, 1);
        model = new GameTableModel(this);

        walls.add(new Wall(2, 2, this));
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public Player getPlayer() {
        return player;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public GameTableModel getModel() {
        return model;
    }

    public GameObject findByPosition(int row, int col) {
        List<GameObject> allObjects = new ArrayList<>();
        allObjects.add(player);
        allObjects.addAll(enemies);
        allObjects.addAll(walls);
        allObjects.addAll(powerUps);

        return allObjects
                .stream()
                .filter(obj -> obj.getCol() == col && obj.getRow() == row)
                .findFirst()
                .orElse(null);
    }

    public boolean canRunIntoPosition(int row, int col) {
        if(row < 0 || row >= boardSize || col < 0 || col >= boardSize) {
            return false;
        }
        return walls
                .stream()
                .noneMatch(w -> w.getRow() == row && w.getCol() == col);
    }

    public void startGame() {
        enemies.forEach(Enemy::runThread);
    }

    public void stopGame() {
        enemies.forEach(Enemy::stopThread);
    }

    public void addPowerUp(PowerUp powerUp) {
        synchronized (powerUps) {
            powerUps.add(powerUp);
        }
    }

    public PowerUpEnum popPowerUp(int row, int col) {
        synchronized (powerUps) {
            Optional<PowerUp> powerUp = powerUps.stream().filter(p -> p.getRow() == row && p.getCol() == col).findFirst();
            if(powerUp.isPresent()) {
                PowerUpEnum powerUpEnum = powerUp.get().getPowerUpEnum();
                powerUps.remove(powerUp.get());
                return powerUpEnum;
            }
        }

        return null;
    }
}
