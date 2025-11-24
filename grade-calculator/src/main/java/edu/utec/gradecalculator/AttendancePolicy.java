package edu.utec.gradecalculator;

/**
 * Política de asistencia.
 * Define la penalización si no se cumple con la asistencia mínima.
 */
public class AttendancePolicy {
    private static final double PENALTY_PERCENTAGE = 0.30; // 30% de penalización

    /**
     * Calcula la penalización basada en la asistencia.
     * 
     * @param hasReachedMinimumClasses Si el estudiante cumplió la asistencia
     *                                 mínima.
     * @param weightedAverage          El promedio ponderado actual.
     * @return El valor a restar de la nota.
     */
    public double calculatePenalty(boolean hasReachedMinimumClasses, double weightedAverage) {
        if (hasReachedMinimumClasses) {
            return 0.0;
        }
        return weightedAverage * PENALTY_PERCENTAGE;
    }
}
