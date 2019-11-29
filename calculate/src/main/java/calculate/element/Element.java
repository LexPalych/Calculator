package calculate.element;

import java.util.List;
import java.util.function.BiFunction;

import static calculate.element.Element.TypeElement.NUMBER;
import static calculate.element.Element.TypeElement.SIGN;
import static java.util.stream.Collectors.toList;

/**
 * Элемент примера: число/знак
 */
public class Element<T> {
    private String element;
    private T value;

    public Element() {

    }

    public Element(String element, T value) {
        this.element = element;
        this.value = value;
    }


    T getValue() {
        return value;
    }

    public String getElement() {
        return element;
    }

    void setValue(T value) {
        this.value = value;
    }

    public void setElement(String element) {
        this.element = element;
    }

    /**
     * Отфильтровывает из списка элементов только знаки
     * @param elementList - список элементов
     * @return - возвращает список знаков в виде лямбд
     */
    static List<BiFunction> getSignList(final List<Element> elementList) {
        return elementList
                .stream()
                .filter(elem -> elem.getTypeElement() == SIGN)
                .map(elem -> (BiFunction) elem.getValue())
                .collect(toList());
    }

    /**
     * Возвращает тип элемента
     */
    public TypeElement getTypeElement() {
        if (getValue().getClass() == Double.class) {
            return NUMBER;

        } else if (getValue().getClass().getSimpleName().contains("MathFunctions$$Lambda")) {
            return SIGN;

        } else
            throw new SecurityException("Неизестный тип элемента" + getValue());
    }

    public enum TypeElement {
        NUMBER,
        SIGN
    }

}


