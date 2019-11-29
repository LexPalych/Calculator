package calculate.element;

import java.util.List;
import java.util.function.BiFunction;

import static calculate.element.Element.TypeElement.SIGN;
import static calculate.element.Element.getSignList;
import static calculate.functions.MathFunctions.*;
import static java.util.stream.Collectors.toList;

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
        Element element;

        //Формируем порядок, в котором будут выполняться действия:
        //Из общего списка знаков, идущих по порядку приоритета от большего к меньшему,
        //Оставляем только те, которые присутствуют в списке элементов.
        //Нужно для ускорения работы и чтобы избежать лишних проходов по всему списку элементов
        List<BiFunction> prioritySignList = List.of(FACTORIAL, EXPONENTIATION, DIVISION, MULTIPLICATION, SUBTRACTION, ADDITIONAL)
                .stream()
                .filter(sign -> getSignList(elementList).contains(sign))
                .collect(toList());

        //Если получившийся список знаков, выполняемых по порядку, получился не пустой, выполняем расчёт элементов
        //Если пустой, значит в списке элементов лишь одно единственно число, которое и возвращаем
        if (prioritySignList.size() != 0) {
            for (BiFunction function : prioritySignList) {
                i = 0;

                while (i < elementList.size()) {
                    element = elementList.get(i);

                    if (element.getTypeElement() == SIGN && element.getValue() == FACTORIAL) {

                        value = (double) function.apply(elementList.get(i-1).getValue(), null);
                        elementList.get(i-1).setValue(value);

                        elementList.remove(i);

                    } else if (element.getTypeElement() == SIGN && element.getValue() == function) {

                        value = (double) function.apply(elementList.get(i-1).getValue(), elementList.get(i+1).getValue());

                        elementList.get(i-1).setValue(value);

                        elementList.remove(i);
                        elementList.remove(i);

                    } else {
                        i++;
                    }
                }
            }
        }

        return (double) elementList.get(0).getValue();
    }
}