package calculate;

import java.util.List;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;

class ExampleValidation {
    /**
     * Проверяет выражение на правильность записи
     * @return - возвращает true, если ошибок нет
     */
    static boolean checkExample(final String expression) {
        return checkIncorrectSigns(expression) &&
                checkBracketAmount(expression) &&
                checkBracketOrder(expression) &&
                checkArgumentBracket(expression) &&
                checkExpressionInBracketIsCorrect(expression) &&
                checkSymbolBeforeFunction(expression) &&
                checkSeveralSignConsecutive(expression) &&
                checkFactorial(expression) &&
                checkFirstSymbol(expression) &&
                checkLastSymbol(expression) &&
                checkNoSpace(expression) &&
                checkNoComma(expression) &&
                checkNoOnlyLetter(expression);
    }

    /**
     * Проверяет, что нет некорректных символов
     * @return - возвращает true, если в выражении нет некорректных символов
     */
    private static boolean checkIncorrectSigns(final String expression) {
        List<Character> signList = List.of('(', ')', '+', '-', '*', '/', '!', '^', '.');
        char symbol;

        for (int i = 0; i < expression.length(); i++) {
            symbol = expression.charAt(i);

            if (!(isLetter(symbol) || isDigit(symbol) || signList.contains(symbol)))
                throw new StringException("Введён некорректный символ");
        }

        return true;
    }

    /**
     * Проверяет, что количество открывающих скобочек равно количеству закрывающих
     * @return - возвращает true, если в выражении количество открывающих скобочек равно количеству закрывающих
     */
    private static boolean checkBracketAmount(final String expression) {
        int bracket = 0;

        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                bracket++;

            } else if (expression.charAt(i) == ')') {
                bracket--;
            }
        }

        if (bracket != 0)
            throw new StringException("Неверное количество скобочек");

        return true;
    }

    /**
     * Проверить, что закрывающая скобочка стоит после открывающей
     * @return - возвращает true, если в выражении открывающая и закрывающая скобочки стоят в правильнм порядке
     */
    private static boolean checkBracketOrder(final String expression) {
        int bracket = 0;

        for (int i = 0; i < expression.length(); i++) {

            if (expression.charAt(i) == '(') {
                bracket++;

            } else if (expression.charAt(i) == ')') {
                if (bracket == 0)
                    throw new StringException("Закрывающая скобочка стоит перед открывающей");
                else
                    bracket--;
            }
        }

        return true;
    }

    /**
     * Проверить, что перед аргументом стоит открывающая скобочка
     * @return - возвращает true, если в выражении количество открывающих скобочек равно количеству закрывающих
     */
    private static boolean checkArgumentBracket(final String expression) {
        for (int i = 1; i < expression.length(); i++) {
            if (isDigit(expression.charAt(i)) && isLetter(expression.charAt(i-1)))
                throw new StringException("Отсутствует открывающая скобочка перед аргументом");
        }

        return true;
    }

    /**
     * Проверить, что в скобочках присутствует выражение
     * @return - возвращает true, если в скобочках присутствует выражение
     */
    private static boolean checkExpressionInBracketIsCorrect(final String expression) {
        List<Character> signList = List.of('+', '-', '*', '/', '^', '.', '(');
        char symbol;
        char frontSymbol;

        for (int i = 1; i < expression.length(); i++) {
            symbol = expression.charAt(i);
            frontSymbol = expression.charAt(i-1);

            if (symbol == ')' && signList.contains(frontSymbol))
                throw new StringException("Выражение в скобочках некорректно");
        }

        return true;
    }

    /**
     * Проверить, что перед именем функции отсутствуют некорректные символы
     * @return - возвращает true, если перед именем функции отсутствуют некорректные символы
     */
    private static boolean checkSymbolBeforeFunction(final String expression) {
        char symbol;
        char frontSymbol;

        for (int i = 1; i < expression.length(); i++) {
            symbol = expression.charAt(i);
            frontSymbol = expression.charAt(i-1);

            if (isLetter(symbol) &&
                    ((isDigit(frontSymbol) || frontSymbol == '.' || frontSymbol == ')' || frontSymbol == '!')))
                throw new StringException("Некорректный символ перед функцией");
        }

        return true;
    }

    /**
     * Проверить, что в выражении отсутствуют подряд стоящие знаки
     * @return - возвращает true, если в выражении отсутствуют подряд стоящие знаки
     */
    private static boolean checkSeveralSignConsecutive(final String expression) {
        List<Character> signList = List.of('+', '-', '*', '/', '^', '.');

        for (int i = 1; i < expression.length(); i++) {
            if (signList.contains(expression.charAt(i)) && signList.contains(expression.charAt(i-1)))
                throw new StringException("Два или более знака подряд");
        }

        return true;
    }

    /**
     * Проверить, что в выражении перед факториалом отсутствуют некорректные символы
     * @return - возвращает true, если в выражении перед факториалом отсутствуют некорректные символы
     */
    private static boolean checkFactorial(final String expression) {
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
    private static boolean checkFirstSymbol(final String expression) {
        List<Character> signList = List.of('+', '*', '/', '!', '^', '.');

        if (signList.contains(expression.charAt(0)))
            throw new StringException("Некорректный первый символ выражения");

        return true;
    }

    /**
     * Проверить, что выражение заканчивается корректным символом
     * @return - возвращает true, если выражение заканчивается с корректного символа
     */
    private static boolean checkLastSymbol(final String expression) {
        List<Character> signList = List.of('+', '-', '*', '/', '^', '.');

        if (signList.contains(expression.charAt(expression.length() - 1)))
            throw new StringException("Некорректный последний символ выражения");

        return true;
    }

    /**
     * Проверить, что в выражении отсутствуют пробелы
     * @return - возвращает true, если в выражении отсутствуют пробелы
     */
    private static boolean checkNoSpace(final String expression) {
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
    private static boolean checkNoComma(final String expression) {
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
    private static boolean checkNoOnlyLetter(final String expression) {
        char symbol;

        for (int i = 0; i < expression.length(); i++) {
            symbol = expression.charAt(i);

            if (isDigit(symbol))
                return true;
        }

        throw new StringException("В выражении отсутствуют числа");
    }

}