package calculate;

import calculate.element.Element;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import static calculate.ExampleValidation.checkExample;
import static calculate.element.CalculateElement.calculateElement;
import static calculate.element.Element.TypeElement.SIGN;
import static calculate.element.ElementCreator.createElementFunction;

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
     * Разбивает пример (подпример главного примера) на список элементов:
     * Числа, знаки математических действий, выражения в скобках, функции
     * Значения таких элементов, как выражения в скобках и функции, рекурсивно высчитываются
     * @param subExample - пример (подпример главного примера)
     * @return - возвращает список элементов примера, состоящий из числовых значений и знаков (лямбда-функций) между ними
     */
    public static Double calculate(final String subExample) {
        Element element;

        List<Element> elementList = new LinkedList<>();

        int i = 0;

        while (i < subExample.length()) {
            char symbol = subExample.charAt(i);

            Function<String, Element> createElementFunction = createElementFunction(symbol);
            element = createElementFunction.apply(subExample.substring(i));

            elementList.add(element);
            i += element.getElement().length();
        }

        //Если первый элемент примера (подпримера главного примера) является знаком действия (впереди стоит минус "-"),
        //То на нулевую позицию помещается ноль ("0.0"),
        //Чтобы сохранялся принцип "число-знак-число-знак-число"
        if (elementList.get(0).getTypeElement() == SIGN) {
            elementList.add(0, new Element<>("0.0", 0.0));
        }

        return calculateElement(elementList);
    }
}