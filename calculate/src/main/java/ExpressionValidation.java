import java.util.List;
import java.util.logging.Logger;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;

public class ExpressionValidation {
    private String expression;
    private static Logger log = Logger.getLogger(ExpressionValidation.class.getName());

    private char getChar(int index) {
        return expression.charAt(index);
    }
    /**
     * Проверяет выражение на правильность записи
     * @return - возвращает true, если ошибок нет
     */
    public boolean checkExpression(final String expression) {
        this.expression = expression;

        checkIncorrectSigns()
                .checkBracketAmount()
                .checkBracketOrder()
                .checkArgumentBracket()
                .checkExpressionInBracketIsCorrect()
                .checkSymbolBeforeFunction()
                .checkSeveralSignConsecutive()
                .checkFactorial()
                .checkFirstSymbol()
                .checkLastSymbol()
                .checkNoSpace()
                .checkNoComma();
//                .checkNoOnlyLetter();


        return true;
    }

    /**
     * Проверяет, что нет некорректных символов
     * @return - возвращает true, если в выражении нет некорректных символов
     */
    private ExpressionValidation checkIncorrectSigns() {
        var signList = List.of('(', ')', '+', '-', '*', '/', '!', '^', '.');

        for (int i = 0; i < expression.length(); i++) {
            var symbol = getChar(i);

            if (!(isLetter(symbol) || isDigit(symbol) || signList.contains(symbol)))
                throw new StringException("Введён некорректный символ");
        }

        return this;
    }

    /**
     * Проверяет, что количество открывающих скобочек равно количеству закрывающих
     * @return - возвращает true, если в выражении количество открывающих скобочек равно количеству закрывающих
     */
    private ExpressionValidation checkBracketAmount() {
        var bracket = 0;

        for (int i = 0; i < expression.length(); i++) {
            if (getChar(i) == '(')
                bracket++;

            else if (getChar(i) == ')') {
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
    private ExpressionValidation checkBracketOrder() {
        var bracket = 0;

        for (int i = 0; i < expression.length(); i++) {

            if (getChar(i) == '(')
                bracket++;

            else if (getChar(i) == ')') {
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
    private ExpressionValidation checkArgumentBracket() {
        for (int i = 1; i < expression.length(); i++) {
            if(isDigit(getChar(i)) && isLetter(getChar(i-1)))
                throw new StringException("Отсутствует открывающая скобочка перед аргументом");
        }

        return this;
    }

    /**
     * Проверить, что в скобочках присутствует выражение
     * @return - возвращает true, если в скобочках присутствует выражение
     */
    private ExpressionValidation checkExpressionInBracketIsCorrect() {
        var signList = List.of('+', '-', '*', '/', '^', '.', '(');

        for (int i = 1; i < expression.length(); i++) {
            var symbol = getChar(i);
            var frontSymbol = getChar(i-1);

            if(symbol == ')' && signList.contains(frontSymbol))
                throw new StringException("Выражение в скобочках некорректно");
        }

        return this;
    }

    /**
     * Проверить, что перед именем функции отсутствуют некорректные символы
     * @return - возвращает true, если перед именем функции отсутствуют некорректные символы
     */
    private ExpressionValidation checkSymbolBeforeFunction() {
        for (int i = 1; i < expression.length(); i++) {
            var symbol = getChar(i);
            var frontSymbol = getChar(i-1);

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
    private ExpressionValidation checkSeveralSignConsecutive() {
        var signList = List.of('+', '-', '*', '/', '^', '.');

        for (int i = 1; i < expression.length(); i++) {
            if (signList.contains(getChar(i)) && signList.contains(getChar(i-1)))
                throw new StringException("Два или более знака подряд");
        }

        return this;
    }

    /**
     * Проверить, что в выражении перед факториалом отсутствуют некорректные символы
     * @return - возвращает true, если в выражении перед факториалом отсутствуют некорректные символы
     */
    private ExpressionValidation checkFactorial() {
        for (int i = 1; i < expression.length(); i++) {
            var symbol = getChar(i);
            var frontSymbol = getChar(i-1);

            if (symbol == '!' && !(isDigit(frontSymbol) || frontSymbol == ')' || frontSymbol == '!'))
                throw new StringException("Некорректный аргумент фактоиала");
        }

        return this;
    }

    /**
     * Проверить, что выражение начинается с корректного символа
     * @return - возвращает true, если выражение начинается с корректного символа
     */
    private ExpressionValidation checkFirstSymbol() {
        var signList = List.of('+', '*', '/', '!', '^', '.');

        if (signList.contains(getChar(0)))
            throw new StringException("Некорректный первый символ выражения");

        return this;
    }

    /**
     * Проверить, что выражение заканчивается корректным символом
     * @return - возвращает true, если выражение заканчивается с корректного символа
     */
    private ExpressionValidation checkLastSymbol() {
        var signList = List.of('+', '-', '*', '/', '^', '.');

        if (signList.contains(getChar(expression.length() - 1)))
            throw new StringException("Некорректный последний символ выражения");

        return this;
    }

    /**
     * Проверить, что в выражении отсутствуют пробелы
     * @return - возвращает true, если в выражении отсутствуют пробелы
     */
    private ExpressionValidation checkNoSpace() {
        for (int i = 1; i < expression.length(); i++) {
            if (getChar(i) == ' ')
                throw new StringException("Выражение не должно содержать пробелов");
        }

        return this;
    }

    /**
     * Проверить, что в выражении отсутствуют запятые
     * @return - возвращает true, если в выражении отсутствуют запятые
     */
    private ExpressionValidation checkNoComma() {
        for (int i = 1; i < expression.length(); i++) {
            if (getChar(i) == ',')
                throw new StringException("Выражение не должно содержать запятыхю. Используйте точку");
        }

        return this;
    }

//    /**
//     * Проверить, что в выражении присутствуют числа или константы
//     * @return - возвращает true, если в выражении присутствуют числа или константы
//     */
//    private ExpressionValidation checkNoOnlyLetter() {
//        for (int i = 0; i < expression.length(); i++) {
//            var symbol = getChar(i);
//
//            if (isDigit(symbol) || (symbol == 'e' && !(isLetter(getChar(i-1)) || isLetter(getChar(i+1)))))
//                return this;
//        }
//
//        throw new StringException("В выражении отсутствуют числа");
//    }

}