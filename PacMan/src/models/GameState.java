package models;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private static final int ENEMIES_COUNT = 3;

    private List<Enemy> enemies;
    private List<Wall> walls;
    private Player player;
    private int boardSize;
    private GameTableModel model;

    public void init(int boardSize) {
        this.boardSize = boardSize;

        int middlePos = boardSize / 2;
        enemies = new ArrayList<>();

        String[] enemiesImages = {
                "./images/PacmanImg1.png",
                "./images/PacmanImg2.png",
                "./images/PacmanImg3.png",
        };
        for(int i = 0; i < ENEMIES_COUNT; i++) {
            enemies.add(new Enemy(middlePos, middlePos, 1, enemiesImages[i]));
        }

        player = new Player(0, 0, 1);
        model = new GameTableModel(this);

        walls = new ArrayList<>();
        walls.add(new Wall(2, 2));
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
        if(player.getRow() == row && player.getCol() == col) {
            return player;
        }
        for(Enemy enemy : enemies) {
            if(enemy.getRow() == row && enemy.getCol() == col) {
                return enemy;
            }
        }
        for(Wall wall : walls) {
            if(wall.getRow() == row && wall.getCol() == col) {
                return wall;
            }
        }
        return null;
    }
}
