package com.cgi.tennis;
//rule 6 (Don’t abbreviate). Rename variables and method internals to full, descriptive names without abbreviations.
public class Step2 implements TennisGame {

    private int player1Score;
    private int player2Score;
    private String player1Name;
    private String player2Name;

    public Step2(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public String getScore() {
        String score;
        if (player1Score < 4 && player2Score < 4 && !(player1Score + player2Score == 6)) {
            String[] scoreTerms = new String[]{"Love", "Fifteen", "Thirty", "Forty"};
            score = scoreTerms[player1Score];
            return (player1Score == player2Score) ? score + "-All" : score + "-" + scoreTerms[player2Score];
        } else {
            if (player1Score == player2Score)
                return "Deuce";
            score = player1Score > player2Score ? player1Name : player2Name;
            return ((player1Score - player2Score) * (player1Score - player2Score) == 1) ? "Advantage " + score : "Win for " + score;
        }
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(this.player1Name))
            this.player1Score += 1;
        else
            this.player2Score += 1;

    }

}