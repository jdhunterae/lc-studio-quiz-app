package org.example;

import java.util.ArrayList;
import java.util.List;

public class CheckboxQuestion extends Question {
    private static final String DELIM = "::";
    private final List<String> choices;
    private final List<String> answers;
    private List<String> userAnswers;

    public CheckboxQuestion(String description, List<String> choices, List<String> answers) {
        super(description);
        this.choices = choices;
        this.answers = answers;
    }

    public CheckboxQuestion(String name, String description, List<String> choices, List<String> answers) {
        super(name, description);
        this.choices = choices;
        this.answers = answers;
    }

    @Override
    void parseResponse(String input) {
        userAnswers = new ArrayList<String>();
        answered = false;

        if (input == null) {
            return;
        }

        input = input.trim().toLowerCase();

        if (input.isEmpty()) {
            return;
        }

        String[] entries = input.split(DELIM);

        for (String entry : entries) {
            String filtered = entry.trim().toLowerCase();

            if (!choices.contains(filtered)) {
                return;
            }

            if (!userAnswers.contains(filtered)) {
                userAnswers.add(filtered);
            }
        }

        if (userAnswers.size() > 0) {
            answered = true;
        }
    }

    @Override
    boolean isCorrect() {
        int correctAnswerCount = 0;
        int incorrectAnswerCount = 0;

        if (!answered || userAnswers.size() != answers.size()) {
            return false;
        }

        for (String entry : userAnswers) {
            if (answers.contains(entry)) {
                correctAnswerCount++;
            } else {
                incorrectAnswerCount++;
            }
        }

        return incorrectAnswerCount == 0 && correctAnswerCount == answers.size();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(super.toString());

        for (int i = 0; i < choices.size(); i++) {
            result.append(String.format("\n  %d: %s", i + 1, choices.get(i)));
        }

        result.append(String.format("\n(Please enter your choices separated by '%s')", DELIM));

        return result.toString();
    }
}
