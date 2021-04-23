package examplecalculator.element;

import static examplecalculator.element.ElementListCreator.getElementList;
import static examplecalculator.function.ActionFunction.ACTION_STACK;

import examplecalculator.function.ActionFunction;
import examplecalculator.objectmodel.Element;
import java.util.List;

public final class ElementCalculator {

    /**
     * Выполняет расчёт примера (подпримера главного примера): Переводит пример в список элементов
     * примера и Выполняет действия между числами примера в соответствии с порядком действия знаков
     *
     * @param subExample - пример (подпример главного примера)
     * @return - возвращает значение примера (подпримера главного примера)
     */
    public static Double getExampleValue(final String subExample) {
        List<Element> elementList = getElementList(subExample);

        //Если получившийся список знаков, выполняемых по порядку, получился не пустой, выполняем расчёт элементов
        //Если пустой, значит в списке элементов лишь одно единственно число, которое и возвращаем
        for (ActionFunction.Actions action : ACTION_STACK) {
            int i = 0;

            while (i < elementList.size()) {
                Element currentElement = elementList.get(i);

                if (currentElement.getValue() == action) {
                    Element leftElement = elementList.get(i - 1);
                    Element rightElement = elementList.get(i + 1);

                    double value = action.getAction().apply((double) leftElement.getValue(), (double) rightElement.getValue());
                    String element = leftElement.getElement()
                        + currentElement.getElement()
                        + rightElement.getElement();

                    elementList.get(i - 1).setValue(value);
                    elementList.get(i - 1).setElement(element);

                    elementList.removeAll(List.of(currentElement, rightElement));

                } else {
                    i++;
                }
            }
        }

        return (double) elementList.get(0).getValue();
    }
}