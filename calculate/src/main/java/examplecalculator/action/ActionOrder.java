package examplecalculator.action;

import examplecalculator.objectmodel.Element;

import java.util.List;
import java.util.function.BiFunction;

import static examplecalculator.objectmodel.Element.TypeElement.SIGN;
import static examplecalculator.action.ActionFunction.*;
import static java.util.stream.Collectors.toList;

/**
 * Класс, содержащий информацио о порядке выполнения математических действий
 */
public class ActionOrder {
    private static final List<BiFunction> PRIORITY_ORDER =  List.of(EXPONENTIATION, DIVISION, MULTIPLICATION, SUBTRACTION, ADDITIONAL);

    /**
     * Отфильтровывает из списка элементов только знаки
     * @param elementList - список элементов
     * @return - возвращает список знаков в виде лямбд
     */
    private static List<BiFunction> getSignList(final List<Element> elementList) {
        return elementList
                .stream()
                .filter(element -> element.getTypeElement() == SIGN)
                .map(element -> (BiFunction) element.getValue())
                .collect(toList());
    }

    /**
     * Формируем порядок, в котором будут выполняться действия:
     * Из общего списка знаков, идущих по порядку приоритета от большего к меньшему,
     * Оставляем только те, которые присутствуют в списке элементов.
     * Нужно для ускорения работы и чтобы избежать лишних проходов по всему списку элементов
     */
    public static List<BiFunction> getActionOrderList(final List<Element> elementList) {
        List<BiFunction> signList = getSignList(elementList);

        return PRIORITY_ORDER
                .stream()
                .filter(signList::contains)
                .collect(toList());
    }
}