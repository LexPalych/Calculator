package examplecalculator.action;

import examplecalculator.ExampleException;

import java.util.function.BiFunction;

public class ActionFunction {
    static final BiFunction<Double, Double, Double> ADDITIONAL = Double::sum;
    static final BiFunction<Double, Double, Double> SUBTRACTION = (x, y) -> x - y;
    static final BiFunction<Double, Double, Double> MULTIPLICATION = (x, y) -> x * y;
    static final BiFunction<Double, Double, Double> DIVISION = (x, y) -> x / y;
    static final BiFunction<Double, Double, Double> EXPONENTIATION = Math::pow;

    public static BiFunction<Double, Double, Double> getMathFunction(final String sign) {
        switch (sign) {
            case "+":
                return ADDITIONAL;

            case "-":
                return SUBTRACTION;

            case "*":
                return MULTIPLICATION;

            case "/":
                return DIVISION;

            case "^":
                return EXPONENTIATION;

            default:
                throw new ExampleException("Неизвестный знак дейстия");

        }
    }

}