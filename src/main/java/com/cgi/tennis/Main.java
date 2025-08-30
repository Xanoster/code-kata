package com.cgi.tennis;

public class Main {
    public static void main(String[] args) {
        // Create a new tennis game with two players
        TennisGame game = new Step2("Player 1", "Player 2");

        // Simulate a game and print the score after each point
        System.out.println("Initial Score: " + game.getScore()); // Love-All

        game.wonPoint("Player 1");
        System.out.println("Player 1 scores: " + game.getScore()); // Fifteen-Love

        game.wonPoint("Player 2");
        System.out.println("Player 2 scores: " + game.getScore()); // Fifteen-All

        game.wonPoint("Player 1");
        game.wonPoint("Player 1");
        System.out.println("Player 1 scores twice: " + game.getScore()); // Forty-Fifteen

        game.wonPoint("Player 2");
        game.wonPoint("Player 2");
        System.out.println("Player 2 scores twice: " + game.getScore()); // Deuce

        game.wonPoint("Player 1");
        System.out.println("Player 1 scores: " + game.getScore()); // Advantage Player 1

        game.wonPoint("Player 2");
        System.out.println("Player 2 scores: " + game.getScore()); // Deuce

        game.wonPoint("Player 2");
        System.out.println("Player 2 scores: " + game.getScore()); // Advantage Player 2

        game.wonPoint("Player 2");
        System.out.println("Player 2 scores again: " + game.getScore()); // Win for Player 2
    }
}