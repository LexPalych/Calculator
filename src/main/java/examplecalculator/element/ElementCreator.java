package examplecalculator.element;

import static examplecalculator.objectmodel.Element.TypeElement.BRACKET;
import static examplecalculator.objectmodel.Element.TypeElement.FACTORIAL;
import static examplecalculator.objectmodel.Element.TypeElement.FUNCTION;
import static examplecalculator.objectmodel.Element.TypeElement.NUMBER;
import static examplecalculator.objectmodel.Element.TypeElement.SIGN;
import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;

import examplecalculator.ExampleException;
import examplecalculator.objectmodel.BracketElement;
import examplecalculator.objectmodel.Element;
import examplecalculator.objectmodel.FactorialElement;
import examplecalculator.objectmodel.FunctionElement;
import examplecalculator.objectmodel.NumberElement;
import examplecalculator.objectmodel.SignElement;
import java.util.List;
import java.util.function.Function;

/**
 * Класс методов для распознавания элементов примера
 */
final class ElementCreator {

    /**
     * Определяет функцию для создания элемента примера в зависимости от типа символа примера (знак,
     * число, буква, скобка)
     *
     * @param symbol - текущий символ
     * @return - возвращает функцию создания элемента примера
     */
    static Function<String, Element> getElementCreator(final char symbol) {
        return switch (getTypeElement(symbol)) {
            case SIGN -> example -> new SignElement(example.substring(0, 1));
            case FACTORIAL -> example -> new FactorialElement(example.substring(0, 1));
            case FUNCTION -> example -> new FunctionElement(example.substring(0, getClosingBracketIndex(example) + 1));
            case BRACKET -> example -> new BracketElement(example.substring(0, getClosingBracketIndex(example) + 1));
            case NUMBER -> example -> {
                int lastNumeralIndex = 0;

                while (lastNumeralIndex < example.length()
                    && getTypeElement(example.charAt(lastNumeralIndex)) == NUMBER) {
                    lastNumeralIndex++;
                }

                return new NumberElement(example.substring(0, lastNumeralIndex));
            };
        };
    }

    /**
     * Определяет тип символа (знак, число, буква, скобка)
     *
     * @param symbol - символ
     * @return - возвращает тип символа
     */
    private static Element.TypeElement getTypeElement(final char symbol) {
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
     *
     * @param subExample - пример
     * @return - возвращает индекс закрывающей скобочки
     */
    private static int getClosingBracketIndex(final String subExample) {
        int lastBracketIndex = 0;
        int bracketAmount = 0;
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