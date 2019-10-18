import java.util.LinkedList;
import java.util.List;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;

public class CalculateString {
    public static double calculateString(final String expression) {
        ExampleValidation check = new ExampleValidation();
        ExampleElement exampleElement = new ExampleElement();

        exampleElement.setExpression(expression);

        if (check.checkExpression(expression))
            return calculate(expression);

        else
            throw new StringException("Какая-то неведомая ошибка");
    }

    static double calculate(final String expression) {
        List<Double> numberList = getNumberList(expression);
        List<Character> signList = getSignList(expression);

        return CalculateExampleElement.calculateExpressionValue(numberList, signList);
    }

    private static SymbolType checkSymbolType(final char symbol) {
        List<Character> signList = List.of('+', '-', '*', '/', '^', '!');

        if (signList.contains(symbol)) {
            return SymbolType.SIGN;

        } else if (isDigit(symbol) || symbol == '.') {
            return SymbolType.DIGIT;

        } else if (isLetter(symbol)) {
            return SymbolType.LETTER;

        } else if (symbol == '(') {
            return SymbolType.BRACKET;

        } else {
            throw new SecurityException("Неизестный сивол " + symbol);
        }
    }

    private static ExampleElement getNumberExampleElement(final String subExpression) {
        int lastNumberIndex = 0;
        String number;
        ExampleElement exampleElement = new ExampleElement();

        while (lastNumberIndex < subExpression.length() && checkSymbolType(subExpression.charAt(lastNumberIndex)) == SymbolType.DIGIT) {
            lastNumberIndex++;
        }

        number = subExpression.substring(0, lastNumberIndex);
        exampleElement.setNumber(Double.parseDouble(number));
        exampleElement.setLengthExample(number.length());

        return exampleElement;
    }

    private static ExampleElement getFunctionExampleElement(final String subExpression) {
        ExampleElement exampleElement = new ExampleElement();

        int lastFunctionIndex = getClosingBracketIndex(subExpression);
        String exampleFunction = subExpression.substring(0, lastFunctionIndex+1);
        double functionValue = CalculateFunction.getFunctionValue(exampleFunction);

        exampleElement.setNumber(functionValue);
        exampleElement.setLengthExample(exampleFunction.length());

        return exampleElement;
    }

//    private static List<Character> getSignExampleElement(final Character signChar) {
//        ExampleElement exampleElement = new ExampleElement();
//        exampleElement.setSign(signChar);
//        exampleElement.se
//
//
//
//
//        return signList;
//    }

    private static List<Character> getSignList(final String subExpression) {
        List<Character> signList = new LinkedList<>();
        int i = 0;
        char symbol;
        signList.add(null);

        while (i < subExpression.length()) {
            symbol = subExpression.charAt(i);

            SymbolType symbolType = checkSymbolType(symbol);

            if (symbolType == SymbolType.SIGN) {
                signList.add(symbol);

            } else if (symbolType == SymbolType.BRACKET) {
                i += getClosingBracketIndex(subExpression.substring(i));
            }

            i++;
        }
        return signList;
    }

    private static List<Double> getNumberList(final String subExpression) {
        List<Double> numberList = new LinkedList<>();
        int i = 0;
        int closeBracketIndex;
        int lastElementSymbolIndex = 0;
        ExampleElement exampleElement = new ExampleElement();

        while (i < subExpression.length()) {
            char symbol = subExpression.charAt(i);
            SymbolType symbolType = checkSymbolType(symbol);

            if (symbolType == SymbolType.DIGIT) {
                exampleElement = getNumberExampleElement(subExpression.substring(i));
                lastElementSymbolIndex = i + exampleElement.getLengthExample() - 1;

            } else if (symbolType == SymbolType.SIGN && i == 0) {
                exampleElement.setNumber(0.0);
                lastElementSymbolIndex = i;

            } else if (symbolType == SymbolType.SIGN && i != 0) {
                i++;
                continue;

            } else if (symbolType == SymbolType.LETTER) {
                exampleElement = getFunctionExampleElement(subExpression.substring(i));
                lastElementSymbolIndex = i + exampleElement.getLengthExample() - 1;

            } else if (symbolType == SymbolType.BRACKET) {
                closeBracketIndex = i + getClosingBracketIndex(subExpression.substring(i));
                exampleElement.setNumber(calculate(subExpression.substring(i+1, closeBracketIndex)));
                lastElementSymbolIndex = closeBracketIndex;
            }

            numberList.add(exampleElement.getNumber());
            i = lastElementSymbolIndex + 1;
        }
        return numberList;
    }

    private static int getClosingBracketIndex(final String subExpression) {
        int bracketAmount = 0;
        int lastBracketIndex = 0;
        char currentChar;

        do {
            currentChar = subExpression.charAt(lastBracketIndex++);

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