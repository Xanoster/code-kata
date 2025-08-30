package com.cgi.tennis;
//Apply rule 2 (Don’t use the ELSE keyword)
public class Step4 implements TennisGame {

    private int player1Score;
    private int player2Score;
    private String player1Name;
    private String player2Name;

    public Step4(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public String getScore() {
        if (isEarlyGame()) {
            return getEarlyGameScore();
        }
        return getLateGameScore();
    }

    private boolean isEarlyGame() {
        return player1Score < 4 && player2Score < 4 && !(player1Score + player2Score == 6);
    }

    private String getEarlyGameScore() {
        String[] scoreTerms = new String[]{"Love", "Fifteen", "Thirty", "Forty"};
        String player1Term = scoreTerms[player1Score];
        if (player1Score == player2Score) {
            return player1Term + "-All";
        }
        return player1Term + "-" + scoreTerms[player2Score];
    }

    private String getLateGameScore() {
        if (player1Score == player2Score) {
            return "Deuce";
        }
        String leadingPlayer = getLeadingPlayerName();
        if (isAdvantage()) {
            return "Advantage " + leadingPlayer;
        }
        return "Win for " + leadingPlayer;
    }

    private String getLeadingPlayerName() {
        if (player1Score > player2Score) {
            return player1Name;
        }
        return player2Name;
    }

    private boolean isAdvantage() {
        int difference = Math.abs(player1Score - player2Score);
        return difference * difference == 1;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name)) {
            player1Score += 1;
            return;
        }
        player2Score += 1;
    }

}