package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MultipleChoiceQuestionTest {
    static MultipleChoiceQuestion mcQuestion;

    @BeforeAll
    public static void setUp() {
        mcQuestion = new MultipleChoiceQuestion("Which fruit starts with 'A'?", new ArrayList<>(Arrays.asList("apple", "banana", "orange", "grape")), "apple");
    }

    @Test
    public void testMCQCorrectReturnsTrueIfCorrectAnswer() {
        mcQuestion.parseResponse("apple");
        assertTrue(mcQuestion.isCorrect());
    }

    @Test
    public void testMCQCorrectReturnsFalseIfIncorrectAnswer() {
        mcQuestion.parseResponse("banana");
        assertFalse(mcQuestion.isCorrect());
    }

    @Test
    public void testMCQCorrectReturnsTrueIgnoresCase() {
        mcQuestion.parseResponse("AppLE");
        assertTrue(mcQuestion.isCorrect());
    }

    @Test
    public void testMCQCorrectReturnsFalseIfAnswerNotAnOption() {
        mcQuestion.parseResponse("steak");
        assertFalse(mcQuestion.isCorrect());
    }
}
