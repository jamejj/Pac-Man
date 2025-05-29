package models;

import javax.swing.*;
import java.awt.*;

public abstract class GameObject {
    protected int row;
    protected int col;
    protected int speed;
    protected GameState gameState;
    private ImageIcon image;


    public GameObject(int row, int col, GameState gameState, int speed, String imagePath) {
        this.row = row;
        this.col = col;
        this.gameState = gameState;
        this.speed = speed;

        ImageIcon icon = new ImageIcon(imagePath);
        image = new ImageIcon(icon.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH));
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public ImageIcon getImage() {
        return image;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
