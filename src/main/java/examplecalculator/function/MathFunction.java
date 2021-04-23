package examplecalculator.function;

import static examplecalculator.element.ElementCalculator.getExampleValue;
import static java.lang.StrictMath.PI;

import examplecalculator.ExampleException;
import java.util.function.Function;

public final class MathFunction {

    /**
     * Нахождит значение тригонометрической функции
     */
    public static double getFunctionValue(final String example) {
        Function<Double, Double> mathFunction = getMathFunction(example);
        String functionArgument = getFunctionArgument(example);

        double functionValue = getExampleValue(functionArgument);

        return mathFunction.apply(functionValue);
    }

    /**
     * Находит в переданном примере первую функцию и выцепляет её имя
     *
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
     *
     * @param example - пример
     * @return - возвращет строку с аргументом функции
     */
    private static String getFunctionArgument(final String example) {
        String functionName = getFunctionName(example);
        return example.substring(functionName.length() + 1, example.length() - 1);
    }

    /**
     * Распознаёт строку с именем функции и возвращает соответствующую функцию
     *
     * @param example - пример
     * @return - возвращает функцию для расчётов
     */
    private static Function<Double, Double> getMathFunction(final String example) {
        String functionName = getFunctionName(example);
        double rad = PI / 180;

        return switch (functionName) {
            case "sin" -> value -> Math.sin(value * rad);
            case "cos" -> value -> Math.cos(value * rad);
            case "tan" -> value -> Math.tan(value * rad);
            case "asin" -> value -> Math.asin(value) / rad;
            case "acos" -> value -> Math.acos(value) / rad;
            case "atan" -> value -> Math.atan(value) / rad;
            case "sinh" -> value -> Math.sinh(value * rad);
            case "cosh" -> value -> Math.cosh(value * rad);
            case "tanh" -> value -> Math.tanh(value * rad);
            case "exp" -> Math::exp;
            case "abs" -> Math::abs;
            case "sqrt" -> Math::sqrt;
            case "ln" -> Math::log;
            default -> throw new ExampleException("Неизвестная функция");
        };
    }
}