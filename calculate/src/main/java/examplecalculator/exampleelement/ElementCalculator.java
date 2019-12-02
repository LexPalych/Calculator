package examplecalculator.exampleelement;

import java.util.List;
import java.util.function.BiFunction;

import static examplecalculator.examplefunctions.ActionPriority.getActionList;
import static examplecalculator.examplefunctions.MathActions.FACTORIAL;

public class ElementCalculator {
    /**
     * Выполняет расчёт элементов примера:
     * Выполняет действия между числами примера в соответствии с приоритетом (порядком действия) знаков
     * @param elementList - список элементов примера
     * @return - возвращает значение примера
     */
    public static double calculateElement(final List<Element> elementList) {
        Double value;
        Double first;
        Double second;

        List<BiFunction> actionList = getActionList(elementList);

        //Если получившийся список знаков, выполняемых по порядку, получился не пустой, выполняем расчёт элементов
        //Если пустой, значит в списке элементов лишь одно единственно число, которое и возвращаем
        if (actionList.size() != 0) {
            for (BiFunction action : actionList) {
                int i = 0;

                while (i < elementList.size()) {
                    if (elementList.get(i).getValue() == action) {
                        first = (Double) elementList.get(i-1).getValue();

                        if (action == FACTORIAL) {
                            second = 0.0;

                        } else {
                            second = (Double) elementList.get(i+1).getValue();
                            elementList.remove(i);
                        }
                        elementList.remove(i);

                        value = (Double) action.apply(first, second);
                        elementList.get(i-1).setValue(value);

                    } else {
                        i++;
                    }
                }
            }
        }

        return (Double) elementList.get(0).getValue();
    }
}