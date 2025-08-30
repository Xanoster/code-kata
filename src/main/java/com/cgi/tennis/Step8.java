package com.cgi.tennis;
//Apply rule 7 (Keep all entities small). Class is under 50 lines, but extract more methods/classes if needed.
// Split late game logic into a strategy class (GameState) to keep small.
public class Step8 implements TennisGame {

    private Score player1Score = new Score();
    private Score player2Score = new Score();
    private PlayerName player1Name;
    private PlayerName player2Name;
    private ScoreTerms scoreTerms = new ScoreTerms();

    public Step8(String player1Name, String player2Name) {
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
        boolean player1Under4 = player1Score.lessThan(4);
        boolean player2Under4 = player2Score.lessThan(4);
        Score totalScore = player1Score.plus(player2Score);
        int totalValue = totalScore.getValue();
        boolean notSix = totalValue != 6;
        return player1Under4 && player2Under4 && notSix;
    }

    private String getEarlyGameScore() {
        String player1Term = scoreTerms.getTerm(player1Score);
        boolean scoresEqual = player1Score.equals(player2Score);
        if (scoresEqual) {
            return player1Term + "-All";
        }
        String player2Term = scoreTerms.getTerm(player2Score);
        return player1Term + "-" + player2Term;
    }

    private String getLateGameScore() {
        boolean scoresEqual = player1Score.equals(player2Score);
        if (scoresEqual) {
            GameState state = new DeuceState();
            return state.getDescription();
        }
        PlayerName leadingPlayer = getLeadingPlayerName();
        boolean advantage = isAdvantage();
        GameState state;
        if (advantage) {
            state = new AdvantageState(leadingPlayer);
        } else {
            state = new WinState(leadingPlayer);
        }
        return state.getDescription();
    }

    private PlayerName getLeadingPlayerName() {
        boolean player1Leading = player1Score.greaterThan(player2Score);
        if (player1Leading) {
            return player1Name;
        }
        return player2Name;
    }

    private boolean isAdvantage() {
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
        PlayerName winner = new PlayerName(playerName);
        boolean isPlayer1 = winner.equals(player1Name);
        if (isPlayer1) {
            player1Score.increment();
            return;
        }
        player2Score.increment();
    }

}