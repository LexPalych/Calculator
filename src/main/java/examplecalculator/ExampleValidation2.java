package examplecalculator;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;
import static java.util.stream.Collectors.toList;

/**
 * Класс для валидации примера на корректность записи
 */
final class ExampleValidation2 {
    private List<String> errorList = new ArrayList<>();
    private boolean isCorrect = true;
    private String example;
    private List<Character> expressionCharList;
    private int length;

    public ExampleValidation2(String example) {
        expressionCharList = example
            .chars()
            .mapToObj(c -> (char) c)
            .collect(toList());

        length = expressionCharList.size();
    }

    public boolean isCorrect() {
        checkIncorrectSigns();
        checkBracketAmount();
        checkBracketOrder();
        checkArgumentBracket();
        checkExpressionInBracketIsCorrect();
        checkSymbolBeforeFunction();
        checkSymbolAfterFunction();
        checkSeveralSignConsecutive();
        checkFirstSymbol();
        checkLastSymbol();
        checkNoOnlyLetter();

        if (!isCorrect) {
            for (String error : errorList) {
                System.out.println(error);
            }

            throw new ExampleException("Выражение некорректно");
        }

        return true;

    }

    /**
     * Проверяет выражение на правильность записи
     */
    private void checkIncorrectSigns() {
        List<Character> signList = List.of('(', ')', '+', '-', '*', '/', '!', '^', '.', ' ', ',');

        List<String> errors = expressionCharList
            .stream()
            .filter(symbol -> !(isLetter(symbol) || isDigit(symbol) || signList.contains(symbol)))
            .map(symbol -> "Симол " + symbol + " является некорректным")
            .collect(toList());

        errorList.addAll(errors);

        if (errorList.size() != 0) {
            isCorrect = false;
        }
    }

    /**
     * Проверяет совпадение в количестве открывающий и закрывающих скобочек
     */
    private void checkBracketAmount() {
        long openingBracket = expressionCharList
                .stream()
                .filter(symbol -> symbol.equals('('))
                .count();

        long closingBracket = expressionCharList
                .stream()
                .filter(symbol -> symbol.equals(')'))
                .count();
        
        if (openingBracket != closingBracket)
            addError("Неверное количество скобочек");
    }

    /**
     * Проверяет наличие закрывающей скобочки перед открыающей
     */
    private void checkBracketOrder() {
        int bracketAmount = 0;

        for (int i = 0; i < length; i++) {
            char symbol = expressionCharList.get(i);

            if (symbol == '(') {
                bracketAmount++;

            } else if (symbol == ')') {
                bracketAmount--;
            }

            if (bracketAmount < 0)
                addError("Закрывающая скобочка стоит перед открывающей (позиция " + i + ")");
        }
    }

    /**
     * Проверяет наличие скобочки перед выражением аргумента функции
     */
    private void checkArgumentBracket() {
        for (int i = 1; i < length; i++) {

            if (isDigit(expressionCharList.get(i)) && isLetter(expressionCharList.get(i - 1)))
                addError("Отсутствует скобочка перед аргументом функции на позиции " + i);
        }
    }

    /**
     * Проверяет наличие в скобочках какого-либо выражения
     */
    private void checkExpressionInBracketIsCorrect() {
        List<Character> signList = List.of('+', '-', '*', '/', '^', '.', '(');

        for (int i = 1; i < length; i++) {
            if (expressionCharList.get(i) == ')' && signList.contains(expressionCharList.get(i - 1)))
                addError("Отсутствует выражение перед скобочкой на позиции " + i);
        }
    }

    /**
     * Проверяет наличие перед функцией некорректных символов
     */
    private void checkSymbolBeforeFunction() {
        List<Character> signList = List.of(')', '.', '!');

        for (int i = 1; i < length; i++) {
            char currentSymbol = expressionCharList.get(i);
            char prevSymbol = expressionCharList.get(i - 1);

            if (isLetter(currentSymbol) && ((isDigit(prevSymbol) || signList.contains(prevSymbol))))
                addError("Некорректный символ (" + prevSymbol + ") перед функцией");
        }
    }

    /**
     * Проверяет наличие после имени функции некорректных символов
     */
    private void checkSymbolAfterFunction() {
        List<Character> signList = List.of(')', '.', '!', '+', '-', '*', '/', '^');

        for (int i = 0; i < length - 1; i++) {
            char currentSymbol = expressionCharList.get(i);
            char nextSymbol = expressionCharList.get(i+1);

            if (isLetter(currentSymbol) && signList.contains(nextSymbol))
                addError("Некорректный символ (" + nextSymbol + ") после имени функции");
        }
    }

    /**
     * Проверяет наличие подряд стоящих знаков
     */
    private void checkSeveralSignConsecutive() {
        List<Character> signList = List.of('+', '-', '*', '/', '^', '.');

        for (int i = 0; i < length - 1; i++) {
            char currentSymbol = expressionCharList.get(i);
            char nextSymbol = expressionCharList.get(i + 1);

            if (signList.contains(currentSymbol) && signList.contains(nextSymbol))
                addError("Несколько знаков подряд начиная с позиции " + i);
        }
    }

    /**
     * Проверяет наличие некорректных символов в начале выражения
     */
    private void checkFirstSymbol() {
        List<Character> signList = List.of('+', '*', '/', '!', '^', '.');

        if (signList.contains(expressionCharList.get(0)))
            addError("Некорректный первый символ выражения");
    }

    /**
     * Проверяет наличие некорректных символов в конце выражения
     */
    private void checkLastSymbol() {
        List<Character> signList = List.of('+', '-', '*', '/', '^', '.');

        if (signList.contains(expressionCharList.get(length - 1))) {
            addError("Некорректный последний символ выражения");
        }
    }

    /**
     * Проверяет наличие в выражении чисел
     */
    private void checkNoOnlyLetter() {
        if (expressionCharList.stream().filter(Character::isDigit).count() == 0)
            addError("В выражении отсутствуют числа");
    }

    private void addError(String errorMessage) {
        errorList.add(errorMessage);
        isCorrect = false;
    }
}