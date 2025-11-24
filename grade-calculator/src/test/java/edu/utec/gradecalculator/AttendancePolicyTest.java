package edu.utec.gradecalculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AttendancePolicyTest {

    private final AttendancePolicy policy = new AttendancePolicy();

    @Test
    void shouldReturnZeroPenaltyWhenAttendanceIsMet() {
        double penalty = policy.calculatePenalty(true, 15.0);
        assertEquals(0.0, penalty);
    }

    @Test
    void shouldReturnPenaltyWhenAttendanceIsNotMet() {
        double weightedAverage = 10.0;
        double expectedPenalty = weightedAverage * 0.30; // 30%
        double penalty = policy.calculatePenalty(false, weightedAverage);
        assertEquals(expectedPenalty, penalty);
    }
}
