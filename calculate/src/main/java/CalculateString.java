import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Character.*;

public class CalculateString {
    private String expression;
    private Map<Integer, Character> signList;
    private Map<Integer, Double> numberList;

    public CalculateString(String expression) {
        this.expression = expression;
    }

    private char getChar(int index) {
        return expression.charAt(index);
    }

    private char getChar(int index, String expression) {
        return expression.charAt(index);
    }

    private Double getNumber(int index) {
        return numberList.get(index);
    }

    private Character getSign(int index) {
        return signList.get(index);
    }

    /**
     * Проверяет выражение на правильность записи и, если оно без ошибок, рассчитывает его значение
     * @return - возвращает значение выражения
     */
    public double calculateString() {
        var check = new ExpressionValidation();

        if (check.checkExpression(expression))
            return calculate(expression);

        else
            throw new StringException("Какая-то неведомая ошибка");
    }

    /**
     * Расчитывает значение введённого выражения
     * @param expression - выражение
     * @return - возвращает значение выражения
     */
    private double calculate(final String expression) {
        numberList = createNumberList(expression);
        signList = createSignList(expression);

        return getExpressionValue(1, signList.size());
    }

    /**
     * Создаёт список знаков выражения
     * @param expression - выражение
     * @return - возвращает мапу со знаками действий в выражении
     */
    private Map<Integer, Character> createSignList(final String expression) {
        var signList = new HashMap<Integer, Character>();
        var signListSize = 0;
        var i = 0;
        String subExpression;

        while (i < expression.length()) {
            var symbol = getChar(i, expression);

            if (checkCharIsSign(symbol)) {
                signListSize++;
                signList.put(signListSize, symbol);

            } else if (symbol == '(') {
                subExpression = expression.substring(i);
                i = i + getClosingBracketIndex(subExpression) - 1;
            }

            i++;
        }
        return signList;
    }

    /**
     * Создаёт список чисел выражения
     * @param expression - выражение
     * @return - возвращает мапу с числами в выражении
     */
    private Map<Integer, Double> createNumberList(final String expression) {
        var numberList = new HashMap<Integer, Double>();
        var number = new StringBuilder();
        var numberListSize = 0;
        var i = 0;
        var value = 0.0;

        String subExpression;
        int closeBracketIndex;

        while (i < expression.length()) {
            var symbol = getChar(i, expression);

            if (symbol == '-' && (i == 0 || getChar(i-1, expression) == '(')) {
                numberListSize++;
                numberList.put(0, 0.0);

            } else if (checkCharIsSign(symbol)) {
                numberListSize++;
                number.delete(0, number.length());

            } else if (isDigit(symbol) || symbol == '.') {
                number.append(symbol);
                value = Double.parseDouble(number.toString());

            } else if (symbol == '(') {
                subExpression = expression.substring(i);
                closeBracketIndex = getClosingBracketIndex(subExpression);

                value = calculate(subExpression.substring(1, closeBracketIndex));
                i = i + closeBracketIndex - 1;

            } else if (symbol == '!') {
                value = factorial(numberList.get(numberListSize));
                number.delete(0, number.length());

            } else if (symbol == 'e') {
                value = Math.exp(1);

            } else if (isLetter(symbol)) {
                subExpression = expression.substring(i);
                closeBracketIndex = getClosingBracketIndex(subExpression);

                value = getFunctionValue(subExpression.substring(0, closeBracketIndex+1));
                i = i + closeBracketIndex - 1;
            }

            numberList.put(numberListSize, value);
            i++;
        }
        return numberList;
    }

    /**
     * Проверяет, является ли символ знаком арифметического действия
     * @param symbol - символ
     * @return - возвращает true, если символ является одним из знаков арифметических действий
     */
    private boolean checkCharIsSign(final char symbol) {
        var signList = List.of('+', '-', '*', '/', '^');
        return signList.contains(symbol);
    }

