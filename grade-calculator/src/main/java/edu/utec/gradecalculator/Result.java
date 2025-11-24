package edu.utec.gradecalculator;

/**
 * Contiene el resultado del cálculo de notas.
 */
public class Result {
    private final double finalGrade;
    private final double weightedAverage;
    private final double attendancePenalty;
    private final double extraPoints;

    public Result(double finalGrade, double weightedAverage, double attendancePenalty, double extraPoints) {
        this.finalGrade = finalGrade;
        this.weightedAverage = weightedAverage;
        this.attendancePenalty = attendancePenalty;
        this.extraPoints = extraPoints;
    }

    public double getFinalGrade() {
        return finalGrade;
    }

    public double getWeightedAverage() {
        return weightedAverage;
    }

    public double getAttendancePenalty() {
        return attendancePenalty;
    }

    public double getExtraPoints() {
        return extraPoints;
    }

    @Override
    public String toString() {
        return String.format(
                "Nota Final: %.2f (Promedio Ponderado: %.2f, Penalización Asistencia: %.2f, Puntos Extra: %.2f)",
                finalGrade, weightedAverage, attendancePenalty, extraPoints);
    }
}
