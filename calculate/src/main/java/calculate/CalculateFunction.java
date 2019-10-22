package calculate;

import java.util.function.Function;

import static calculate.CalculateString.calculate;
import static functions.TrigonometricFunctions.getFunctions;

class CalculateFunction {
    /**
     * Нахождит значение тригонометрической функции
     * @return - возвращает значение тригонометрической функции
     */
    static double getFunctionValue(final String expression) {
        String functionName = getFunctionName(expression);
        Function<Double, Double> function = getFunctions(functionName);

        String functionArgument = getFunctionArgument(expression);
        double functionArgumentValue = calculate(functionArgument);

        return function.apply(functionArgumentValue);
    }

    private static String getFunctionName(final String expression) {
        int nameFunctionLastIndex = 0;

        while (expression.charAt(nameFunctionLastIndex) != '(') {
            nameFunctionLastIndex++;
        }

        return expression.substring(0, nameFunctionLastIndex);
    }

    private static String getFunctionArgument(final String expression) {
        String functionName = getFunctionName(expression);
        return expression.substring(functionName.length()+1, expression.length()-1);
    }
}
