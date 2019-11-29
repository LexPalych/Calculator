package calculate.element;

import java.util.List;
import java.util.function.BiFunction;

import static calculate.functions.ActionPriority.getPriorityList;
import static calculate.functions.MathFunctions.FACTORIAL;

public class CalculateElement {
    /**
     * Выполняет расчёт элементов примера:
     * Выполняет действия между числами примера в соответствии с приоритетом (порядком действия) знаков
     * @param elementList - список элементов примера
     * @return - возвращает значение примера
     */
    public static double calculateElement(final List<Element> elementList) {
        double value;
        int i;
        Double first;
        Double second;

        List<BiFunction> prioritySignList = getPriorityList(elementList);

        //Если получившийся список знаков, выполняемых по порядку, получился не пустой, выполняем расчёт элементов
        //Если пустой, значит в списке элементов лишь одно единственно число, которое и возвращаем
        if (prioritySignList.size() != 0) {
            for (BiFunction function : prioritySignList) {
                i = 0;

                while (i < elementList.size()) {
                    if (elementList.get(i).getValue() == function) {
                        first = (Double) elementList.get(i-1).getValue();

                        if (function == FACTORIAL) {
                            second = 0.0;

                        } else {
                            second = (Double) elementList.get(i+1).getValue();
                            elementList.remove(i);
                        }
                        elementList.remove(i);

                        value = (double) function.apply(first, second);
                        elementList.get(i-1).setValue(value);

                    } else {
                        i++;
                    }
                }
            }
        }

        return (double) elementList.get(0).getValue();
    }
}