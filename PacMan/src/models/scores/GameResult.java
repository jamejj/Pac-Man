package models.scores;

import java.io.Serializable;

public class GameResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String username;
    private final int score;

    public GameResult(String username, int score) {
        this.username = username;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    public String toString() {
        return username + " " + score + " points";
    }
}
