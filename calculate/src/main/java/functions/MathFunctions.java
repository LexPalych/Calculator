package functions;

import java.util.function.BiFunction;
import java.util.function.Function;

public class MathFunctions {
    public static final Function<Double, Double> FACTORIAL = MathFunctions::getFactorial;
    public static final BiFunction<Double, Double, Double> ADDITIONAL = Double::sum;
    public static final BiFunction<Double, Double, Double> SUBTRACTION = (x, y) -> x - y;
    public static final BiFunction<Double, Double, Double> MULTIPLICATION = (x, y) -> x * y;
    public static final BiFunction<Double, Double, Double> DIVISION = (x, y) -> x / y;
    public static final BiFunction<Double, Double, Double> EXPONENTIATION = Math::pow;

    /**
     * Находит факториал числа
     * @param number - число
     * @return - возвращает факториал числа типом Double
     */
    private static double getFactorial(double number) {
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
