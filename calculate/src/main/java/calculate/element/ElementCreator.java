package calculate.element;

import static calculate.CalculateExample.calculate;
import static calculate.SymbolType.Symbol.DIGIT;
import static calculate.SymbolType.getSymbolType;
import static calculate.functions.CalculateFunction.getFunctionValue;

public class ElementCreator {
    public static Element getExampleNumber(final String subExample) {
        int lastNumberIndex = 0;
        String number;
        Element element = new Element();

        while (lastNumberIndex < subExample.length() && getSymbolType(subExample.charAt(lastNumberIndex)) == DIGIT) {
            lastNumberIndex++;
        }

        number = subExample.substring(0, lastNumberIndex);
        element.setNumber(Double.parseDouble(number));
        element.setLength(number.length());

        return element;
    }

    public static Element getExampleFunction(final String subExample) {
        Element element = new Element();

        int lastFunctionIndex = getClosingBracketIndex(subExample);
        String exampleFunction = subExample.substring(0, lastFunctionIndex+1);
        double functionValue = getFunctionValue(exampleFunction);

        element.setNumber(functionValue);
        element.setLength(exampleFunction.length());

        return element;
    }

    public static Element getExampleBracket(final String subExample) {
        Element element = new Element();

        int lastFunctionIndex = getClosingBracketIndex(subExample);
        String exampleBracket = subExample.substring(1, lastFunctionIndex);
        double bracketValue = calculate(exampleBracket);

        element.setNumber(bracketValue);
        element.setLength(exampleBracket.length() + 2);

        return element;
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
