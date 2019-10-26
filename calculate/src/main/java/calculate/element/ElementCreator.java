package calculate.element;

import static calculate.CalculateExample.calculate;
import static calculate.SymbolType.Symbol.DIGIT;
import static calculate.SymbolType.getSymbolType;
import static calculate.functions.CalculateFunction.getFunctionValue;

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

    public static Element getExampleBracket(final String subExample) {
        Element exampleElement = new Element();

        int lastFunctionIndex = getClosingBracketIndex(subExample);
        String exampleBracket = subExample.substring(1, lastFunctionIndex);
        double bracketValue = calculate(exampleBracket);

        exampleElement.setNumber(bracketValue);
        exampleElement.setLength(exampleBracket.length() + 2);

        return exampleElement;
    }

    private static int getClosingBracketIndex(final String subExample) {
        int bracketAmount = 0;
        int lastBracketIndex = 0;
        char currentChar;

        do {
            currentChar = subExample.charAt(lastBracketIndex++);

            if (currentChar == '(') {
                bracketAmount++;

            } else if (currentChar == ')') {
                bracketAmount--;
            }
        } while (!(bracketAmount == 0 && currentChar == ')'));
        lastBracketIndex--;

        return lastBracketIndex;
    }
}
