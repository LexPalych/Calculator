package examplecalculator.exampleelement;

import examplecalculator.ExampleException;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import static examplecalculator.ExampleCalculator.calculate;
import static examplecalculator.exampleelement.ElementCreator.SymbolType.*;
import static examplecalculator.examplefunctions.CalculateFunction.getFunctionValue;
import static examplecalculator.examplefunctions.MathFunctions.getMathFunction;
import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;

/**
 * Класс методов для распознавания элементов примера
 */
public class ElementCreator {

    /**
     * Распознаёт и выцепляет из примера первое число
     */
    private static final Function<String, Element> DIGIT_CREATOR = example -> {
        int lastNumberIndex = 0;
        char symbol;

        while (lastNumberIndex < example.length()) {
            symbol = example.charAt(lastNumberIndex);

            if (isDigit(symbol) || symbol == '.') {
                lastNumberIndex++;

            } else {
                break;
            }
        }

        String stringValue = example.substring(0, lastNumberIndex);
        Double numericValue = Double.parseDouble(stringValue);

        return new Element<>(stringValue, numericValue);
    };

    /**
     * Распознаёт и выцепляет из примера первую функцию
     */
    private static final Function<String, Element> LETTER_CREATOR = example -> {
        int lastFunctionIndex = getClosingBracketIndex(example);

        String stringValue = example.substring(0, lastFunctionIndex+1);
        Double numericValue = getFunctionValue(stringValue);

        return new Element<>(stringValue, numericValue);
    };

    /**
     * Распознаёт и выцепляет из примера выражение, заключённое в скобки
     */
    private static final Function<String, Element> BRACKET_CREATOR = example -> {
        int lastFunctionIndex = getClosingBracketIndex(example);

        String stringValue = example.substring(0, lastFunctionIndex+1);
        Double numericValue = calculate(example.substring(1, lastFunctionIndex));

        return new Element<>(stringValue, numericValue);
    };

    /**
     * Распознаёт и выцепляет из примера знак математического дейстия (!,^, /, *, -, +)
     */
    private static final Function<String, Element> SIGN_CREATOR = example -> {
        String stringValue = example.substring(0, 1);
        BiFunction mathFunctionValue = getMathFunction(stringValue);

        return new Element<>(stringValue, mathFunctionValue);
    };

    /**
     * Определяет функцию для создания элемента примера в зависимости от типа символа примера (знак, число, буква, скобка)
     * @param symbol - текущий символ
     * @return - возвращает функцию создания элемента примера
     */
    public static Function<String, Element> createElementFunction(final char symbol) {
        switch (getSymbolType(symbol)) {
            case SIGN:
                return SIGN_CREATOR;

            case DIGIT:
                return DIGIT_CREATOR;

            case LETTER:
                return LETTER_CREATOR;

            case BRACKET:
                return BRACKET_CREATOR;

            default:
                throw new ExampleException("Отсутствует условие для символа " + symbol);
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
        lastBracketIndex--;

        return lastBracketIndex;
    }

    /**
     * Определяет тип символа (знак, число, буква, скобка)
     * @param symbol - символ
     * @return - возвращает тип символа
     */
    private static SymbolType getSymbolType(final char symbol) {
        List<Character> signList = List.of('+', '-', '*', '/', '^', '!');

        if (signList.contains(symbol)) {
            return SIGN;

        } else if (isDigit(symbol) || symbol == '.') {
            return DIGIT;

        } else if (isLetter(symbol)) {
            return LETTER;

        } else if (symbol == '(') {
            return BRACKET;

        } else {
            throw new ExampleException("Неизестный сивол " + symbol);
        }
    }

    protected enum SymbolType {
        SIGN,
        DIGIT,
        LETTER,
        BRACKET,
    }
}