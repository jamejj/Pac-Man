package models;

import javax.swing.*;
import java.awt.*;

public abstract class GameObject {
    protected int row;
    protected int col;
    private int speed;
    private final Object speedLock;
    protected GameState gameState;
    private ImageIcon image;


    public GameObject(int row, int col, GameState gameState, int speed, String imagePath) {
        this.row = row;
        this.col = col;
        this.gameState = gameState;
        this.speed = speed;
        speedLock = new Object();

        ImageIcon icon = new ImageIcon(imagePath);
        image = new ImageIcon(icon.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH));
    }

    public int getRow() {
        return row;
    }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getCol() {
        return col;
    }

    public ImageIcon getImage() {
        return image;
    }

    public int getSpeed() {
        synchronized (speedLock) {
            return speed;
        }
    }

    public void setSpeed(int speed) {
        synchronized (speedLock) {
            System.out.println("Set speed to " + speed);
            this.speed = speed;
        }
    }
}
