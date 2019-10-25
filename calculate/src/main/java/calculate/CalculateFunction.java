package calculate;

import java.util.function.Function;

import static calculate.CalculateExample.calculate;
import static functions.TrigonometricFunctions.getFunctions;

class CalculateFunction {
    /**
     * Нахождит значение тригонометрической функции
     * @return - возвращает значение тригонометрической функции
     */
    static double getFunctionValue(final String example) {
        String functionName = getFunctionName(example);
        Function<Double, Double> function = getFunctions(functionName);

        String functionArgument = getFunctionArgument(example);
        double functionArgumentValue = calculate(functionArgument);

        return function.apply(functionArgumentValue);
    }

    private static String getFunctionName(final String example) {
        int lastLetterIndex = 0;

        while (example.charAt(lastLetterIndex) != '(') {
            lastLetterIndex++;
        }

        return example.substring(0, lastLetterIndex);
    }

    private static String getFunctionArgument(final String example) {
        String functionName = getFunctionName(example);
        return example.substring(functionName.length()+1, example.length()-1);
    }
}
