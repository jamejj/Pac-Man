package models;

import models.collisions.CollisionManager;
import models.powerups.PowerUp;
import models.powerups.PowerUpEnum;
import models.powerups.PowerUpManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class GameState {
    private static final int ENEMIES_COUNT = 4;

    private final List<Enemy> enemies;
    private final List<PowerUp> powerUps;
    private final List<Wall> walls;
    private final Player player;
    private int boardSize;
    private final GameTableModel model;
    private final PowerUpManager powerUpManager;
    private final CollisionManager collisionManager;
    private boolean gameOver;

    public GameState() {
        powerUpManager = new PowerUpManager(this);
        collisionManager = new CollisionManager(this);
        enemies = new ArrayList<>();
        powerUps = new ArrayList<>();
        walls = new ArrayList<>();
        gameOver = false;
        player = new Player(0, 0, this, 1);
        model = new GameTableModel(this);
    }

    public void init(int boardSize) {
        this.boardSize = boardSize;

        int middlePos = boardSize / 2;
        String[] enemiesImages = {
                "./images/PacmanImg1.png",
                "./images/PacmanImg2.png",
                "./images/PacmanImg3.png",
                "./images/PacmanImg1.png"
        };
        for(int i = 0; i < ENEMIES_COUNT; i++) {
            enemies.add(new Enemy(middlePos, middlePos, this, 2, enemiesImages[i]));
        }

        createWalls();
    }

    private void createWalls() {
        int maxDepth = boardSize / 3;
        for(int depth = 1; depth <= maxDepth; depth += 2) {
            for(int i = depth; i < boardSize - depth; i++) {
                if(i != boardSize / 2) {
                    walls.add(new Wall(depth, i, this));
                    walls.add(new Wall(i, depth, this));
                    walls.add(new Wall(i, boardSize - depth - 1, this));
                    walls.add(new Wall(boardSize - depth - 1, i, this));
                }
            }
        }
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

    public PowerUpManager getPowerUpManager() {
        return powerUpManager;
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

    public List<GameObject> findAllByPosition(int row, int col) {
        List<GameObject> allObjects = new ArrayList<>();
        allObjects.add(player);
        allObjects.addAll(enemies);
        allObjects.addAll(walls);
        allObjects.addAll(powerUps);

        return allObjects
                .stream()
                .filter(obj -> obj.getCol() == col && obj.getRow() == row)
                .toList();
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
        powerUpManager.runThread();
        collisionManager.runThread();
    }

    public void stopGame() {
        enemies.forEach(Enemy::stopThread);
        powerUpManager.stopThread();
        collisionManager.stopThread();
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

    public void gameOver() {
        System.out.println("Game Over");
        gameOver = true;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public CollisionManager getCollisionManager() {
        return collisionManager;
    }
}
