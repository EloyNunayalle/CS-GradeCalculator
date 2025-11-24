package edu.utec.gradecalculator;

import edu.utec.gradecalculator.exceptions.InvalidDataException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EvaluationTest {

    @Test
    void shouldCreateEvaluationWhenDataIsValid() {
        Evaluation evaluation = new Evaluation(15.0, 0.5);
        assertEquals(15.0, evaluation.getValue());
        assertEquals(0.5, evaluation.getWeight());
    }

    @Test
    void shouldThrowExceptionWhenValueIsNegative() {
        Exception exception = assertThrows(InvalidDataException.class, () -> new Evaluation(-1.0, 0.5));
        assertTrue(exception.getMessage().contains("entre 0 y 20"));
    }

    @Test
    void shouldThrowExceptionWhenValueIsGreaterThan20() {
        Exception exception = assertThrows(InvalidDataException.class, () -> new Evaluation(21.0, 0.5));
        assertTrue(exception.getMessage().contains("entre 0 y 20"));
    }

    @Test
    void shouldThrowExceptionWhenWeightIsZeroOrNegative() {
        assertThrows(InvalidDataException.class, () -> new Evaluation(15.0, 0.0));
        assertThrows(InvalidDataException.class, () -> new Evaluation(15.0, -0.1));
    }

    @Test
    void shouldThrowExceptionWhenWeightIsGreaterThanOne() {
        Exception exception = assertThrows(InvalidDataException.class, () -> new Evaluation(15.0, 1.1));
        assertTrue(exception.getMessage().contains("entre 0 y 1.0"));
    }
}
