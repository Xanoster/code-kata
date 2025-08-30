package com.cgi.tennis;

public class Player {
    private PlayerName name;
    private Score score = new Score();

    public Player(String name) {
        this.name = new PlayerName(name);
    }

    public void winPoint() {
        score.increment();
    }

    public Score getScore() {
        return score;
    }

    public PlayerName getName() {
        return name;
    }

    public boolean hasHigherScoreThan(Player other) {
        return score.greaterThan(other.score);
    }

    public boolean hasSameScoreAs(Player other) {
        return score.equals(other.score);
    }
}