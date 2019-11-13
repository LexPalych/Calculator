package calculate.functions;

import java.util.function.Function;

import static calculate.CalculateExample.calculate;
import static calculate.functions.TrigonometricFunctions.getFunctions;

public class CalculateFunction {
    /**
     * Нахождит значение тригонометрической функции
     */
    public static double getFunctionValue(final String example) {
        String functionName = getFunctionName(example);
        Function<Double, Double> function = getFunctions(functionName);

        String functionArgument = getFunctionArgument(example);
        double functionArgumentValue = calculate(functionArgument);

        return function.apply(functionArgumentValue);
    }

    /**
     * Находит в переданном примере первую функцию и выцепляет её имя
     * @param example - пример
     * @return - возвращет строку с именем функции
     */
    private static String getFunctionName(final String example) {
        int lastLetterIndex = 0;

        while (example.charAt(lastLetterIndex) != '(') {
            lastLetterIndex++;
        }

        return example.substring(0, lastLetterIndex);
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
}
