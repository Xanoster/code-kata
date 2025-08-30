// src/test/java/com/cgi/tennis/TennisAllImplementationsTest.java
package com.cgi.tennis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.reflections.Reflections;

class TennisAllImplementationsTest {

    /**
     * This method provides all the test cases.
     * Each test case is a combination of player scores and the expected score string.
     */
    private static Stream<Arguments> getAllScores() {
        return Stream.of(
                Arguments.of(0, 0, "Love-All"),
                Arguments.of(1, 1, "Fifteen-All"),
                Arguments.of(2, 2, "Thirty-All"),
                Arguments.of(3, 3, "Deuce"),
                Arguments.of(4, 4, "Deuce"),
                Arguments.of(1, 0, "Fifteen-Love"),
                Arguments.of(0, 1, "Love-Fifteen"),
                Arguments.of(2, 0, "Thirty-Love"),
                Arguments.of(0, 2, "Love-Thirty"),
                Arguments.of(3, 0, "Forty-Love"),
                Arguments.of(0, 3, "Love-Forty"),
                Arguments.of(4, 0, "Win for player1"),
                Arguments.of(0, 4, "Win for player2"),
                Arguments.of(4, 3, "Advantage player1"),
                Arguments.of(3, 4, "Advantage player2"),
                Arguments.of(6, 4, "Win for player1"),
                Arguments.of(4, 6, "Win for player2")
        );
    }

    /**
     * This is the main test method.
     * JUnit will run this method for every combination of TennisGame class and score scenario.
     */
    @ParameterizedTest(name = "{0}: {1}-{2} => {3}")
    @MethodSource("gameImplementationsAndScores")
    void checkTennisGameScores(Class<? extends TennisGame> gameClass, int player1Score, int player2Score, String expectedScore) throws Exception {
        // Dynamically create an instance of the class to be tested
        TennisGame game = gameClass.getConstructor(String.class, String.class).newInstance("player1", "player2");

        // Award points to simulate the game state
        for (int i = 0; i < player1Score; i++) {
            game.wonPoint("player1");
        }
        for (int i = 0; i < player2Score; i++) {
            game.wonPoint("player2");
        }

        // Assert that the score is correct
        assertEquals(expectedScore, game.getScore());
    }

    /**
     * This is the magic method that provides all the test parameters.
     * It finds every class that implements TennisGame and pairs it with every score scenario.
     */
    private static Stream<Arguments> gameImplementationsAndScores() {
        // Find all classes in the package that implement TennisGame
        Reflections reflections = new Reflections("com.cgi.tennis");
        return reflections.getSubTypesOf(TennisGame.class).stream()
                .flatMap(gameClass -> getAllScores().map(scoreArgs -> {
                    Object[] originalArgs = scoreArgs.get();
                    // Create a new set of arguments: [GameClass, p1Score, p2Score, expectedScore]
                    return Arguments.of(gameClass, originalArgs[0], originalArgs[1], originalArgs[2]);
                }));
    }
}