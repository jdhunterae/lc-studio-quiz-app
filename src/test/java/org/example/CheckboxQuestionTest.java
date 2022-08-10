package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckboxQuestionTest {
    static CheckboxQuestion cbQuestion;

    @BeforeAll
    public static void setUp() {
        cbQuestion = new CheckboxQuestion("Which fruit(s) contain a double 'p' in them?",
                new ArrayList<>(Arrays.asList("apple", "banana", "orange", "pineapple")),
                new ArrayList<>(Arrays.asList("apple", "pineapple")));
    }

    @Test
    public void testCBQCorrectReturnsTrueIfCorrectAnswer() {
        cbQuestion.parseResponse("apple::pineapple");
        assertTrue(cbQuestion.isCorrect());
    }

    @Test
    public void testCBQCorrectReturnsFalseIfPartialCorrectAnswer() {
        cbQuestion.parseResponse("apple");
        assertFalse(cbQuestion.isCorrect());
    }

    @Test
    public void testCBQCorrectReturnsTrueIfCorrectIgnoresLeadingAndTrailingSpaces() {
        cbQuestion.parseResponse("apple  ::  pineapple");
        assertTrue(cbQuestion.isCorrect());
    }

    @Test
    public void testCBQCorrectReturnsFalseIfCorrectButExtraAnswer() {
        cbQuestion.parseResponse("apple::pineapple::grape");
        assertFalse(cbQuestion.isCorrect());
    }
}
