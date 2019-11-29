package calculate.element;

import static calculate.element.Element.TypeElement.NUMBER;
import static calculate.element.Element.TypeElement.SIGN;

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


    public T getValue() {
        return value;
    }

    public String getElement() {
        return element;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setElement(String element) {
        this.element = element;
    }

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


