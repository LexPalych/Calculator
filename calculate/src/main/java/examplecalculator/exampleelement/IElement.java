package examplecalculator.exampleelement;

public interface IElement<T> {
    T getValue();

    String getElement();

    void setValue(T value);

    void setElement(String element);

    TypeElement getTypeElement();

    enum TypeElement {
        NUMBER,
        SIGN
    }
}
