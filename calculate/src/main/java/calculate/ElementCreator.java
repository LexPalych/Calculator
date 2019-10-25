package calculate;

import static calculate.CalculateExample.calculate;
import static calculate.CalculateFunction.getFunctionValue;
import static calculate.CalculateExample.getClosingBracketIndex;
import static calculate.SymbolType.Symbol.DIGIT;
import static calculate.SymbolType.getSymbolType;

public class ElementCreator {
    public static Element getExampleNumber(final String subExample) {
        int lastNumberIndex = 0;
        String number;
        Element exampleElement = new Element();

        while (lastNumberIndex < subExample.length() && getSymbolType(subExample.charAt(lastNumberIndex)) == DIGIT) {
            lastNumberIndex++;
        }

        number = subExample.substring(0, lastNumberIndex);
        exampleElement.setNumber(Double.parseDouble(number));
        exampleElement.setLength(number.length());

        return exampleElement;
    }

    public static Element getExampleFunction(final String subExample) {
        Element exampleElement = new Element();

        int lastFunctionIndex = getClosingBracketIndex(subExample);
        String exampleFunction = subExample.substring(0, lastFunctionIndex+1);
        double functionValue = getFunctionValue(exampleFunction);

        exampleElement.setNumber(functionValue);
        exampleElement.setLength(exampleFunction.length());

        return exampleElement;
    }

    public static Element getExampleSign(final Character signChar) {
        Element exampleElement = new Element();

        exampleElement.setSign(signChar);
        exampleElement.setLength(1);

        return exampleElement;
    }

    public static Element getExampleBracket(final String subExample) {
        Element exampleElement = new Element();

        int lastFunctionIndex = getClosingBracketIndex(subExample);
        String exampleBracket = subExample.substring(1, lastFunctionIndex);
        double bracketValue = calculate(exampleBracket);

        exampleElement.setNumber(bracketValue);
        exampleElement.setLength(exampleBracket.length() + 2);

        return exampleElement;
    }
}
