public class ExampleElement {
    private Character sign;
    private Double number;
    private int length;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Character getSign() {
        return sign;
    }

    public void setSign(Character sign) {
        this.sign = sign;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public static ExampleElement getNumberExampleElement(final String subExpression) {
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

    public static ExampleElement getFunctionExampleElement(final String subExpression) {
        ExampleElement exampleElement = new ExampleElement();

        int lastFunctionIndex = CalculateString.getClosingBracketIndex(subExpression);
        String exampleFunction = subExpression.substring(0, lastFunctionIndex+1);
        double functionValue = CalculateFunction.getFunctionValue(exampleFunction);

        exampleElement.setNumber(functionValue);
        exampleElement.setLength(exampleFunction.length());

        return exampleElement;
    }

    public static ExampleElement getSignExampleElement(final Character signChar) {
        ExampleElement exampleElement = new ExampleElement();

        exampleElement.setSign(signChar);
        exampleElement.setLength(1);

        return exampleElement;
    }

    public static ExampleElement getBracketExampleElement(final String subExpression) {
        ExampleElement exampleElement = new ExampleElement();

        int lastFunctionIndex = CalculateString.getClosingBracketIndex(subExpression);
        String exampleBracket = subExpression.substring(1, lastFunctionIndex);
        double bracketValue = CalculateString.calculate(exampleBracket);

        exampleElement.setNumber(bracketValue);
        exampleElement.setLength(exampleBracket.length() + 2);

        return exampleElement;
    }

}
