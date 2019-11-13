package calculate.element;

import static calculate.CalculateExample.calculate;
import static calculate.SymbolType.Symbol.DIGIT;
import static calculate.SymbolType.getSymbolType;
import static calculate.functions.CalculateFunction.getFunctionValue;

/**
 * Класс методов для распознавания элементов примера
 */
public class ElementCreator {
    /**
     * Распознаёт и выцепляет из примера первое число
     * @param subExample - пример
     * @return - возвращает елемент примера (число)
     */
    public static Element getExampleNumber(final String subExample) {
        int lastNumberIndex = 0;
        String number;
        Element element = new Element();

        while (lastNumberIndex < subExample.length() && getSymbolType(subExample.charAt(lastNumberIndex)) == DIGIT) {
            lastNumberIndex++;
        }

        number = subExample.substring(0, lastNumberIndex);
        element.setNumber(Double.parseDouble(number));
        element.setLength(number.length());

        return element;
    }

    /**
     * Распознаёт и выцепляет из примера первую функцию
     * @param subExample - пример
     * @return - возвращает елемент примера (число)
     */
    public static Element getExampleFunction(final String subExample) {
        Element element = new Element();

        int lastFunctionIndex = getClosingBracketIndex(subExample);
        String exampleFunction = subExample.substring(0, lastFunctionIndex+1);
        double functionValue = getFunctionValue(exampleFunction);

        element.setNumber(functionValue);
        element.setLength(exampleFunction.length());

        return element;
    }

    /**
     * Распознаёт и выцепляет из примера выражение, заключённое во внешние скобочки
     * @param subExample - пример
     * @return - возвращает елемент примера (число)
     */
    public static Element getExampleBracket(final String subExample) {
        Element element = new Element();

        int lastFunctionIndex = getClosingBracketIndex(subExample);
        String exampleBracket = subExample.substring(1, lastFunctionIndex);
        double bracketValue = calculate(exampleBracket);

        element.setNumber(bracketValue);
        element.setLength(exampleBracket.length() + 2);

        return element;
    }

    /**
     * Находит индекс скобочки, закрыающей перую открывающую скобочку
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
