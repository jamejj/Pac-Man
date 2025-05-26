package models;

import java.util.ArrayList;
import java.util.List;

public class Player extends GameObject {
    private static final String IMAGE_PATH = "./images/pacman.jpg";
    private List<PowerUp> powerUps;

    public Player(int row, int col, int speed) {
        super(row, col, speed, IMAGE_PATH);
        powerUps = new ArrayList<PowerUp>();
    }

    public void addPowerUp(PowerUp powerUp) {
        powerUps.add(powerUp);
    }
}
