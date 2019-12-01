package examplecalculator.exampleelement;

import examplecalculator.ExampleException;

import static examplecalculator.exampleelement.Element.TypeElement.NUMBER;
import static examplecalculator.exampleelement.Element.TypeElement.SIGN;

/**
 * Элемент примера: число/знак
 */
public class Element<T> {
    private String element;
    private T value;

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

    void setValue(T value) {
        this.value = value;
    }

    public void setElement(String element) {
        this.element = element;
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
            throw new ExampleException("Неизестный тип элемента" + getValue());
    }

    public enum TypeElement {
        NUMBER,
        SIGN
    }

}


