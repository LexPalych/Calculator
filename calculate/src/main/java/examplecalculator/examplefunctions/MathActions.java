package examplecalculator.examplefunctions;

import examplecalculator.ExampleException;

import java.util.function.BiFunction;

public class MathActions {
    public static final BiFunction<Double, Double, Double> ADDITIONAL = Double::sum;
    public static final BiFunction<Double, Double, Double> SUBTRACTION = (x, y) -> x - y;
    public static final BiFunction<Double, Double, Double> MULTIPLICATION = (x, y) -> x * y;
    public static final BiFunction<Double, Double, Double> DIVISION = (x, y) -> x / y;
    public static final BiFunction<Double, Double, Double> EXPONENTIATION = Math::pow;
    public static final BiFunction<Double, Double, Double> FACTORIAL = (x, y) -> getFactorial(x);

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

            case "!":
                return FACTORIAL;

            default:
                throw new ExampleException("Неизвестный знак дейстия");

        }
    }

    /**
     * Находит факториал числа
     * @param number - число
     * @return - возвращает факториал числа типом Double
     */
    private static Double getFactorial(final double number) {
        if (number < 0)
            throw new ArithmeticException("Отрицательный аргумент факториала");

        if (number % 1 !=0)
            throw new ArithmeticException("Аргумент факториала не является целым числом");

        if (number == 0 || number == 1)
            return 1.0;

        else
            return number * getFactorial(number-1);
    }

}