package com.cgi.tennis;

public class PlayerName {
    private final String value;

    public PlayerName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public boolean equals(PlayerName other) {
        return this.value.equals(other.value);
    }
}