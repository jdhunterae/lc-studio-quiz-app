package org.example;

import java.util.List;

public class MultipleChoiceQuestion extends Question {
    private final List<String> choices;
    private final String answer;
    private String userAnswer;

    public MultipleChoiceQuestion(String description, List<String> choices, String answer) {
        super(description);
        this.choices = choices;
        this.answer = answer;
    }

    public MultipleChoiceQuestion(String name, String description, List<String> choices, String answer) {
        super(name, description);
        this.choices = choices;
        this.answer = answer;
    }

    @Override
    void parseResponse(String input) {
        if (input == null) {
            answered = false;
            userAnswer = "";
            return;
        }

        input = input.trim().toLowerCase();

        if (input.isEmpty() || !choices.contains(input)) {
            answered = false;
            userAnswer = "";
            return;
        }

        userAnswer = input;
        answered = true;
    }

    @Override
    boolean isCorrect() {
        return answered && answer.equals(userAnswer);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(super.toString());

        for (int i = 0; i < choices.size(); i++) {
            result.append(String.format("\n  %d: %s", i + 1, choices.get(i)));
        }

        return result.toString();
    }
}
