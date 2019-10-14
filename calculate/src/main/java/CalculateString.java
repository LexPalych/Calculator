import java.util.LinkedList;
import java.util.List;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;

public class CalculateString {
    private static String expression;

//    public static String getExpression() {
//        return expression;
//    }
//
//    public static void setExpression(String expression) {
//        CalculateString.expression = expression;
//    }

    public CalculateString(String expression) {
        this.expression = expression;
    }

//    private static char getChar(int index) {
//        return expression.charAt(index);
//    }

    public double calculateString() {
        ExpressionValidation check = new ExpressionValidation();
        ExpressionElement expressionElement = new ExpressionElement();

        expressionElement.setExpression(expression);

        if (check.checkExpression(expressionElement.getExpression()))
            return calculate(expressionElement.getExpression());

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

    private static SymbolType getSymbolType(final int index, final String expression) {
        return checkSymbolType(expression.charAt(index));
    }

    private static ExpressionElement setNumberInExpressionElement(final int firstNumberIndex) {
        int lastNumberIndex = firstNumberIndex;
        String numberAsString;
        ExpressionElement expressionElement = new ExpressionElement();

        while (lastNumberIndex < expression.length() && getSymbolType(lastNumberIndex, expression) == SymbolType.DIGIT) {
            lastNumberIndex++;
        }

        numberAsString = expression.substring(firstNumberIndex, lastNumberIndex);
        expressionElement.setNumber(Double.parseDouble(numberAsString));
        expressionElement.setLastSymbolIndex(lastNumberIndex-1);

        return expressionElement;
    }

    private static ExpressionElement setFunctionInExpressionElement(final int firstFunctionIndex) {
        ExpressionElement expressionElement = new ExpressionElement();

        int lastFunctionIndex = getClosingBracketIndex(firstFunctionIndex);
        double functionValue = CalculateFunction.getFunctionValue(expression.substring(firstFunctionIndex, lastFunctionIndex));

        expressionElement.setNumber(functionValue);
        expressionElement.setLastSymbolIndex(lastFunctionIndex);

        return expressionElement;
    }

    private static List<Character> createSignList(final String subExpression) {
        List<Character> signList = new LinkedList<>();
        int i = 0;
        char symbol;
        signList.add('!');

        while (i < subExpression.length()) {
            symbol = subExpression.charAt(i);

            SymbolType symbolType = getSymbolType(i, subExpression);

            if (symbolType == SymbolType.SIGN) {
                signList.add(symbol);

            } else if (symbolType == SymbolType.BRACKET) {
                i = getClosingBracketIndex(i);
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
//            char symbol = subExpression.charAt(i);
            SymbolType symbolType = getSymbolType(i, subExpression);

            if (symbolType == SymbolType.DIGIT) {
                expressionElement = setNumberInExpressionElement(i);

            } else if (symbolType == SymbolType.SIGN && i == 0) {
                expressionElement.setNumber(0.0);
                expressionElement.setLastSymbolIndex(i);

            } else if (symbolType == SymbolType.SIGN && i != 0) {
                i++;
                continue;

            } else if (symbolType == SymbolType.LETTER) {
                expressionElement = setFunctionInExpressionElement(i);

            } else if (symbolType == SymbolType.BRACKET) {
                closeBracketIndex = getClosingBracketIndex(i);
                expressionElement.setNumber(calculate(subExpression.substring(i+1, closeBracketIndex-1)));
                expressionElement.setLastSymbolIndex(closeBracketIndex);

            } /*else if (symbol == '!') {
                expressionElement.setNumber(factorial(numberList.get(numberList.size()-1)));
            }*/

            numberList.add(expressionElement.getNumber());
            i = expressionElement.getLastSymbolIndex() + 1;
        }
        return numberList;
    }

    private static int getClosingBracketIndex(final int firstBracketIndex) {
        int bracketAmount = 0;
        int lastBracketIndex = firstBracketIndex;
        char currentChar;

        do {
//            currentChar = getChar(lastBracketIndex++);
            currentChar = expression.charAt(lastBracketIndex++);

            if (currentChar == '(') {
                bracketAmount++;

            } else if (currentChar == ')') {
                bracketAmount--;
            }
        } while (!(bracketAmount == 0 && currentChar == ')'));

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