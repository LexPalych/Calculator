package calculate;

import java.util.LinkedList;
import java.util.List;

import static calculate.CalculateExampleElement.calculateExpressionValue;
import static calculate.Example.*;
import static calculate.SymbolType.Symbol.*;
import static calculate.SymbolType.getSymbolType;

public class CalculateString {
    public static double calculateString(final String example) {
        ExampleValidation check = new ExampleValidation();

        if (check.checkExpression(example))
            return calculate(example);

        else
            throw new StringException("Какая-то неведомая ошибка");
    }

    public static double calculate(final String example) {
        List<Double> numberList = getNumberList(example);
        List<Character> signList = getSignList(example);

        return calculateExpressionValue(numberList, signList);
    }

    private static List<Character> getSignList(final String subExample) {
        List<Character> signList = new LinkedList<>();
        int i = 0;
        char symbol;

        signList.add(null);

        while (i < subExample.length()) {
            symbol = subExample.charAt(i);

            SymbolType.Symbol symbolType = getSymbolType(symbol);

            if (symbolType == SIGN) {
                signList.add(symbol);

            } else if (symbolType == BRACKET) {
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
            SymbolType.Symbol symbolType = getSymbolType(symbol);

            if (symbolType == DIGIT) {
                exampleElement = getExampleNumber(subExample.substring(i));

            } else if (symbolType == LETTER) {
                exampleElement = getExampleFunction(subExample.substring(i));

            } else if (symbolType == BRACKET) {
                exampleElement = getExampleBracket(subExample.substring(i));

            } else if (symbolType == SIGN) {
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