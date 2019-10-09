import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Character.*;

public class CalculateString {
    private static String expression;
    private static Map<Integer, Character> signList;
    private static Map<Integer, Double> numberList;

    public CalculateString(String expression) {
        this.expression = expression;
    }

    private static char getChar(int index) {
        return expression.charAt(index);
    }

    private static char getChar(int index, String expression) {
        return expression.charAt(index);
    }

    private static Character getSign(int index) {
        return signList.get(index);
    }

    public static double calculateString() {
        var check = new ExpressionValidation();

        if (check.checkExpression(expression))
            return calculate(expression);

        else
            throw new StringException("Какая-то неведомая ошибка");
    }

//    static double calculate(final String expression) {
//        numberList = createNumberList(expression);
//        signList = createSignList(expression);
//
//        return getExpressionValue(1, signList.size());
//    }

    static double calculate(final String expression) {
        Map<Integer, Double> numberList = createNumberList(expression);
        Map<Integer, Character> signList = createSignList(expression);

        return getExpressionValue(numberList, signList);
    }

    private static SymbolType checkSymbolType(final char symbol) {
        List<Character> signList = List.of('+', '-', '*', '/', '^');

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

    private static SymbolType getSymbolType(final int index) {
        return checkSymbolType(getChar(index));
    }

    private static ExpressionElement setNumberInExpressionElement(final int firstNumberIndex) {
        int lastNumberIndex = firstNumberIndex;
        String numberAsString;
        ExpressionElement expressionElement = new ExpressionElement();

        while (getSymbolType(lastNumberIndex) == SymbolType.DIGIT && lastNumberIndex < expression.length()) {
            lastNumberIndex++;
        }
        lastNumberIndex--;
        numberAsString = expression.substring(firstNumberIndex, lastNumberIndex);
        expressionElement.setNumber(Double.parseDouble(numberAsString));
        expressionElement.setLastSymbolIndex(lastNumberIndex);

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

            SymbolType symbolType = getSymbolType(i);

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
//        int numberListSize = 0;
        int i = 0;

        int closeBracketIndex;

        ExpressionElement expressionElement = new ExpressionElement();

        while (i < subExpression.length()) {
            char symbol = subExpression.charAt(i);
            SymbolType symbolType = getSymbolType(i);

            if (symbolType == SymbolType.DIGIT) {
                expressionElement = setNumberInExpressionElement(i);
//                numberListSize++;

            } else if (symbolType == SymbolType.SIGN && i == 0) {
                expressionElement.setNumber(0.0);
                expressionElement.setLastSymbolIndex(i);

            } else if (symbolType == SymbolType.LETTER) {
                expressionElement = setFunctionInExpressionElement(i);
//                numberListSize++;

            } else if (symbolType == SymbolType.BRACKET) {
                closeBracketIndex = getClosingBracketIndex(i);
                expressionElement.setNumber(calculate(subExpression.substring(i+1, closeBracketIndex-1)));
                expressionElement.setLastSymbolIndex(closeBracketIndex);
//                numberListSize++;

            } else if (symbol == '!') {
                expressionElement.setNumber(factorial(numberList.get(numberList.size()-1)));
            }

            numberList.add(expressionElement.getNumber());
            i = expressionElement.getLastSymbolIndex() + 1;
        }
        return numberList;
    }

    private static double getExpressionValue(List<Double> numberList, List<Character> signList) {
        int i = 1;
        double value = numberList.get(0);

        while (i < numberList.size()) {
            var number = numberList.get(i);

            if (checkSignInList(i, '+', "+-")) {
                value += number;

            } else if (checkSignInList(i, '-', "+-")) {
                value -= number;

            } else if (checkSignInList(i, '*', "+-*/")) {
                value *= number;

            } else if (checkSignInList(i, '/', "+-*/")) {
                if (number != 0)
                    value /= number;
                else
                    throw new ArithmeticException("Деление на ноль");

            } else if (checkSignInList(i, '+', "*/^")) {
                value += getExpressionValue(++i, lastElement);
                break;

            } else if (checkSignInList(i, '-', "*/^")) {
                var closeIndex = getLastIndex(i, lastElement);

                value -= getExpressionValue(++i, closeIndex);
                i = closeIndex + 1;
                continue;

            } else if (checkSignInList(i, '*', "^")) {
                value *= getExpressionValue(++i, i);

            } else if (checkSignInList(i, '/', "^")) {
                var denominator = getExpressionValue(++i, i);
                if (denominator != 0)
                    value /= denominator;
                else
                    throw new ArithmeticException("Деление на ноль");

            } else if (getSign(i) == '^') {
                value = Math.pow(value, numberList.get(i));
            }
            i++;
        }

        return value;
    }

//    /**
//     * Расчитывает значение выражения в указанном интервале чисел и знаков
//     * @param firstElement - индекс первых элементов из списков чисел и знаков
//     * @param lastElement - индекс последних элементов из списков чисел и знаков
//     * @return - возвращает значение выражения
//     */
//    private static double getExpressionValue(int firstElement, int lastElement) {
//        var i = firstElement;
//        var value = numberList.get(i-1);
//
//        while (i <= lastElement) {
//            var number = numberList.get(i);
//
//            if (checkSignInList(i, '+', "+-")) {
//                value += number;
//
//            } else if (checkSignInList(i, '-', "+-")) {
//                value -= number;
//
//            } else if (checkSignInList(i, '*', "+-*/")) {
//                value *= number;
//
//            } else if (checkSignInList(i, '/', "+-*/")) {
//                if (number != 0)
//                    value /= number;
//                else
//                    throw new ArithmeticException("Деление на ноль");
//
//            } else if (checkSignInList(i, '+', "*/^")) {
//                value += getExpressionValue(++i, lastElement);
//                break;
//
//            } else if (checkSignInList(i, '-', "*/^")) {
//                var closeIndex = getLastIndex(i, lastElement);
//
//                value -= getExpressionValue(++i, closeIndex);
//                i = closeIndex + 1;
//                continue;
//
//            } else if (checkSignInList(i, '*', "^")) {
//                value *= getExpressionValue(++i, i);
//
//            } else if (checkSignInList(i, '/', "^")) {
//                var denominator = getExpressionValue(++i, i);
//                if (denominator != 0)
//                    value /= denominator;
//                else
//                    throw new ArithmeticException("Деление на ноль");
//
//            } else if (getSign(i) == '^') {
//                value = Math.pow(value, numberList.get(i));
//            }
//            i++;
//        }
//
//        return value;
//    }

    /**
     * Находит индекс последнего знака, до которого нужно производить расчёт
     * @param firstIndex
     * @param lastIndex
     * @return
     */
    private static int getLastIndex(final int firstIndex, final int lastIndex) {
        var i = firstIndex;

        while (true) {
            i++;
            var currentSign = getSign(i);

            if (currentSign == '+' || currentSign == '-')
                return i-1;

            else if (i == lastIndex)
                return i;
        }

    }

    /**
     * Проверяет, что по определённому индексу находится определённый знак;
     * Проверяет, является ли этот знак последним;
     * Если нет, проверяет, содержится ли следующий за ним знак в определённом списке
     * @param index - индекс знака
     * @param currentSign - знак
     * @param signList - список знаков
     * @return - возвращает true, если по определённому индексу находитс определённый знак
     *          и следующий за ним знак находится в определённом списке знаков, либо если этот знак является последним
     */
    private static boolean checkSignInList(final int index, final char currentSign, final String signList) {
        var nextSign = getSign(index+1);
        if (nextSign != null) {
            var signInList =  signList
                    .chars()
                    .mapToObj(i -> (char) i)
                    .collect(Collectors.toList())
                    .contains(nextSign);

            return (getSign(index) == currentSign) && signInList;
        }

        return (getSign(index) == currentSign);
    }

    private static int getClosingBracketIndex(final int firstBracketIndex) {
        int bracketAmount = 0;
        int lastBracketIndex = firstBracketIndex;
        char currentChar;

        do {
            currentChar = getChar(lastBracketIndex++);

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
    private static double factorial(double number) {
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