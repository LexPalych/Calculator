package examplecalculator;

public class Element {
    private final String element;
    private TypeElement typeElement;
    private double value;

    public Element(String element) {
        this.element = element;
    }

    double getValue() {
        return value;
    }

    void setValue(double value) {
        this.value = value;
    }

    String getElement() {
        return element;
    }

    TypeElement getTypeElement() {
        return typeElement;
    }

    void setTypeElement(TypeElement typeElement) {
        this.typeElement = typeElement;
    }

    enum TypeElement {
        SIGN,
        NUMBER,
        FUNCTION,
        BRACKET,
        FACTORIAL
    }
}