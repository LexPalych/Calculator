package examplecalculator;

import examplecalculator.exampleelement.Element;
import examplecalculator.exampleelement.ElementFactorial;
import examplecalculator.exampleelement.ElementNumber;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import static examplecalculator.ExampleValidation.assertExample;
import static examplecalculator.exampleelement.Element.TypeElement.SIGN;
import static examplecalculator.exampleelement.ElementCalculator.calculateElement;
import static examplecalculator.exampleelement.ElementCreator.createElementFunction;

public class ExampleCalculator {
    /**
     * Проверяет пример на корректность записи и производит его вычисление
     * @param example - пример
     * @return - возвращает итоговый результат вычислений
     */
    public static double calculateExample(final String example) {
        assertExample(example);

        return calculate(example);
    }

    /**
     * Разбивает пример (подпример главного примера) на элементы:
     * Числа, знаки математических действий, выражения в скобках, функции
     * Значения таких элементов, как выражения в скобках и функции, рекурсивно высчитываются
     * Помещает полученные элементы в список для дальнейшего расчёта значения примера (подпримера главного примера)
     * @param subExample - пример (подпример главного примера)
     * @return - возвращает список элементов примера, состоящий из числовых значений и знаков (лямбда-функций) между ними
     */
    public static Double calculate(final String subExample) {
        List<Element> elementList = new LinkedList<>();
        Element element;
        int i = 0;

        while (i < subExample.length()) {
            char currentSymbol = subExample.charAt(i);

            //В зависимости от того, какой текущий символ, выбирается функция, которая создаёт элемент примера
            Function<String, Element> createElementFunction = createElementFunction(currentSymbol);
            element = createElementFunction.apply(subExample.substring(i));
            elementList.add(element);

            //Итератор переносится на индекс символа, стоящего сразу после последнего символа элемента примера
            i += element.getElement().length();
        }

        return calculateElement(replaceElementList(elementList));
    }

    private static List<Element> replaceElementList(final List<Element> rowElementList) {
        replaceFirstElement(rowElementList);
        replaceFactorialElement(rowElementList);

        return rowElementList;
    }

    /**
     * Проверяет, есть ли в начале списка элемент типа SIGN (знак минус "-")
     * Если есть, то в начало списка помещается ноль ("0")
     * Нужно для сохранения принципа "число-знак-число-...-знак-...-число"
     * @param elementList - "сырой" список элементов
     * @return - исправленный список элементов
     */
    private static List<Element> replaceFirstElement(final List<Element> elementList) {
        if (elementList.get(0).getTypeElement() == SIGN) {
            elementList.add(0, new ElementNumber("0"));
        }

        return elementList;
    }

    /**
     * Проверяет, есть ли в "сыром" списке элементов знак факториала "!"
     * Если есть, заменяет предыдущий элемент типа NUMBER на элемент типа FACTORIAL
     * Знак "!" перемещается к новому созданному элементу и затирается в "сыром" списке элементов
     * Нужно для сохранения принципа "число-знак-число-...-знак-...-число"
     * @param elementList - "сырой" список элементов
     * @return - исправленный список элементов
     */
    private static List<Element> replaceFactorialElement(final List<Element> elementList) {
        int i = 0;

        while (i < elementList.size()) {
            if (elementList.get(i).getElement().equals("!")) {
                elementList.set(i-1, new ElementFactorial(elementList.get(i-1).getElement()));
                elementList.remove(i);

            } else {
                i++;
            }
        }

        return elementList;
    }

}