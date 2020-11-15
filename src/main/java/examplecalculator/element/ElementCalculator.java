package examplecalculator.element;

import examplecalculator.objectmodel.Element;

import java.util.List;
import java.util.Stack;
import java.util.function.BiFunction;

import static examplecalculator.element.ElementListCreator.getElementList;
import static examplecalculator.function.ActionFunction.*;

public final class ElementCalculator {
    /**
     * Выполняет расчёт примера (подпримера главного примера):
     * Переводит пример в список элементов примера и
     * Выполняет действия между числами примера в соответствии с порядком действия знаков
     * @param subExample - пример (подпример главного примера)
     * @return - возвращает значение примера (подпримера главного примера)
     */
    public static Double getExampleValue(final String subExample) {
        List<Element> elementList = getElementList(subExample);

        Stack<BiFunction<Double, Double, Double>> actionStack = new Stack<>();
        actionStack.push(EXPONENTIATION);
        actionStack.push(DIVISION);
        actionStack.push(MULTIPLICATION);
        actionStack.push(SUBTRACTION);
        actionStack.push(ADDITIONAL);

        //Если получившийся список знаков, выполняемых по порядку, получился не пустой, выполняем расчёт элементов
        //Если пустой, значит в списке элементов лишь одно единственно число, которое и возвращаем
        for (BiFunction action : actionStack) {
            int i = 0;

            while (i < elementList.size()) {
                if (elementList.get(i).getValue() == action) {
                    Element leftElement = elementList.get(i - 1);
                    Element rightElement = elementList.get(i + 1);

                    double value = (double) action.apply(leftElement.getValue(), rightElement.getValue());
                    elementList.get(i - 1).setValue(value);
                    elementList.removeAll(List.of(rightElement, elementList.get(i)));

                } else
                    i++;
            }
        }

        return (double) elementList.get(0).getValue();
    }
}