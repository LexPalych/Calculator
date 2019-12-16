package examplecalculator.function;

import examplecalculator.ExampleException;

import java.util.function.Function;

final class MathFunctions {
    private static final Double RAD = Math.acos(-1)/180;

    /**
     * Распознаёт строку с именем функции и возвращает соответствующую функцию
     * @param functionName - имя функции
     * @return - возвращает функцию для расчётов
     */
    static Function<Double, Double> getFunction(final String functionName) {
        switch (functionName) {
            case "sin":
                return value -> Math.sin(value * RAD);

            case "cos":
                return value -> Math.cos(value * RAD);

            case "tan":
                return value -> Math.tan(value * RAD);

            case "asin":
                return value -> Math.asin(value) / RAD;

            case "acos":
                return value -> Math.acos(value) / RAD;

            case "atan":
                return value -> Math.atan(value) / RAD;

            case "sinh":
                return value -> Math.sinh(value * RAD);

            case "cosh":
                return value -> Math.cosh(value * RAD);

            case "tanh":
                return value -> Math.tanh(value * RAD);

            case "exp":
                return Math::exp;

            case "abs":
                return Math::abs;

            case "sqrt":
                return Math::sqrt;

            case "ln":
                return value -> {
                    if (value > 0)
                        return Math.log(value);
                    else
                        throw new ArithmeticException("Аргумент логарифма должен быть положительным");
                };

            default:
                throw new ExampleException("Неизвестная функция");
        }
    }

}