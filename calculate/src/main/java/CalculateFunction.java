public class CalculateFunction {
    /**
     * Нахождит значение тригонометрической функции
     * @return - возвращает значение тригонометрической функции
     */
    public static double getFunctionValue(final int index) {
        double rad = Math.acos(-1)/180;
        String functionName = getFunctionName(index);

        int openBracketIndex = index + functionName.length();
        int closeBracketIndex = getClosingBracketIndex(openBracketIndex);

        String functionArgument = expression.substring(openBracketIndex+1, closeBracketIndex-1);

        switch (functionName) {
            case "sin":
                return Math.sin(calculate(functionArgument) * rad);

            case "cos":
                return Math.cos(calculate(functionArgument) * rad);

            case "tan":
                return Math.tan(calculate(functionArgument) * rad);

            case "ln":
                var value = calculate(functionArgument);
                if (value > 0)
                    return Math.log(value);
                else
                    throw new ArithmeticException("Аргумент логарифма должен быть положительным");

            case "abs":
                return Math.abs(calculate(functionArgument));

            case "asin":
                return Math.asin(calculate(functionArgument)) / rad;

            case "acos":
                return Math.acos(calculate(functionArgument)) / rad;

            case "atan":
                return Math.atan(calculate(functionArgument)) / rad;

            case "sinh":
                return Math.sinh(calculate(functionArgument) * rad);

            case "cosh":
                return Math.cosh(calculate(functionArgument) * rad);

            case "tanh":
                return Math.tanh(calculate(functionArgument) * rad);

            case "sqrt":
                return Math.sqrt(calculate(functionArgument));

            default:
                throw new StringException("Неизвестная функция");
        }
    }
}
