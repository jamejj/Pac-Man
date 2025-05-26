package models;

public class Wall extends GameObject {
    private static final String IMAGE_PATH = "./images/pacman.jpg";

    public Wall(int row, int col) {
        super(row, col, 0, IMAGE_PATH);
    }
}
