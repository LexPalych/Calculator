public class Example {
    public static ExampleElement getNumberFromExample(final String subExpression) {
        int lastNumberIndex = 0;
        String number;
        ExampleElement exampleElement = new ExampleElement();

        while (lastNumberIndex < subExpression.length() && SymbolValidation.getSymbolType(subExpression.charAt(lastNumberIndex)) == SymbolType.DIGIT) {
            lastNumberIndex++;
        }

        number = subExpression.substring(0, lastNumberIndex);
        exampleElement.setNumber(Double.parseDouble(number));
        exampleElement.setLength(number.length());

        return exampleElement;
    }

    public static ExampleElement getFunctionFromExample(final String subExpression) {
        ExampleElement exampleElement = new ExampleElement();

        int lastFunctionIndex = CalculateString.getClosingBracketIndex(subExpression);
        String exampleFunction = subExpression.substring(0, lastFunctionIndex+1);
        double functionValue = CalculateFunction.getFunctionValue(exampleFunction);

        exampleElement.setNumber(functionValue);
        exampleElement.setLength(exampleFunction.length());

        return exampleElement;
    }

    public static ExampleElement getSignFromExample(final Character signChar) {
        ExampleElement exampleElement = new ExampleElement();

        exampleElement.setSign(signChar);
        exampleElement.setLength(1);

        return exampleElement;
    }

    public static ExampleElement getBracketFromExample(final String subExpression) {
        ExampleElement exampleElement = new ExampleElement();

        int lastFunctionIndex = CalculateString.getClosingBracketIndex(subExpression);
        String exampleBracket = subExpression.substring(1, lastFunctionIndex);
        double bracketValue = CalculateString.calculate(exampleBracket);

        exampleElement.setNumber(bracketValue);
        exampleElement.setLength(exampleBracket.length() + 2);

        return exampleElement;
    }
}
