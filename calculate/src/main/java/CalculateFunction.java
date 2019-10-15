public class CalculateFunction {
    private static String functionExpression;

    /**
     * Нахождит значение тригонометрической функции
     * @return - возвращает значение тригонометрической функции
     */
    public static double getFunctionValue(final String expression) {
        functionExpression = expression;

        String functionName = getFunctionName();
        String functionArgument = getFunctionArgument();
        double functionArgumentValue = CalculateString.calculate(functionArgument);

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

            case "exp":
                return Math.exp(functionArgument);

            default:
                throw new StringException("Неизвестная функция");
        }

    }

    private static String getFunctionName() {
        int nameFunctionLastIndex = 0;

        while (functionExpression.charAt(nameFunctionLastIndex) != '(') {
            nameFunctionLastIndex++;
        }
        nameFunctionLastIndex--;

        return functionExpression.substring(0, nameFunctionLastIndex);
    }

    private static String getFunctionArgument() {
        String functionName = getFunctionName();
        return functionExpression.substring(functionName.length()+1, functionExpression.length()-1);
    }
}
