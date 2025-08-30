package com.cgi.tennis;

public abstract class GameState {
    protected PlayerName leadingPlayer;

    public GameState(PlayerName leadingPlayer) {
        this.leadingPlayer = leadingPlayer;
    }

    public abstract String getDescription();
}

class DeuceState extends GameState {
    public DeuceState() {
        super(null);
    }

    @Override
    public String getDescription() {
        return "Deuce";
    }
}

class AdvantageState extends GameState {
    public AdvantageState(PlayerName leadingPlayer) {
        super(leadingPlayer);
    }

    @Override
    public String getDescription() {
        return "Advantage " + leadingPlayer.getValue();
    }
}

class WinState extends GameState {
    public WinState(PlayerName leadingPlayer) {
        super(leadingPlayer);
    }

    @Override
    public String getDescription() {
        return "Win for " + leadingPlayer.getValue();
    }
}