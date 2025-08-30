package com.cgi.tennis;
//Apply rule 8 (No classes with more than two instance variables). Group into Player objects (each with name and
// score, 2 vars).
// Main class has two players and scoreTerms (but limit to 2: perhaps move scoreTerms to static or separate).
public class Step9 implements TennisGame {

    private Player player1;
    private Player player2;

    public Step9(String player1Name, String player2Name) {
        this.player1 = new Player(player1Name);
        this.player2 = new Player(player2Name);
    }

    public String getScore() {
        if (isEarlyGame()) {
            return getEarlyGameScore();
        }
        return getLateGameScore();
    }

    private boolean isEarlyGame() {
        Score player1Score = player1.getScore();
        Score player2Score = player2.getScore();
        boolean player1Under4 = player1Score.lessThan(4);
        boolean player2Under4 = player2Score.lessThan(4);
        Score totalScore = player1Score.plus(player2Score);
        int totalValue = totalScore.getValue();
        boolean notSix = totalValue != 6;
        return player1Under4 && player2Under4 && notSix;
    }

    private String getEarlyGameScore() {
        ScoreTerms scoreTerms = new ScoreTerms();
        String player1Term = scoreTerms.getTerm(player1.getScore());
        boolean scoresEqual = player1.hasSameScoreAs(player2);
        if (scoresEqual) {
            return player1Term + "-All";
        }
        String player2Term = scoreTerms.getTerm(player2.getScore());
        return player1Term + "-" + player2Term;
    }

    private String getLateGameScore() {
        boolean scoresEqual = player1.hasSameScoreAs(player2);
        if (scoresEqual) {
            GameState state = new DeuceState();
            return state.getDescription();
        }
        Player leadingPlayer = getLeadingPlayer();
        boolean advantage = isAdvantage();
        GameState state;
        if (advantage) {
            state = new AdvantageState(leadingPlayer.getName());
        } else {
            state = new WinState(leadingPlayer.getName());
        }
        return state.getDescription();
    }

    private Player getLeadingPlayer() {
        if (player1.hasHigherScoreThan(player2)) {
            return player1;
        }
        return player2;
    }

    private boolean isAdvantage() {
        Score player1Score = player1.getScore();
        Score player2Score = player2.getScore();
        Score difference = player1Score.minus(player2Score);
        int diffValue = difference.getValue();
        if (diffValue < 0) {
            difference = player2Score.minus(player1Score);
            diffValue = difference.getValue();
        }
        int squared = diffValue * diffValue;
        return squared == 1;
    }

    public void wonPoint(String playerName) {
        PlayerName winnerName = new PlayerName(playerName);
        if (winnerName.equals(player1.getName())) {
            player1.winPoint();
            return;
        }
        player2.winPoint();
    }

}