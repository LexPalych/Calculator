package calculate;

import org.junit.jupiter.api.function.Executable;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Character.*;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

class ExampleValidation {
    /**
     * Проверяет выражение на правильность записи
     */
    static void checkExample(final String expression) {

        List<Character> expressionCharList = expression
                .chars()
                .mapToObj(c -> (char) c)
                .collect(toList());

        List<Executable> executableList = new LinkedList<>();

        executableList.addAll(checkIncorrectSigns(expressionCharList));
        executableList.addAll(checkBracketAmount(expressionCharList));
        executableList.addAll(checkBracketOrder(expressionCharList));
        executableList.addAll(checkArgumentBracket(expressionCharList));
        executableList.addAll(checkExpressionInBracketIsCorrect(expressionCharList));
        executableList.addAll(checkSymbolBeforeFunction(expressionCharList));
        executableList.addAll(checkSeveralSignConsecutive(expressionCharList));
        executableList.addAll(checkFactorial(expressionCharList));
        executableList.addAll(checkFirstSymbol(expressionCharList));
        executableList.addAll(checkLastSymbol(expressionCharList));
        executableList.addAll(checkNoSpace(expressionCharList));
        executableList.addAll(checkNoComma(expressionCharList));
        executableList.addAll(checkNoOnlyLetter(expressionCharList));

        assertAll(executableList);
    }

    /**
     * Проверяет наличие некорректных символов в выражении
     */
    private static List<Executable> checkIncorrectSigns(final List<Character> expressionCharList) {
        List<Character> signList = List.of('(', ')', '+', '-', '*', '/', '!', '^', '.');

        return expressionCharList
                .stream()
                .map(symbol -> (Executable) () ->
                        assertTrue(isLetter(symbol) || isDigit(symbol) || signList.contains(symbol),
                        "Симол " + symbol + " является некорректным")
                )
                .collect(toList());
    }

    /**
     * Проверяет сопадение в количестве открывающий и закрыающих скобочек
     */
    private static List<Executable> checkBracketAmount(final List<Character> expressionCharList) {
        List<Executable> executableList = new LinkedList<>();

        int openingBracket = (int) expressionCharList
                .stream()
                .filter(symbol -> symbol.equals('('))
                .count();

        int closingBracket = (int) expressionCharList
                .stream()
                .filter(symbol -> symbol.equals(')'))
                .count();

        executableList.add(() -> assertEquals(openingBracket, closingBracket, "Неверное количество скобочек"));

        return executableList;
    }

    /**
     * Проверяет наличие закрывающей скобочки перед открыающей
     */
    private static List<Executable> checkBracketOrder(final List<Character> expressionCharList) {
        List<Executable> executableList = new LinkedList<>();
        int bracket = 0;

        for (int i = 0; i < expressionCharList.size(); i++) {
            int j = i;
            char symbol = expressionCharList.get(j);

            if (symbol == '(') {
                bracket++;

            } else if (symbol == ')') {
                bracket--;
            }

            if (bracket < 0) {
                executableList.add(() -> fail("Закрывающая скобочка стоит перед открывающей (позиция " + j + " )"));
                bracket = 0;
            }
        }

        return executableList;
    }

    /**
     * Проверяет наличие скобочки перед выражением аргумента функции
     */
    private static List<Executable> checkArgumentBracket(final List<Character> expressionCharList) {
        List<Executable> executableList = new LinkedList<>();

        for (int i = 1; i < expressionCharList.size(); i++) {
            int j = i;

            executableList.add(() ->
                    assertFalse(isDigit(expressionCharList.get(j)) && isLetter(expressionCharList.get(j-1)),
                    "Отсутствует скобочка перед аргументом на позиции " + j)
            );
        }

        return executableList;
    }

    /**
     * Проверяет наличие в скобочках какого-либо выражения
     */
    private static List<Executable> checkExpressionInBracketIsCorrect(final List<Character> expressionCharList) {
        List<Character> signList = List.of('+', '-', '*', '/', '^', '.', '(');
        List<Executable> executableList = new LinkedList<>();

        for (int i = 1; i < expressionCharList.size(); i++) {
            int j = i;

            executableList.add(() ->
                    assertFalse(expressionCharList.get(j) == ')' && signList.contains(expressionCharList.get(j)),
                    "Отсутстует выражение перед скобочкой на позиции " + j)
            );
        }

        return executableList;
    }

