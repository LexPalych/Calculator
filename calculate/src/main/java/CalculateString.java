import java.util.LinkedList;
import java.util.List;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;

public class CalculateString {
//    private static String expression;

//    public static String getExpression() {
//        return expression;
//    }
//
//    public static void setExpression(String expression) {
//        CalculateString.expression = expression;
//    }

//    public CalculateString(String expression) {
//        this.expression = expression;
//    }

//    private static char getChar(int index) {
//        return expression.charAt(index);
//    }

    public static double calculateString(final String expression) {
        ExpressionValidation check = new ExpressionValidation();
        ExpressionElement expressionElement = new ExpressionElement();

        expressionElement.setExpression(expression);

        if (check.checkExpression(expression))
            return calculate(expression);

        else
            throw new StringException("Какая-то неведомая ошибка");
    }

    static double calculate(final String expression) {
        List<Double> numberList = createNumberList(expression);
        List<Character> signList = createSignList(expression);

        return CalculateExpressionElement.calculateExpressionValue(numberList, signList);
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

//    private static SymbolType getSymbolType(final int index, final String expression) {
//        return checkSymbolType(expression.charAt(index));
//    }

    private static ExpressionElement setNumberInExpressionElement(final String subExpression) {
        int lastNumberIndex = 0;
        String numberAsString;
        ExpressionElement expressionElement = new ExpressionElement();

        while (lastNumberIndex < subExpression.length() && checkSymbolType(subExpression.charAt(lastNumberIndex)) == SymbolType.DIGIT) {
            lastNumberIndex++;
        }

        numberAsString = subExpression.substring(0, lastNumberIndex);
        expressionElement.setNumber(Double.parseDouble(numberAsString));
        expressionElement.setLastSymbolIndex(lastNumberIndex-1);

        return expressionElement;
    }

    private static ExpressionElement setFunctionInExpressionElement(final String subExpression) {
        ExpressionElement expressionElement = new ExpressionElement();

        int lastFunctionIndex = getClosingBracketIndex(subExpression);
        double functionValue = CalculateFunction.getFunctionValue(subExpression.substring(0, lastFunctionIndex+1));

        expressionElement.setNumber(functionValue);
        expressionElement.setLastSymbolIndex(lastFunctionIndex);

        return expressionElement;
    }

    private static List<Character> createSignList(final String subExpression) {
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

    private static List<Double> createNumberList(final String subExpression) {
        List<Double> numberList = new LinkedList<>();
        int i = 0;
        int closeBracketIndex;
        ExpressionElement expressionElement = new ExpressionElement();

        while (i < subExpression.length()) {
            char symbol = subExpression.charAt(i);
            SymbolType symbolType = checkSymbolType(symbol);

            if (symbolType == SymbolType.DIGIT) {
                expressionElement = setNumberInExpressionElement(subExpression.substring(i));
                expressionElement.setLastSymbolIndex(i + expressionElement.getLastSymbolIndex());

            } else if (symbolType == SymbolType.SIGN && i == 0) {
                expressionElement.setNumber(0.0);
                expressionElement.setLastSymbolIndex(i);

            } else if (symbolType == SymbolType.SIGN && i != 0) {
                i++;
                continue;

            } else if (symbolType == SymbolType.LETTER) {
                expressionElement = setFunctionInExpressionElement(subExpression.substring(i));
                expressionElement.setLastSymbolIndex(i + expressionElement.getLastSymbolIndex());

            } else if (symbolType == SymbolType.BRACKET) {
                closeBracketIndex = i + getClosingBracketIndex(subExpression.substring(i));
                expressionElement.setNumber(calculate(subExpression.substring(i+1, closeBracketIndex)));
                expressionElement.setLastSymbolIndex(closeBracketIndex);
            }

            numberList.add(expressionElement.getNumber());
            i = expressionElement.getLastSymbolIndex() + 1;
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

    /**
     * Находит факториал числа
     * @param number - число
     * @return - возвращает факториал числа типом Double
     */
    public static double factorial(double number) {
        if (number < 0)
            throw new ArithmeticException("Отрицательный аргумент факториала");

        if (number % 1 !=0)
            throw new ArithmeticException("Аргумент факториала не является целым числом");

        if (number == 0 || number == 1)
            return 1.0;

        else
            return number * factorial(number-1);
    }

}