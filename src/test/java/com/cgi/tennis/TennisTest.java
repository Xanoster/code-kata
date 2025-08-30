package com.cgi.tennis;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.reflections.Reflections;

class TennisTest {

    static final List<ExpectedResult> getAllScores() {
        return Arrays.asList(
                of(0, 0, "Love-All"),
                of(1, 1, "Fifteen-All"),
                of(2, 2, "Thirty-All"),
                of(3, 3, "Deuce"),
                of(4, 4, "Deuce"),
                of(1, 0, "Fifteen-Love"),
                of(0, 1, "Love-Fifteen"),
                of(2, 0, "Thirty-Love"),
                of(0, 2, "Love-Thirty"),
                of(3, 0, "Forty-Love"),
                of(0, 3, "Love-Forty"),
                of(4, 0, "Win for player1"),
                of(0, 4, "Win for player2"),

                of(2, 1, "Thirty-Fifteen"),
                of(1, 2, "Fifteen-Thirty"),
                of(3, 1, "Forty-Fifteen"),
                of(1, 3, "Fifteen-Forty"),
                of(4, 1, "Win for player1"),
                of(1, 4, "Win for player2"),

                of(3, 2, "Forty-Thirty"),
                of(2, 3, "Thirty-Forty"),
                of(4, 2, "Win for player1"),
                of(2, 4, "Win for player2"),

                of(4, 3, "Advantage player1"),
                of(3, 4, "Advantage player2"),
                of(5, 4, "Advantage player1"),
                of(4, 5, "Advantage player2"),
                of(15, 14, "Advantage player1"),
                of(14, 15, "Advantage player2"),

                of(6, 4, "Win for player1"),
                of(4, 6, "Win for player2"),
                of(16, 14, "Win for player1"),
                of(14, 16, "Win for player2"));
    }

    @ParameterizedTest
    @MethodSource("refactoringSteps")
    public void checkTennisGameToBeRefactored(Class<? extends TennisGame> clazz, int pointsPlayer1, int pointsPlayer2, String exepctedResult) {
        TennisGame game = new ToBeRefactored("player1", "player2");
        checkAllScores(game, pointsPlayer1, pointsPlayer2, "player1", "player2", exepctedResult);
    }

    private static class ExpectedResult {
        public ExpectedResult(int pointsPlayer1, int pointsPlayer2, String result) {
            super();
            this.pointsPlayer1 = pointsPlayer1;
            this.pointsPlayer2 = pointsPlayer2;
            this.result = result;
        }

        public int pointsPlayer1;
        public int pointsPlayer2;
        public String result;
    }

    static ExpectedResult of(int pointsPlayer1, int pointsPlayer2, String result) {
        return new ExpectedResult(pointsPlayer1, pointsPlayer2, result);
    }

    public void checkAllScores(TennisGame game, int pointsPlayer1, int pointsPlayer2, String namePlayer1, String namePlayer2, String result) {
        int highestScore = Math.max(pointsPlayer1, pointsPlayer2);
        for (int i = 0; i < highestScore; i++) {
            if (i < pointsPlayer1)
                game.wonPoint(namePlayer1);
            if (i < pointsPlayer2)
                game.wonPoint(namePlayer2);
        }
        assertThat(result, CoreMatchers.equalTo(game.getScore()));
    }

    public static Stream<Arguments> refactoringSteps() {
        Reflections reflections = new Reflections("com.cgi.tennis");
        Set<Class<? extends TennisGame>> subTypesOf = reflections.getSubTypesOf(TennisGame.class);
        List<Arguments> asList = new ArrayList<>();
        for (ExpectedResult result : getAllScores()) {
            for (Class<? extends TennisGame> clazz : subTypesOf) {
                asList.add(Arguments.of(clazz, result.pointsPlayer1, result.pointsPlayer2, result.result));
            }
        }
        return asList.stream();
    }
}
