package com.cgi.tennis;
//Apply rule 4 (First class collections). Wrap the scoreTerms array in a dedicated class (ScoreTerms) with behavior.
public class Step6 implements TennisGame {

    private Score player1Score = new Score();
    private Score player2Score = new Score();
    private PlayerName player1Name;
    private PlayerName player2Name;
    private ScoreTerms scoreTerms = new ScoreTerms();

    public Step6(String player1Name, String player2Name) {
        this.player1Name = new PlayerName(player1Name);
        this.player2Name = new PlayerName(player2Name);
    }

    public String getScore() {
        if (isEarlyGame()) {
            return getEarlyGameScore();
        }
        return getLateGameScore();
    }

    private boolean isEarlyGame() {
        return player1Score.lessThan(4) && player2Score.lessThan(4) &&
                !(player1Score.plus(player2Score).getValue() == 6);
    }

    private String getEarlyGameScore() {
        String player1Term = scoreTerms.getTerm(player1Score);
        if (player1Score.equals(player2Score)) {
            return player1Term + "-All";
        }
        return player1Term + "-" + scoreTerms.getTerm(player2Score);
    }

    private String getLateGameScore() {
        if (player1Score.equals(player2Score)) {
            return "Deuce";
        }
        PlayerName leadingPlayer = getLeadingPlayerName();
        if (isAdvantage()) {
            return "Advantage " + leadingPlayer.getValue();
        }
        return "Win for " + leadingPlayer.getValue();
    }

    private PlayerName getLeadingPlayerName() {
        if (player1Score.greaterThan(player2Score)) {
            return player1Name;
        }
        return player2Name;
    }

    private boolean isAdvantage() {
        Score difference = player1Score.minus(player2Score);
        if (difference.getValue() < 0) {
            difference = player2Score.minus(player1Score);
        }
        return difference.getValue() * difference.getValue() == 1;
    }

    public void wonPoint(String playerName) {
        PlayerName winner = new PlayerName(playerName);
        if (winner.equals(player1Name)) {
            player1Score.increment();
            return;
        }
        player2Score.increment();
    }

}