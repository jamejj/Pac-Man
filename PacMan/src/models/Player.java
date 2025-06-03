package models;

import models.powerups.PowerUpEnum;

public class Player extends GameObject implements Movable {
    private static final String IMAGE_PATH = "./images/pacman.jpg";
    private static final int DEFAULT_SPEED_DELAY_MS = 250;
    private NewScoreListener onNewScore;

    private long lastTimeMove;
    private boolean invisible;
    private int score;

    public Player(int row, int col, GameState gameState, int speed) {
        super(row, col, gameState, speed, IMAGE_PATH);
        lastTimeMove = System.currentTimeMillis();
        invisible = false;
        score = 0;
        onNewScore = null;
    }

    public void setOnNewScore(NewScoreListener onNewScore) {
        this.onNewScore = onNewScore;
    }

    @Override
    public boolean up() {
        return move(row - 1, col);
    }

    @Override
    public boolean down() {
        return move(row + 1, col);
    }

    @Override
    public boolean left() {
        return move(row, col - 1);
    }

    @Override
    public boolean right() {
        return move(row, col + 1);
    }

    public void addScore(int score) {
        if(score > 0) {
            this.score += score;
            if(onNewScore != null) {
                onNewScore.newScore(this.score);
            }
        }
    }

    public int getScore() {
        return score;
    }

    public boolean isInvisible() {
        return invisible;
    }

    public void setInvisible(boolean invisible) {
        this.invisible = invisible;
    }

    private boolean move(int newRow, int newCol) {
        if(System.currentTimeMillis() - lastTimeMove < DEFAULT_SPEED_DELAY_MS * (long)getSpeed()) {
            return false;
        }

        if(gameState.canRunIntoPosition(newRow, newCol)) {
            col = newCol;
            row = newRow;

            lastTimeMove = System.currentTimeMillis();
            collectPowerUp();
            return true;
        }
        return false;
    }

    private void collectPowerUp() {
        PowerUpEnum powerUpEnum = gameState.popPowerUp(row, col);
        if(powerUpEnum != null) {
            gameState.getPowerUpManager().addPowerUp(powerUpEnum);
            addScore(100);
        }
    }
}
