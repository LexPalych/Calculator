import java.util.List;
import java.util.logging.Logger;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;

class ExampleValidation {
    /**
     * Проверяет выражение на правильность записи
     * @return - возвращает true, если ошибок нет
     */
    boolean checkExpression(final String expression) {
        checkIncorrectSigns(expression)
                .checkBracketAmount(expression)
                .checkBracketOrder(expression)
                .checkArgumentBracket(expression)
                .checkExpressionInBracketIsCorrect(expression)
                .checkSymbolBeforeFunction(expression)
                .checkSeveralSignConsecutive(expression)
                .checkFactorial(expression)
                .checkFirstSymbol(expression)
                .checkLastSymbol(expression)
                .checkNoSpace(expression)
                .checkNoComma(expression);
//                .checkNoOnlyLetter(expression);


        return true;
    }

    /**
     * Проверяет, что нет некорректных символов
     * @return - возвращает true, если в выражении нет некорректных символов
     */
    private ExampleValidation checkIncorrectSigns(final String expression) {
        var signList = List.of('(', ')', '+', '-', '*', '/', '!', '^', '.');

        for (int i = 0; i < expression.length(); i++) {
            var symbol = expression.charAt(i);

            if (!(isLetter(symbol) || isDigit(symbol) || signList.contains(symbol)))
                throw new StringException("Введён некорректный символ");
        }

        return this;
    }

    /**
     * Проверяет, что количество открывающих скобочек равно количеству закрывающих
     * @return - возвращает true, если в выражении количество открывающих скобочек равно количеству закрывающих
     */
    private ExampleValidation checkBracketAmount(final String expression) {
        var bracket = 0;

        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(')
                bracket++;

            else if (expression.charAt(i) == ')') {
                bracket--;
            }
        }

        if (bracket != 0)
            throw new StringException("Неверное количество скобочек");

        return this;
    }

    /**
     * Проверить, что закрывающая скобочка стоит после открывающей
     * @return - возвращает true, если в выражении открывающая и закрывающая скобочки стоят в правильнм порядке
     */
    private ExampleValidation checkBracketOrder(final String expression) {
        var bracket = 0;

        for (int i = 0; i < expression.length(); i++) {

            if (expression.charAt(i) == '(')
                bracket++;

            else if (expression.charAt(i) == ')') {
                if (bracket == 0)
                    throw new StringException("Закрывающая скобочка стоит перед открывающей");
                else
                    bracket--;
            }
        }

        return this;
    }

    /**
     * Проверить, что перед аргументом стоит открывающая скобочка
     * @return - возвращает true, если в выражении количество открывающих скобочек равно количеству закрывающих
     */
    private ExampleValidation checkArgumentBracket(final String expression) {
        for (int i = 1; i < expression.length(); i++) {
            if(isDigit(expression.charAt(i)) && isLetter(expression.charAt(i-1)))
                throw new StringException("Отсутствует открывающая скобочка перед аргументом");
        }

        return this;
    }

    /**
     * Проверить, что в скобочках присутствует выражение
     * @return - возвращает true, если в скобочках присутствует выражение
     */
    private ExampleValidation checkExpressionInBracketIsCorrect(final String expression) {
        var signList = List.of('+', '-', '*', '/', '^', '.', '(');

        for (int i = 1; i < expression.length(); i++) {
            var symbol = expression.charAt(i);
            var frontSymbol = expression.charAt(i-1);

            if(symbol == ')' && signList.contains(frontSymbol))
                throw new StringException("Выражение в скобочках некорректно");
        }

        return this;
    }

    /**
     * Проверить, что перед именем функции отсутствуют некорректные символы
     * @return - возвращает true, если перед именем функции отсутствуют некорректные символы
     */
    private ExampleValidation checkSymbolBeforeFunction(final String expression) {
        for (int i = 1; i < expression.length(); i++) {
            var symbol = expression.charAt(i);
            var frontSymbol = expression.charAt(i-1);

            if (isLetter(symbol) &&
                    ((isDigit(frontSymbol) || frontSymbol == '.' || frontSymbol == ')' || frontSymbol == '!')))
                throw new StringException("Некорректный символ перед функцией");
        }

        return this;
    }

    /**
     * Проверить, что в выражении отсутствуют подряд стоящие знаки
     * @return - возвращает true, если в выражении отсутствуют подряд стоящие знаки
     */
    private ExampleValidation checkSeveralSignConsecutive(final String expression) {
        var signList = List.of('+', '-', '*', '/', '^', '.');

        for (int i = 1; i < expression.length(); i++) {
            if (signList.contains(expression.charAt(i)) && signList.contains(expression.charAt(i-1)))
                throw new StringException("Два или более знака подряд");
        }

        return this;
    }

    /**
     * Проверить, что в выражении перед факториалом отсутствуют некорректные символы
     * @return - возвращает true, если в выражении перед факториалом отсутствуют некорректные символы
     */
    private ExampleValidation checkFactorial(final String expression) {
        for (int i = 1; i < expression.length(); i++) {
            var symbol = expression.charAt(i);
            var frontSymbol = expression.charAt(i-1);

            if (symbol == '!' && !(isDigit(frontSymbol) || frontSymbol == ')' || frontSymbol == '!'))
                throw new StringException("Некорректный аргумент фактоиала");
        }

        return this;
    }

    /**
     * Проверить, что выражение начинается с корректного символа
     * @return - возвращает true, если выражение начинается с корректного символа
     */
    private ExampleValidation checkFirstSymbol(final String expression) {
        var signList = List.of('+', '*', '/', '!', '^', '.');

        if (signList.contains(expression.charAt(0)))
            throw new StringException("Некорректный первый символ выражения");

        return this;
    }

    /**
     * Проверить, что выражение заканчивается корректным символом
     * @return - возвращает true, если выражение заканчивается с корректного символа
     */
    private ExampleValidation checkLastSymbol(final String expression) {
        var signList = List.of('+', '-', '*', '/', '^', '.');

        if (signList.contains(expression.charAt(expression.length() - 1)))
            throw new StringException("Некорректный последний символ выражения");

        return this;
    }

    /**
     * Проверить, что в выражении отсутствуют пробелы
     * @return - возвращает true, если в выражении отсутствуют пробелы
     */
    private ExampleValidation checkNoSpace(final String expression) {
        for (int i = 1; i < expression.length(); i++) {
            if (expression.charAt(i) == ' ')
                throw new StringException("Выражение не должно содержать пробелов");
        }

        return this;
    }

    /**
     * Проверить, что в выражении отсутствуют запятые
     * @return - возвращает true, если в выражении отсутствуют запятые
     */
    private ExampleValidation checkNoComma(final String expression) {
        for (int i = 1; i < expression.length(); i++) {
            if (expression.charAt(i) == ',')
                throw new StringException("Выражение не должно содержать запятыхю. Используйте точку");
        }

        return this;
    }

//    /**
//     * Проверить, что в выражении присутствуют числа или константы
//     * @return - возвращает true, если в выражении присутствуют числа или константы
//     */
//    private ExampleValidation checkNoOnlyLetter(final String expression) {
//        for (int i = 0; i < expression.length(); i++) {
//            var symbol = expression.charAt(i);
//
//            if (isDigit(symbol) || (symbol == 'e' && !(isLetter(expression.charAt(i-1)) || isLetter(expression.charAt(i+1)))))
//                return this;
//        }
//
//        throw new StringException("В выражении отсутствуют числа");
//    }

}