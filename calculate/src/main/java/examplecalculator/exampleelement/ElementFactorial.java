package examplecalculator.exampleelement;

import static examplecalculator.exampleelement.Element.TypeElement.FACTORIAL;
import static examplecalculator.examplefunctions.MathActions.FIND_FACTORIAL;

public class ElementFactorial implements Element<Double> {
    private String element;
    private Double value;

    ElementFactorial(String element) {
        this.element = element;
    }

    public ElementFactorial(Double elementValue) {
        this.value = FIND_FACTORIAL.apply(elementValue, null);
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public String getElement() {
        return element;
    }

    @Override
    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public void setElement(String element) {
        this.element = element;
    }

    @Override
    public TypeElement getTypeElement() {
        return FACTORIAL;
    }
}