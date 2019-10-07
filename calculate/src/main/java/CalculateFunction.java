public class CalculateFunction {
    /**
     * Нахождит значение тригонометрической функции
     * @return - возвращает значение тригонометрической функции
     */
    public static double getFunctionValue(final String expression) {
        String functionArgument = getFunctionArgument(expression);
        double functionArgumentValue = CalculateString.calculate(functionArgument);
        String functionName = getFunctionName(expression);

        return getArgumentValue(functionName, functionArgumentValue);
    }

    private static double getArgumentValue(final String functionName, final double functionArgument) {
//        List<String> functionList = List.of("sin", "cos", "tan", "ln", "abs", "asin", "acos", "atan", "sinh", "cosh", "tanh", "sqrt", "exp");
        double rad = Math.acos(-1)/180;

        switch (functionName) {
            case "sin":
                return Math.sin(functionArgument * rad);

            case "cos":
                return Math.cos(functionArgument * rad);

            case "tan":
                return Math.tan(functionArgument * rad);

            case "ln":
                if (functionArgument > 0)
                    return Math.log(functionArgument);
                else
                    throw new ArithmeticException("Аргумент логарифма должен быть положительным");

            case "abs":
                return Math.abs(functionArgument);

            case "asin":
                return Math.asin(functionArgument) / rad;

            case "acos":
                return Math.acos(functionArgument) / rad;

            case "atan":
                return Math.atan(functionArgument) / rad;

            case "sinh":
                return Math.sinh(functionArgument * rad);

            case "cosh":
                return Math.cosh(functionArgument * rad);

            case "tanh":
                return Math.tanh(functionArgument * rad);

            case "sqrt":
                return Math.sqrt(functionArgument);

            default:
                throw new StringException("Неизвестная функция");
        }

    }

    private static String getFunctionName(final String expression) {
        int nameFunctionLastIndex = 0;

        while (expression.charAt(nameFunctionLastIndex) != '(') {
            nameFunctionLastIndex++;
        }
        nameFunctionLastIndex--;

        return expression.substring(0, nameFunctionLastIndex);
    }

    private static String getFunctionArgument(final String expression) {
        String functionName = getFunctionName(expression);
        return expression.substring(functionName.length()+1, expression.length()-1);
    }
}
