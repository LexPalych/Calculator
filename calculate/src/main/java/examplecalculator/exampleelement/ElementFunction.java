package examplecalculator.exampleelement;

import static examplecalculator.exampleelement.IElement.TypeElement.NUMBER;

public class ElementFunction implements IElement<Double> {
    private String element;
    private Double value;

    public ElementFunction(String element, Double value) {
        this.element = element;
        this.value = value;
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
        return NUMBER;
    }
}
