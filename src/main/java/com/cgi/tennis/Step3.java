package com.cgi.tennis;

//rule 1 (One level of indentation per method).
//Extract nested logic in getScore into separate methods to reduce indentation levels.

public class Step3 implements TennisGame {

    private int player1Score;
    private int player2Score;
    private String player1Name;
    private String player2Name;

    public Step3(String player1Name, String player2Name) {
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
        String leadingPlayer = player1Score > player2Score ? player1Name : player2Name;
        if ((player1Score - player2Score) * (player1Score - player2Score) == 1) {
            return "Advantage " + leadingPlayer;
        }
        return "Win for " + leadingPlayer;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(this.player1Name))
            this.player1Score += 1;
        else
            this.player2Score += 1;

    }

}