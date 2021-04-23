package examplecalculator.objectmodel;

public interface Element<T> {

    T getValue();

    void setValue(T value);

    String getElement();

    void setElement(String element);

    TypeElement getTypeElement();

    enum TypeElement {
        SIGN,
        NUMBER,
        FUNCTION,
        BRACKET,
        FACTORIAL
    }
}