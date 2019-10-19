import java.util.LinkedList;
import java.util.List;

public class CalculateString {
    public static double calculateString(final String example) {
        ExampleValidation check = new ExampleValidation();

        if (check.checkExpression(example))
            return calculate(example);

        else
            throw new StringException("Какая-то неведомая ошибка");
    }

    static double calculate(final String example) {
        List<Double> numberList = getNumberList(example);
        List<Character> signList = getSignList(example);

        return CalculateExampleElement.calculateExpressionValue(numberList, signList);
    }

    private static List<Character> getSignList(final String subExample) {
        List<Character> signList = new LinkedList<>();
        int i = 0;
        char symbol;

        signList.add(null);

        while (i < subExample.length()) {
            symbol = subExample.charAt(i);

            SymbolType symbolType = SymbolValidation.getSymbolType(symbol);

            if (symbolType == SymbolType.SIGN) {
                signList.add(symbol);

            } else if (symbolType == SymbolType.BRACKET) {
                i += getClosingBracketIndex(subExample.substring(i));
            }

            i++;
        }
        return signList;
    }

    private static List<Double> getNumberList(final String subExample) {
        List<Double> numberList = new LinkedList<>();
        ExampleElement exampleElement = new ExampleElement();
        int i = 0;

        while (i < subExample.length()) {
            char symbol = subExample.charAt(i);
            SymbolType symbolType = SymbolValidation.getSymbolType(symbol);

            if (symbolType == SymbolType.DIGIT) {
                exampleElement = Example.getNumberFromExample(subExample.substring(i));

            } else if (symbolType == SymbolType.LETTER) {
                exampleElement = Example.getFunctionFromExample(subExample.substring(i));

            } else if (symbolType == SymbolType.BRACKET) {
                exampleElement = Example.getBracketFromExample(subExample.substring(i));

            } else if (symbolType == SymbolType.SIGN) {
                if (i == 0) {
                    numberList.add(0.0);
                }
                i++;
                continue;
            }

            numberList.add(exampleElement.getNumber());
            i += exampleElement.getLength();
        }
        return numberList;
    }

    static int getClosingBracketIndex(final String subExample) {
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