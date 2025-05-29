package models.powerups;

import models.GameState;

public abstract class PowerUpMethod {
    private long timeEnd;

    public PowerUpMethod(long timeEnd) {
        this.timeEnd = timeEnd;
    }

    public long getTimeEnd() {
        return timeEnd;
    }

    public void addTime(long time) {
        this.timeEnd += time;
    }

    public abstract void apply(GameState gameState);
    public abstract void reverse(GameState gameState);
}
