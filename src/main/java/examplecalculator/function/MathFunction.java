package examplecalculator.function;

import examplecalculator.ExampleException;

import java.util.function.Function;

import static examplecalculator.element.ElementCalculator.getExampleValue;
import static java.lang.StrictMath.PI;

public final class MathFunction {
    /**
     * Нахождит значение тригонометрической функции
     */
    public static double getFunctionValue(final String example) {
//        String functionName = getFunctionName(example);
        Function<Double, Double> mathFunction = getMathFunction(example);
        String functionArgument = getFunctionArgument(example);

        Double functionValue = getExampleValue(functionArgument);

        return mathFunction.apply(functionValue);
    }

    /**
     * Находит в переданном примере первую функцию и выцепляет её имя
     * @param example - пример
     * @return - возвращет строку с именем функции
     */
    private static String getFunctionName(final String example) {
        int i = 0;

        while (example.charAt(i) != '(') {
            i++;
        }

        return example.substring(0, i);
    }

    /**
     * Находит в переданном примере перую функцию и выцепляет её аргумент
     * @param example - пример
     * @return - возвращет строку с аргументом функции
     */
    private static String getFunctionArgument(final String example) {
        String functionName = getFunctionName(example);
        return example.substring(functionName.length()+1, example.length()-1);
    }

    /**
     * Распознаёт строку с именем функции и возвращает соответствующую функцию
     * @param example - пример
     * @return - возвращает функцию для расчётов
     */
    private static Function<Double, Double> getMathFunction(final String example) {
        Double rad = PI/180;
        String functionName = getFunctionName(example);

        switch (functionName) {
            case "sin":
                return value -> Math.sin(value * rad);

            case "cos":
                return value -> Math.cos(value * rad);

            case "tan":
                return value -> Math.tan(value * rad);

            case "asin":
                return value -> Math.asin(value) / rad;

            case "acos":
                return value -> Math.acos(value) / rad;

            case "atan":
                return value -> Math.atan(value) / rad;

            case "sinh":
                return value -> Math.sinh(value * rad);

            case "cosh":
                return value -> Math.cosh(value * rad);

            case "tanh":
                return value -> Math.tanh(value * rad);

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