    /**
     * Проверянт наличие перед функцией некорректных симолов
     */
    private static List<Executable> checkSymbolBeforeFunction(final List<Character> expressionCharList) {
        List<Character> signList = List.of(')', '.', '!');
        List<Executable> executableList = new LinkedList<>();

        for (int i = 1; i < expressionCharList.size(); i++) {
            char currentSymbol = expressionCharList.get(i);
            char prevSymbol = expressionCharList.get(i-1);

            executableList.add(() ->
                    assertFalse(isLetter(currentSymbol) && ((isDigit(prevSymbol) || signList.contains(prevSymbol))),
                    "Некорректный символ (" + prevSymbol + ") перед функцией")
            );
        }

        return executableList;
    }

    /**
     * Проверяет наличие подряд стоящих знаков
     */
    private static List<Executable> checkSeveralSignConsecutive(final List<Character> expressionCharList) {
        List<Character> signList = List.of('+', '-', '*', '/', '^', '.');
        List<Executable> executableList = new LinkedList<>();

        for (int i = 0; i < expressionCharList.size()-1; i++) {
            int j = i;
            char currentSymbol = expressionCharList.get(j);
            char nextSymbol = expressionCharList.get(j+1);

            executableList.add(() ->
                    assertFalse(signList.contains(currentSymbol) && signList.contains(nextSymbol),
                    "Несколько знаков подряд начиная с позиции " + j)
            );
        }

        return executableList;
    }

    /**
     * Проверить, что в выражении перед факториалом отсутствуют некорректные символы
     * @return - возвращает true, если в выражении перед факториалом отсутствуют некорректные символы
     */
    private static Executable checkFactorial(final String expression) {
        char symbol;
        char frontSymbol;

        for (int i = 1; i < expression.length(); i++) {
            symbol = expression.charAt(i);
            frontSymbol = expression.charAt(i-1);

            if (symbol == '!' && !(isDigit(frontSymbol) || frontSymbol == ')' || frontSymbol == '!'))
                throw new StringException("Некорректный аргумент фактоиала");
        }

        return true;
    }

    /**
     * Проверить, что выражение начинается с корректного символа
     * @return - возвращает true, если выражение начинается с корректного символа
     */
    private static Executable checkFirstSymbol(final String expression) {
        List<Character> signList = List.of('+', '*', '/', '!', '^', '.');

        if (signList.contains(expression.charAt(0)))
            throw new StringException("Некорректный первый символ выражения");

        return true;
    }

    /**
     * Проверить, что выражение заканчивается корректным символом
     * @return - возвращает true, если выражение заканчивается с корректного символа
     */
    private static Executable checkLastSymbol(final String expression) {
        List<Character> signList = List.of('+', '-', '*', '/', '^', '.');

        if (signList.contains(expression.charAt(expression.length() - 1)))
            throw new StringException("Некорректный последний символ выражения");

        return true;
    }

    /**
     * Проверить, что в выражении отсутствуют пробелы
     * @return - возвращает true, если в выражении отсутствуют пробелы
     */
    private static Executable checkNoSpace(final String expression) {
        for (int i = 1; i < expression.length(); i++) {
            if (expression.charAt(i) == ' ')
                throw new StringException("Выражение не должно содержать пробелов");
        }

        return true;
    }

    /**
     * Проверить, что в выражении отсутствуют запятые
     * @return - возвращает true, если в выражении отсутствуют запятые
     */
    private static Executable checkNoComma(final String expression) {
        for (int i = 1; i < expression.length(); i++) {
            if (expression.charAt(i) == ',')
                throw new StringException("Выражение не должно содержать запятыхю. Используйте точку");
        }

        return true;
    }

    /**
     * Проверить, что в выражении присутствуют числа или константы
     * @return - возвращает true, если в выражении присутствуют числа или константы
     */
    private static Executable checkNoOnlyLetter(final String expression) {
        char symbol;

        for (int i = 0; i < expression.length(); i++) {
            symbol = expression.charAt(i);

            if (isDigit(symbol))
                return true;
        }

        throw new StringException("В выражении отсутствуют числа");
    }

}