package examplecalculator;

import examplecalculator.exampleelement.ElementNumber;
import examplecalculator.exampleelement.IElement;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import static examplecalculator.ExampleValidation.assertExample;
import static examplecalculator.exampleelement.CalculateElement.calculateElement;
import static examplecalculator.exampleelement.IElement.TypeElement.SIGN;
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
        List<IElement> elementList = new LinkedList<>();
        IElement element;
        int i = 0;

        while (i < subExample.length()) {
            char currentSymbol = subExample.charAt(i);

            //В зависимости от того, какой текущий символ, выбирается функция, которая создаёт элемент примера
            Function<String, IElement> createElementFunction = createElementFunction(currentSymbol);
            element = createElementFunction.apply(subExample.substring(i));
            elementList.add(element);

            //Итератор переносится на индекс символа, стоящего сразу после последнего символа элемента примера
            i += element.getElement().length();
        }

        //Если первый элемент примера (подпримера главного примера) является знаком действия (впереди стоит минус "-"),
        //То на нулевую позицию помещается ноль ("0.0"),
        //Чтобы сохранялся принцип "число-знак-число-...-знак-...-число"
        if (elementList.get(0).getTypeElement() == SIGN) {
            elementList.add(0, new ElementNumber("0.0", 0.0));
        }

        return calculateElement(elementList);
    }
}