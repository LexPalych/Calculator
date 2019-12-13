package examplecalculator.function;

import java.util.function.Function;

import static examplecalculator.element.ElementListCreator.createElementList;
import static examplecalculator.function.MathFunctions.getFunction;

public class FunctionCalculator {
    /**
     * Нахождит значение тригонометрической функции
     */
    public static double getFunctionValue(final String example) {
        String functionName = getFunctionName(example);
        Function<Double, Double> function = getFunction(functionName);

        String functionArgument = getFunctionArgument(example);
        double functionArgumentValue = createElementList(functionArgument);

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