package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrueFalseQuestionTest {
    static TrueFalseQuestion trueQuestion;
    static TrueFalseQuestion falseQuestion;

    @BeforeAll
    public static void setUp() {
        trueQuestion = new TrueFalseQuestion("The answer is true.", true);
        falseQuestion = new TrueFalseQuestion("The answer is false.", false);
    }

    @Test
    public void testTFQCorrectReturnsTrueToTrueResponse() {
        trueQuestion.parseResponse("true");
        assertTrue(trueQuestion.isCorrect());
    }

    @Test
    public void testTFQCorrectReturnsFalseToFalseResponse() {
        trueQuestion.parseResponse("false");
        assertFalse(trueQuestion.isCorrect());
    }

    @Test
    public void testTFQCorrectReturnsFalseToTrueResponse() {
        falseQuestion.parseResponse("true");
        assertFalse(falseQuestion.isCorrect());
    }

    @Test
    public void testTFQCorrectReturnsTrueToFalseResponse() {
        falseQuestion.parseResponse("false");
        assertTrue(falseQuestion.isCorrect());
    }

    @Test
    public void testTFQCorrectReturnsFalseIfGivenWrongInputWhenTrue() {
        trueQuestion.parseResponse("apple");
        assertFalse(trueQuestion.isCorrect());
    }

    @Test
    public void testTFQCorrectReturnsFalseIfGivenWrongInputWhenFalse() {
        falseQuestion.parseResponse("apple");
        assertFalse(falseQuestion.isCorrect());
    }
}