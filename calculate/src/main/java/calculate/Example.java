package calculate;

import static calculate.CalculateFunction.getFunctionValue;
import static calculate.CalculateString.calculate;
import static calculate.CalculateString.getClosingBracketIndex;
import static calculate.SymbolType.Symbol.DIGIT;
import static calculate.SymbolType.getSymbolType;

public class Example {
    public static ExampleElement getExampleNumber(final String subExample) {
        int lastNumberIndex = 0;
        String number;
        ExampleElement exampleElement = new ExampleElement();

        while (lastNumberIndex < subExample.length() && getSymbolType(subExample.charAt(lastNumberIndex)) == DIGIT) {
            lastNumberIndex++;
        }

        number = subExample.substring(0, lastNumberIndex);
        exampleElement.setNumber(Double.parseDouble(number));
        exampleElement.setLength(number.length());

        return exampleElement;
    }

    public static ExampleElement getExampleFunction(final String subExample) {
        ExampleElement exampleElement = new ExampleElement();

        int lastFunctionIndex = getClosingBracketIndex(subExample);
        String exampleFunction = subExample.substring(0, lastFunctionIndex+1);
        double functionValue = getFunctionValue(exampleFunction);

        exampleElement.setNumber(functionValue);
        exampleElement.setLength(exampleFunction.length());

        return exampleElement;
    }

    public static ExampleElement getExampleSign(final Character signChar) {
        ExampleElement exampleElement = new ExampleElement();

        exampleElement.setSign(signChar);
        exampleElement.setLength(1);

        return exampleElement;
    }

    public static ExampleElement getExampleBracket(final String subExample) {
        ExampleElement exampleElement = new ExampleElement();

        int lastFunctionIndex = getClosingBracketIndex(subExample);
        String exampleBracket = subExample.substring(1, lastFunctionIndex);
        double bracketValue = calculate(exampleBracket);

        exampleElement.setNumber(bracketValue);
        exampleElement.setLength(exampleBracket.length() + 2);

        return exampleElement;
    }
}
