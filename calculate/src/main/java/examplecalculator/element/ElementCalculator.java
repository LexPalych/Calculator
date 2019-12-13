package examplecalculator.element;

import examplecalculator.objectmodel.Element;

import java.util.List;
import java.util.function.BiFunction;

import static examplecalculator.action.ActionOrder.getActionOrderList;

class ElementCalculator {
    /**
     * Выполняет расчёт элементов примера:
     * Выполняет действия между числами примера в соответствии с приоритетом (порядком действия) знаков
     * @param elementList - список элементов примера
     * @return - возвращает значение примера
     */
    static double calculateElement(final List<Element> elementList) {
        Double value;
        List<BiFunction> actionOrderList = getActionOrderList(elementList);

        //Если получившийся список знаков, выполняемых по порядку, получился не пустой, выполняем расчёт элементов
        //Если пустой, значит в списке элементов лишь одно единственно число, которое и возвращаем
        if (actionOrderList.size() != 0) {
            for (BiFunction action : actionOrderList) {
                int i = 0;

                while (i < elementList.size()) {
                    if (elementList.get(i).getValue() == action) {
                        value = (Double) action.apply(elementList.get(i-1).getValue(), elementList.get(i+1).getValue());
                        elementList.get(i-1).setValue(value);

                        elementList.remove(i);
                        elementList.remove(i);

                    } else {
                        i++;
                    }
                }
            }
        }

        return (Double) elementList.get(0).getValue();
    }
}