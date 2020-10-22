package examplecalculator.function;

import examplecalculator.ExampleException;

import java.util.function.BiFunction;

public final class ActionFunction {
    public static final BiFunction<Double, Double, Double> ADDITIONAL = Double::sum;
    public static final BiFunction<Double, Double, Double> SUBTRACTION = (x, y) -> x - y;
    public static final BiFunction<Double, Double, Double> MULTIPLICATION = (x, y) -> x * y;
    public static final BiFunction<Double, Double, Double> DIVISION = (x, y) -> x / y;
    public static final BiFunction<Double, Double, Double> EXPONENTIATION = Math::pow;

    public static BiFunction<Double, Double, Double> getMathAction(final String sign) {
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