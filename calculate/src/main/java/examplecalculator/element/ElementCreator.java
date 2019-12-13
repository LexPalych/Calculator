package examplecalculator.element;

import examplecalculator.ExampleException;
import examplecalculator.objectmodel.*;

import java.util.List;
import java.util.function.Function;

import static examplecalculator.objectmodel.Element.TypeElement.*;
import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;

/**
 * Класс методов для распознавания элементов примера
 */
class ElementCreator {

    /**
     * Распознаёт и выцепляет из примера первое число
     */
    private static final Function<String, Element> NUMBER_CREATOR = example -> {
        int i = 0;

        while (i < example.length() && getSymbolType(example.charAt(i)) == NUMBER) {
            i++;
        }

        return new ElementNumber(example.substring(0, i));
    };

    /**
     * Распознаёт и выцепляет из примера первую функцию
     */
    private static final Function<String, Element> FUNCTION_CREATOR = example ->
            new ElementFunction(example.substring(0, getClosingBracketIndex(example)+1));

    /**
     * Распознаёт и выцепляет из примера выражение, заключённое в скобки
     */
    private static final Function<String, Element> BRACKET_CREATOR = example ->
            new ElementBracket(example.substring(0, getClosingBracketIndex(example)+1));

    /**
     * Распознаёт и выцепляет из примера знак математического дейстия (^, /, *, -, +)
     */
    private static final Function<String, Element> SIGN_CREATOR = example ->
            new ElementSign(example.substring(0, 1));

    /**
     * Распознаёт и выцепляет из примера знак факторила (!)
     */
    private static final Function<String, Element> FACTORIAL_CREATOR = example ->
            new ElementFactorial(example.substring(0, 1));

    /**
     * Определяет функцию для создания элемента примера в зависимости от типа символа примера (знак, число, буква, скобка)
     * @param symbol - текущий символ
     * @return - возвращает функцию создания элемента примера
     */
    static Function<String, Element> createElementFunction(final char symbol) {
        switch (getSymbolType(symbol)) {
            case SIGN:
                return SIGN_CREATOR;

            case NUMBER:
                return NUMBER_CREATOR;

            case FUNCTION:
                return FUNCTION_CREATOR;

            case BRACKET:
                return BRACKET_CREATOR;

            case FACTORIAL:
                return FACTORIAL_CREATOR;

            default:
                throw new ExampleException("Отсутствует условие для символа " + symbol);
        }
    }

    /**
     * Определяет тип символа (знак, число, буква, скобка)
     * @param symbol - символ
     * @return - возвращает тип символа
     */
    private static Element.TypeElement getSymbolType(final char symbol) {
        List<Character> signList = List.of('+', '-', '*', '/', '^');

        if (signList.contains(symbol)) {
            return SIGN;

        } else if (isDigit(symbol) || symbol == '.') {
            return NUMBER;

        } else if (isLetter(symbol)) {
            return FUNCTION;

        } else if (symbol == '(') {
            return BRACKET;

        } else if (symbol == '!') {
            return FACTORIAL;

        } else {
            throw new ExampleException("Неизестный сивол " + symbol);
        }
    }

    /**
     * Находит индекс скобки, закрывающей перую открывающую скобку
     * @param subExample - пример
     * @return - возвращает индекс закрывающей скобочки
     */
    private static int getClosingBracketIndex(final String subExample) {
        int bracketAmount = 0;
        int lastBracketIndex = 0;
        char currentChar;

        do {
            currentChar = subExample.charAt(lastBracketIndex++);

            if (currentChar == '(') {
                bracketAmount++;

            } else if (currentChar == ')') {
                bracketAmount--;
            }

        } while (!(bracketAmount == 0 && currentChar == ')'));

        return --lastBracketIndex;
    }
}