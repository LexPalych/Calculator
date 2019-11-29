package calculate.element;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import static calculate.CalculateExample.calculate;
import static calculate.functions.CalculateFunction.getFunctionValue;
import static calculate.functions.MathFunctions.getMathFunction;
import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;

/**
 * Класс методов для распознавания элементов примера
 */
public class ElementCreator {
    public static final Function<String, Element> DIGIT_FUNCTION = string -> getExampleNumber(string);
    public static final Function<String, Element> LETTER_FUNCTION = string -> getExampleFunction(string);
    public static final Function<String, Element> BRACKET_FUNCTION = string -> getExampleBracket(string);
    public static final Function<String, Element> SIGN_FUNCTION = string -> getExampleSign(string);

    public static Function<String, Element> getCreateElementFunction(final char symbol) {
        List<Character> signList = List.of('+', '-', '*', '/', '^', '!');

        if (signList.contains(symbol)) {
            return SIGN_FUNCTION;

        } else if (isDigit(symbol) || symbol == '.') {
            return DIGIT_FUNCTION;

        } else if (isLetter(symbol)) {
            return LETTER_FUNCTION;

        } else if (symbol == '(') {
            return BRACKET_FUNCTION;

        } else {
            throw new SecurityException("Неизестный символ " + symbol);
        }
    }

    /**
     * Распознаёт и выцепляет из примера первое число
     * @param subExample - пример
     * @return - возвращает елемент примера (число)
     */
    public static Element<Double> getExampleNumber(final String subExample) {
        int lastNumberIndex = 0;
        char symbol;

        while (lastNumberIndex < subExample.length()) {
            symbol = subExample.charAt(lastNumberIndex);

            if (isDigit(symbol) || symbol == '.') {
                lastNumberIndex++;

            } else {
                break;
            }

        }

        String stringValue = subExample.substring(0, lastNumberIndex);
        Double numericValue = Double.parseDouble(stringValue);

        return new Element<>(stringValue, numericValue);
    }

    /**
     * Распознаёт и выцепляет из примера первую функцию
     * @param subExample - пример
     * @return - возвращает елемент примера (число)
     */
    public static Element<Double> getExampleFunction(final String subExample) {
        int lastFunctionIndex = getClosingBracketIndex(subExample);

        String stringValue = subExample.substring(0, lastFunctionIndex+1);
        Double numericValue = getFunctionValue(stringValue);

        return new Element<>(stringValue, numericValue);
    }

    /**
     * Распознаёт и выцепляет из примера выражение, заключённое в скобки
     * @param subExample - пример
     * @return - возвращает елемент примера (число)
     */
    public static Element<Double> getExampleBracket(final String subExample) {
        int lastFunctionIndex = getClosingBracketIndex(subExample);

        String stringValue = subExample.substring(0, lastFunctionIndex+1);
        Double numericValue = calculate(subExample.substring(1, lastFunctionIndex));

        return new Element<>(stringValue, numericValue);
    }

    /**
     * Распознаёт и выцепляет из примера знак математического дейстия (!,^, /, *, -, +)
     * @param subExample - пример
     * @return - возвращает елемент примера (знак)
     */
    public static Element<BiFunction> getExampleSign(final String subExample) {
        String stringValue = subExample.substring(0, 1);
        BiFunction mathFunctionValue = getMathFunction(stringValue);

        return new Element<>(stringValue, mathFunctionValue);
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
}
