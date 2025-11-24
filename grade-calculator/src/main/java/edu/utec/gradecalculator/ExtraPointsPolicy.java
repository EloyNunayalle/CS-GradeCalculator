package edu.utec.gradecalculator;

/**
 * Política de puntos extra.
 * Define los puntos adicionales si se cumplen ciertos criterios.
 */
public class ExtraPointsPolicy {
    private static final double EXTRA_POINTS = 1.0; // 1 punto extra

    /**
     * Calcula los puntos extra.
     * 
     * @param allYearsTeachers Si cumple el criterio de profesores de todos los
     *                         años.
     * @return Puntos a sumar.
     */
    public double calculateExtraPoints(boolean allYearsTeachers) {
        return allYearsTeachers ? EXTRA_POINTS : 0.0;
    }
}
