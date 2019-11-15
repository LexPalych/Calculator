package calculate;

import calculate.element.Element;

import java.util.LinkedList;
import java.util.List;

import static calculate.ExampleValidation.checkExample;
import static calculate.SymbolType.getSymbolType;
import static calculate.element.CalculateElement.calculateElement;
import static calculate.element.Element.TypeElement.NUMBER;
import static calculate.element.Element.TypeElement.SIGN;
import static calculate.element.ElementCreator.*;

public class CalculateExample {
    /**
     * Проверяет пример на корректность записи и производит его вычисление
     * @param example - пример
     * @return - возвращает итоговый результат вычислений
     */
    public static double calculateExample(final String example) {
        checkExample(example);

        return calculate(example);
    }

    /**
     * Разбивает пример на элементы (числа/знаки)
     * В итоге получается список чисел в примере (все функции и скобочки выщитываются рекурсивным запуском текущего метода)
     * И список знаков между ними
     * @param subExample - пример (подпример главного примера)
     * @return - возвращает результат вычисления примера (подпримера главного примера)
     */
    public static Double calculate(final String subExample) {
        List<Double> numberList = new LinkedList<>();
        List<Character> signList = new LinkedList<>();
        Element element = new Element();

        List<Element> elementList = new LinkedList<>();

        int i = 0;

        // Т.к. знаков между числами всегда на один меньше, чем чисел,
        // а для корректной работы списки должны быть одной длины,
        // нулевому знаку присваивается значение null
        signList.add(null);

        while (i < subExample.length()) {
            char symbol = subExample.charAt(i);
            SymbolType.Symbol symbolType = getSymbolType(symbol);

            switch (symbolType) {
                case DIGIT:
                    element = getExampleNumber(subExample.substring(i));
                    break;

                case LETTER:
                    element = getExampleFunction(subExample.substring(i));
                    break;

                case BRACKET:
                    element = getExampleBracket(subExample.substring(i));
                    break;

                case SIGN:
                    element = getExampleSign(subExample.substring(i));
                    break;
            }

//            numberList.add(element.getNumber());
            elementList.add(element);
            i += element.getLength();

        }

        if (elementList.get(0).getTypeElement() == SIGN) {
            element.setValueElement(0.0);
            element.setTypeElement(NUMBER);

            elementList.set(0, element);
        }

//        return calculateElement(numberList, signList);
        return calculateElement(elementList);
    }
}