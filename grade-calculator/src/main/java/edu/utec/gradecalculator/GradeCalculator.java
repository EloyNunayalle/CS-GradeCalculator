package edu.utec.gradecalculator;

import edu.utec.gradecalculator.exceptions.InvalidDataException;
import java.util.List;

/**
 * Calculadora de notas principal.
 * Orquesta el cálculo considerando evaluaciones, asistencia y puntos extra.
 */
public class GradeCalculator {
    private final AttendancePolicy attendancePolicy;
    private final ExtraPointsPolicy extraPointsPolicy;

    public GradeCalculator() {
        this.attendancePolicy = new AttendancePolicy();
        this.extraPointsPolicy = new ExtraPointsPolicy();
    }

    /**
     * Calcula la nota final.
     * 
     * @param examsStudents            Lista de evaluaciones.
     * @param hasReachedMinimumClasses Si cumplió asistencia mínima.
     * @param allYearsTeachers         Si cumple criterio de puntos extra.
     * @return Objeto Result con el detalle.
     */
    public Result calculate(List<Evaluation> examsStudents, boolean hasReachedMinimumClasses,
            boolean allYearsTeachers) {
        if (examsStudents == null || examsStudents.isEmpty()) {
            throw new InvalidDataException("La lista de evaluaciones no puede estar vacía.");
        }
        // RNF01: Máximo 10 evaluaciones
        if (examsStudents.size() > 10) {
            throw new InvalidDataException("El número máximo de evaluaciones es 10.");
        }

        double sumWeightedGrades = 0;
        double sumWeights = 0;

        for (Evaluation eval : examsStudents) {
            sumWeightedGrades += eval.getValue() * eval.getWeight();
            sumWeights += eval.getWeight();
        }

        // Validación: La suma de pesos debe ser 1.0 (100%)
        if (Math.abs(sumWeights - 1.0) > 0.001) {
            throw new InvalidDataException("El peso total debe ser 100% (1.0). Suma actual: " + sumWeights);
        }

        double weightedAverage = sumWeightedGrades;
        double attendancePenalty = attendancePolicy.calculatePenalty(hasReachedMinimumClasses, weightedAverage);
        double extraPoints = extraPointsPolicy.calculateExtraPoints(allYearsTeachers);

        double finalGrade = weightedAverage - attendancePenalty + extraPoints;

        // Limitar nota entre 0 y 20
        if (finalGrade < 0)
            finalGrade = 0;
        if (finalGrade > 20)
            finalGrade = 20;

        return new Result(finalGrade, weightedAverage, attendancePenalty, extraPoints);
    }
}
