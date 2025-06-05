package models;

public class Wall extends GameObject {
    private static final String IMAGE_PATH = "./images/wall.png";

    public Wall(int row, int col, GameState gameState) {
        super(row, col, gameState, 0, IMAGE_PATH);
    }
}
