package edu.utec.gradecalculator;

import edu.utec.gradecalculator.exceptions.InvalidDataException;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GradeCalculatorTest {

    private final GradeCalculator calculator = new GradeCalculator();

    @Test
    void shouldCalculateGradeCorrectlyWithNormalData() {
        List<Evaluation> evaluations = new ArrayList<>();
        evaluations.add(new Evaluation(14.0, 0.3));
        evaluations.add(new Evaluation(16.0, 0.3));
        evaluations.add(new Evaluation(18.0, 0.4));

        Result result = calculator.calculate(evaluations, true, false);

        assertEquals(16.2, result.getFinalGrade(), 0.01);
    }

    @Test
    void shouldApplyAttendancePenaltyWhenAttendanceNotMet() {
        List<Evaluation> evaluations = new ArrayList<>();
        evaluations.add(new Evaluation(20.0, 1.0));

        Result result = calculator.calculate(evaluations, false, false);

        // Penalty 30% of 20 = 6.0. Final = 14.0
        assertEquals(14.0, result.getFinalGrade(), 0.01);
        assertEquals(6.0, result.getAttendancePenalty(), 0.01);
    }

    @Test
    void shouldApplyExtraPointsWhenConditionMet() {
        List<Evaluation> evaluations = new ArrayList<>();
        evaluations.add(new Evaluation(15.0, 1.0));

        Result result = calculator.calculate(evaluations, true, true);

        // Extra = 1.0. Final = 16.0
        assertEquals(16.0, result.getFinalGrade(), 0.01);
        assertEquals(1.0, result.getExtraPoints(), 0.01);
    }

    @Test
    void shouldThrowExceptionWhenEvaluationsListIsEmpty() {
        Exception exception = assertThrows(InvalidDataException.class,
                () -> calculator.calculate(Collections.emptyList(), true, false));
        assertTrue(exception.getMessage().contains("no puede estar vacía"));
    }

    @Test
    void shouldThrowExceptionWhenWeightsDoNotSumToOne() {
        List<Evaluation> evaluations = new ArrayList<>();
        evaluations.add(new Evaluation(15.0, 0.5));

        Exception exception = assertThrows(InvalidDataException.class,
                () -> calculator.calculate(evaluations, true, false));
        assertTrue(exception.getMessage().contains("debe ser 100%"));
    }

    @Test
    void shouldThrowExceptionWhenMoreThan10Evaluations() {
        List<Evaluation> evaluations = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            evaluations.add(new Evaluation(10.0, 0.09));
        }
        Exception exception = assertThrows(InvalidDataException.class,
                () -> calculator.calculate(evaluations, true, false));
        assertTrue(exception.getMessage().contains("máximo de evaluaciones es 10"));
    }
}
