package examplecalculator.exampleelement;

import java.util.function.BiFunction;

import static examplecalculator.exampleelement.Element.TypeElement.SIGN;
import static examplecalculator.examplefunctions.MathActions.getMathFunction;

public class ElementSign implements Element<BiFunction> {
    private String element;
    private BiFunction value;

    ElementSign(String element) {
        this.element = element;
        this.value = getMathFunction(element);
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