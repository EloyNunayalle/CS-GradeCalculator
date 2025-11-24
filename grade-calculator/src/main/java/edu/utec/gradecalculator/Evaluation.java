package edu.utec.gradecalculator;

import edu.utec.gradecalculator.exceptions.InvalidDataException;

/**
 * Representa una evaluación individual con su nota y peso.
 */
public class Evaluation {
    private final double value;
    private final double weight;

    public Evaluation(double value, double weight) {
        // Validación: Nota entre 0 y 20
        if (value < 0 || value > 20) {
            throw new InvalidDataException("El valor de la evaluación debe estar entre 0 y 20.");
        }
        // Validación: Peso entre 0 y 1.0 (representando 0% a 100%)
        if (weight <= 0 || weight > 1.0) {
            throw new InvalidDataException("El peso debe estar entre 0 y 1.0.");
        }
        this.value = value;
        this.weight = weight;
    }

    public double getValue() {
        return value;
    }

    public double getWeight() {
        return weight;
    }
}
