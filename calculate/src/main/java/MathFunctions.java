import java.util.function.BiFunction;
import java.util.function.Function;

class MathFunctions {
    private static final Double RAD = Math.acos(-1)/180;

    private static final Function<Double, Double> SIN = value -> Math.sin(value * RAD);
    private static final Function<Double, Double> COS = value -> Math.cos(value * RAD);
    private static final Function<Double, Double> TAN = value -> Math.tan(value * RAD);

    private static final Function<Double, Double> ARCSIN = value -> Math.asin(value) / RAD;
    private static final Function<Double, Double> ARCCOS = value -> Math.acos(value) / RAD;
    private static final Function<Double, Double> ARCTAN = value -> Math.atan(value) / RAD;

    private static final Function<Double, Double> SIN_HYPERBOLIC = value -> Math.sin(value * RAD);
    private static final Function<Double, Double> COS_HYPERBOLIC = value -> Math.cos(value * RAD);
    private static final Function<Double, Double> TAN_HYPERBOLIC = value -> Math.tan(value * RAD);

    private static final Function<Double, Double> NATURAL_LOGARITHM = MathFunctions::getLogarithm;
    private static final Function<Double, Double> EXP = Math::exp;
    private static final Function<Double, Double> ABS = Math::abs;
    private static final Function<Double, Double> SQRT = Math::sqrt;

    static final Function<Double, Double> FACTORIAL = MathFunctions::getFactorial;

    static final BiFunction<Double, Double, Double> ADDITIONAL = Double::sum;
    static final BiFunction<Double, Double, Double> SUBTRACTION = (x, y) -> x - y;
    static final BiFunction<Double, Double, Double> MULTIPLICATION = (x, y) -> x * y;
    static final BiFunction<Double, Double, Double> DIVISION = (x, y) -> x / y;
    static final BiFunction<Double, Double, Double> EXPONENTIATION = Math::pow;

    private static Double getLogarithm(final Double argument) {
        if (argument > 0)
            return Math.log(argument);
        else
            throw new ArithmeticException("Аргумент логарифма должен быть положительным");
    }

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

    static Function<Double, Double> getFunctions(final String functionName) {
        switch (functionName) {
            case "sin":
                return SIN;

            case "cos":
                return COS;

            case "tan":
                return TAN;

            case "asin":
                return ARCSIN;

            case "acos":
                return ARCCOS;

            case "atan":
                return ARCTAN;

            case "sinh":
                return SIN_HYPERBOLIC;

            case "cosh":
                return COS_HYPERBOLIC;

            case "tanh":
                return TAN_HYPERBOLIC;

            case "ln":
                return NATURAL_LOGARITHM;

            case "exp":
                return EXP;

            case "abs":
                return ABS;

            case "sqrt":
                return SQRT;

            default:
                throw new StringException("Неизвестная функция");
        }
    }



}
