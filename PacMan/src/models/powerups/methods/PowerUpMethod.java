package models.powerups.methods;

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

    public boolean isExpired() {
        return System.currentTimeMillis() >= timeEnd;
    }

    public abstract void apply(GameState gameState);
    public abstract void reverse(GameState gameState);
}
