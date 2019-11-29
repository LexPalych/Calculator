package calculate.element;

import java.util.function.BiFunction;

import static calculate.CalculateExample.calculate;
import static calculate.SymbolType.Symbol.DIGIT;
import static calculate.SymbolType.getSymbolType;
import static calculate.functions.CalculateFunction.getFunctionValue;
import static calculate.functions.MathFunctions.getMathFunction;

/**
 * Класс методов для распознавания элементов примера
 */
public class ElementCreator {
    /**
     * Распознаёт и выцепляет из примера первое число
     * @param subExample - пример
     * @return - возвращает елемент примера (число)
     */
    public static Element<Double> getExampleNumber(final String subExample) {
        int lastNumberIndex = 0;

        while (lastNumberIndex < subExample.length() && getSymbolType(subExample.charAt(lastNumberIndex)) == DIGIT) {
            lastNumberIndex++;
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
