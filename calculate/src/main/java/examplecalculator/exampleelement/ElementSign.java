package examplecalculator.exampleelement;

import java.util.function.BiFunction;

import static examplecalculator.exampleelement.IElement.TypeElement.SIGN;

public class ElementSign implements IElement<BiFunction> {
    private String element;
    private BiFunction value;

    public ElementSign(String element, BiFunction value) {
        this.element = element;
        this.value = value;
    }

    @Override
    public BiFunction getValue() {
        return value;
    }

    @Override
    public String getElement() {
        return element;
    }

    @Override
    public void setValue(BiFunction value) {
        this.value = value;
    }

    @Override
    public void setElement(String element) {
        this.element = element;
    }

    @Override
    public TypeElement getTypeElement() {
        return SIGN;
    }
}
