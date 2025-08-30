package com.cgi.tennis;

public class Score {
    private int value;

    public Score() {
        this.value = 0;
    }

    public void increment() {
        value += 1;
    }

    public int getValue() {
        return value;
    }

    public boolean lessThan(int other) {
        return value < other;
    }

    public boolean equals(Score other) {
        return value == other.value;
    }

    public Score plus(Score other) {
        Score sum = new Score();
        sum.value = this.value + other.value;
        return sum;
    }

    public Score minus(Score other) {
        Score diff = new Score();
        diff.value = this.value - other.value;
        return diff;
    }

    public boolean greaterThan(Score other) {
        return value > other.value;
    }
}