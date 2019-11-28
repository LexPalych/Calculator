package calculate.functions;

import calculate.MathActionPriority;
import calculate.StringException;

import java.util.function.BiFunction;

public class MathFunctions {
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
                throw new StringException("Неизвестный знак дейстия");

        }
    }

    /**
     * Находит факториал числа
     * @param number - число
     * @return - возвращает факториал числа типом Double
     */
    public static Double getFactorial(final double number) {
        if (number < 0)
            throw new ArithmeticException("Отрицательный аргумент факториала");

        if (number % 1 !=0)
            throw new ArithmeticException("Аргумент факториала не является целым числом");

        if (number == 0 || number == 1)
            return 1.0;

        else
            return number * getFactorial(number-1);
    }

    /**
     * Распознаёт приоритет выполняемого действия и возращает соответствующую логическую функцию
     * @param priorities - приоритет
     * @return - возвращает логическую функцию
     */
    public static BiFunction<Double, Double, Double> getFunction(MathActionPriority.Priorities priorities) {
        switch (priorities) {
            case SECOND:
                return EXPONENTIATION;

            case THIRD:
                return DIVISION;

            case FOURTH:
                return MULTIPLICATION;

            case FIFTH:
                return SUBTRACTION;

            case SIXTH:
                return ADDITIONAL;

            default:
                throw new StringException("Неизвестный приоритет функции");

        }
    }

}