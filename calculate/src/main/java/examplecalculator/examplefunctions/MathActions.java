package examplecalculator.examplefunctions;

import examplecalculator.ExampleException;

import java.util.function.BiFunction;

public class MathActions {
    static final BiFunction<Double, Double, Double> ADDITIONAL = Double::sum;
    static final BiFunction<Double, Double, Double> SUBTRACTION = (x, y) -> x - y;
    static final BiFunction<Double, Double, Double> MULTIPLICATION = (x, y) -> x * y;
    static final BiFunction<Double, Double, Double> DIVISION = (x, y) -> x / y;
    static final BiFunction<Double, Double, Double> EXPONENTIATION = Math::pow;
    public static final BiFunction<Double, Double, Double> FIND_FACTORIAL = (x, y) -> getFactorial(x);

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
                return FIND_FACTORIAL;

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