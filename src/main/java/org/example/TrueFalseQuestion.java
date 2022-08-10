package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrueFalseQuestion extends Question {
    private final static List<String> ALLOWED_RESPONSES = new ArrayList<>(Arrays.asList("true", "t", "false", "f"));
    private final static List<String> TRUE = new ArrayList<>(Arrays.asList("true", "t"));
    private final boolean answer;
    private boolean userAnswer;

    public TrueFalseQuestion(String description, boolean answer) {
        super(description);
        this.answer = answer;
    }

    public TrueFalseQuestion(String name, String description, boolean answer) {
        super(name, description);
        this.answer = answer;
    }

    @Override
    void parseResponse(String input) {
        answered = false;

        if (input == null || input.isEmpty()) {
            return;
        }

        input = input.trim().toLowerCase();
        if (!ALLOWED_RESPONSES.contains(input)) {
            return;
        }

        userAnswer = TRUE.contains(input);
        answered = true;
    }

    @Override
    boolean isCorrect() {
        return answered && answer == userAnswer;
    }

    @Override
    public String toString() {
        return String.format("%s [true/false]?", super.toString());
    }
}
