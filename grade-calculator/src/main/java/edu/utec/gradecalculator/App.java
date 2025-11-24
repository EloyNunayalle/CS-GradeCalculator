package edu.utec.gradecalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Aplicación principal interactiva.
 */
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== CS-GradeCalculator ===");

        try {
            GradeCalculator calculator = new GradeCalculator();
            List<Evaluation> evaluations = new ArrayList<>();

            System.out.println("Ingrese las evaluaciones (Máximo 10).");
            System.out.println("Escriba 'fin' como nota para terminar.");

            while (true) {
                if (evaluations.size() >= 10) {
                    System.out.println("Se ha alcanzado el número máximo de evaluaciones (10).");
                    break;
                }

                System.out.print("Ingrese nota (0-20) o 'fin': ");
                String input = scanner.next();

                if (input.equalsIgnoreCase("fin")) {
                    break;
                }

                try {
                    double value = Double.parseDouble(input);
                    System.out.print("Ingrese peso (0.0 - 1.0): ");
                    double weight = scanner.nextDouble();

                    evaluations.add(new Evaluation(value, weight));
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Por favor ingrese un número.");
                } catch (Exception e) {
                    System.out.println("Error al agregar evaluación: " + e.getMessage());
                }
            }

            System.out.print("¿El estudiante cumplió con la asistencia mínima? (true/false): ");
            boolean hasReachedMinimumClasses = scanner.nextBoolean();

            System.out.print("¿Los docentes de todos los años están de acuerdo para puntos extra? (true/false): ");
            boolean allYearsTeachers = scanner.nextBoolean();

            Result result = calculator.calculate(evaluations, hasReachedMinimumClasses, allYearsTeachers);
            System.out.println("\n=== Resultado ===");
            System.out.println(result);

        } catch (Exception e) {
            System.err.println("Error en el cálculo: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
