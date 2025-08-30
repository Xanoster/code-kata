package com.cgi.tennis;

import java.util.Arrays;
import java.util.List;

public class ScoreTerms {
    private final List<String> terms;

    public ScoreTerms() {
        this.terms = Arrays.asList("Love", "Fifteen", "Thirty", "Forty");
    }

    public String getTerm(Score score) {
        return terms.get(score.getValue());
    }
}