    /**
     * Расчитывает значение выражения в указанном интервале чисел и знаков
     * @param firstElement - индекс первых элементов из списков чисел и знаков
     * @param lastElement - индекс последних элементов из списков чисел и знаков
     * @return - возвращает значение выражения
     */
    private double getExpressionValue(int firstElement, int lastElement) {
        var i = firstElement;
        var value = getNumber(i-1);

        while (i <= lastElement) {
            var number = getNumber(i);

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

            } else if (checkSignInList(i, '+', "*/")) {
                value += getExpressionValue(++i, lastElement);
                break;

            } else if (checkSignInList(i, '-', "*/^")) {
                var closeIndex = getLastIndex(i, lastElement);

                value -= getExpressionValue(++i, closeIndex);
                i = closeIndex + 1;
                continue;

            } else if (checkSignInList(i, '+', "^")) {
                value += getExpressionValue(i, lastElement);
                break;

            } else if (checkSignInList(i, '*', "^")) {
                value *= getExpressionValue(++i, i);

            } else if (checkSignInList(i, '/', "^")) {
                var denominator = getExpressionValue(++i, i);
                if (denominator != 0)
                    value /= denominator;
                else
                    throw new ArithmeticException("Деление на ноль");

            } else if (getSign(i) == '^') {
                value = Math.pow(value, getNumber(i));
            }
            i++;
        }

        return value;
    }

    /**
     * Находит индекс последнего знака, до которого нужно производить расчёт
     * @param firstIndex
     * @param lastIndex
     * @return
     */
    private int getLastIndex(final int firstIndex, final int lastIndex) {
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

//    /**
//     * Проверяет, стоит ли за одним знаком другой
//     * @param index
//     * @param currentSign
//     * @param nextSign
//     * @return
//     */
//    private boolean checkNextSignContainsValue(final int index, final char currentSign, final char nextSign) {
//        try {
//            return currentSign == getSign(index) && nextSign == getSign(index + 1);
//
//        } catch (Exception e) {
//            return currentSign == getSign(index);
//        }
//    }
//
//    private boolean checkNextSignNotContainsValue(final int index, final char currentSign, final char nextSign) {
//        try {
//            return currentSign == getSign(index) && nextSign != getSign(index + 1);
//
//        } catch (Exception e) {
//            return currentSign == getSign(index);
//        }
//    }

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
    private boolean checkSignInList(final int index, final char currentSign, final String signList) {
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

    /**
     * Составляет имя первой функции в выражении
     * @param expression - выражение
     * @return - возвращает имя функции
     */
    private String getFunctionName(final String expression) {
        var functionName = new StringBuilder();

        for (int i=0; getChar(i, expression) != '('; i++) {
            functionName.append(getChar(i, expression));
        }

        return functionName.toString();
    }

    /**
     * Нахождит значение тригонометрической функции
     * @param expression - выражение
     * @return - возвращает значение тригонометрической функции
     */
    private double getFunctionValue(final String expression) {
        var rad = Math.acos(-1)/180;
        var functionName = getFunctionName(expression);
        var subExpression = expression.substring(functionName.length());

        switch (functionName) {
            case "sin":
                return Math.sin(calculate(subExpression) * rad);

            case "cos":
                return Math.cos(calculate(subExpression) * rad);

            case "tan":
                return Math.tan(calculate(subExpression) * rad);

            case "ln":
                var value = calculate(subExpression);
                if (value > 0)
                    return Math.log(value);
                else
                    throw new ArithmeticException("Аргумент логарифма должен быть положительным");

            case "abs":
                return Math.abs(calculate(subExpression));

            case "asin":
                return Math.asin(calculate(subExpression)) / rad;

            case "acos":
                return Math.acos(calculate(subExpression)) / rad;

            case "atan":
                return Math.atan(calculate(subExpression)) / rad;

            case "sinh":
                return Math.sinh(calculate(subExpression) * rad);

            case "cosh":
                return Math.cosh(calculate(subExpression) * rad);

            case "tanh":
                return Math.tanh(calculate(subExpression) * rad);

            case "sqrt":
                return Math.sqrt(calculate(subExpression));

            default:
                throw new StringException("Неизвестная функция");
        }
    }

    /**
     * Находит индекс последней закрывающей скобки в выражении
     * @param expression - выражение
     * @return - возвращает индекс закрывающей скобки
     */
    private int getClosingBracketIndex(final String expression) {
        var bracketAmount = 0;
        var i = 0;
        var currentChar = getChar(i, expression);

        if (currentChar == '(')
            bracketAmount = 1;

        while (!(bracketAmount == 0 && currentChar == ')')) {
            currentChar = getChar(++i, expression);

            if (getChar(i, expression) == ')') {
                bracketAmount--;

            } else if (getChar(i, expression) == '(') {
                bracketAmount++;
            }
        }

        return i;
    }

    /**
     * Находит факториал числа
     * @param number - число
     * @return - возвращает факториал числа типом Double
     */
    private double factorial(double number) {
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