package examplecalculator.examplefunctions;

import examplecalculator.exampleelement.Element;

import java.util.List;
import java.util.function.BiFunction;

import static examplecalculator.exampleelement.Element.TypeElement.SIGN;
import static examplecalculator.examplefunctions.MathActions.*;
import static java.util.stream.Collectors.toList;

/**
 * Класс, содержащий информацио о приоритете выполняемых математических действий
 */
public class ActionPriority {
    private static final List<BiFunction> PRIORITY_LIST =  List.of(EXPONENTIATION, DIVISION, MULTIPLICATION, SUBTRACTION, ADDITIONAL);

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
    public static List<BiFunction> getActionList(final List<Element> elementList) {
        List<BiFunction> signList = getSignList(elementList);

        return PRIORITY_LIST
                .stream()
                .filter(signList::contains)
                .collect(toList());
    }
}