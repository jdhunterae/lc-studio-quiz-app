package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Quiz {
    private final Scanner scanner;
    private final List<Question> questions;

    public Quiz() {
        scanner = new Scanner(System.in);
        questions = new ArrayList<Question>();
    }

    public Quiz(List<Question> questions) {
        scanner = new Scanner(System.in);
        this.questions = questions;
    }

    public static Quiz sample() {
        Quiz q = new Quiz();

        q.add(new TrueFalseQuestion("Java is a compiled language.", true));
        q.add(new TrueFalseQuestion("Abstract classes can be instantiated into objects.", false));

        q.add(new MultipleChoiceQuestion("Which of the following is NOT a scope for Java classes and variables:",
                Arrays.asList("private", "hidden", "protected", "public", "package-private"), "hidden"));

        q.add(new CheckboxQuestion("Which of the following keywords are not used in Java:",
                Arrays.asList("if", "while", "const", "switch", "case", "unless"),
                Arrays.asList("const", "unless")));

        return q;
    }

    public static void main(String[] args) {
        Quiz quiz = Quiz.sample();
        quiz.take();
    }

    public void add(Question question) {
        if (questions.contains(question)) {
            return;
        }

        questions.add(question);
    }

    public void take() {
        System.out.printf("=== Quiz (%d questions) ===\n", questions.size());

        for (Question question : questions) {
            String response = getResponse(question);
            question.parseResponse(response);

            System.out.println(question.isCorrect() ? "CORRECT!" : "incorrect...");
        }

        System.out.printf("Final Score: %.2f (%s [%d/%d])\n", percentage(), letter(), grade(), questions.size());
    }

    private String getResponse(Question question) {
        boolean invalid = true;
        String response = "";

        do {
            try {
                System.out.println(question);
                System.out.print("> ");
                response = scanner.nextLine();
                response = response.trim();

                if (response.length() > 0) {
                    invalid = false;
                }
            } catch (Exception e) {
            }
        } while (invalid);

        return response;
    }

    private double percentage() {
        return (double) grade() / questions.size() * 100.0;
    }

    private String letter() {
        double percent = percentage();

        if (percent >= 90) {
            return "A";
        } else if (percent >= 80) {
            return "B";
        } else if (percent >= 70) {
            return "C";
        } else if (percent >= 60) {
            return "D";
        }
        return "F";
    }

    private int grade() {
        int score = 0;

        for (Question question : questions) {
            if (question.isCorrect()) {
                score++;
            }
        }

        return score;
    }
}