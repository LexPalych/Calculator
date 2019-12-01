package examplecalculator.exampleelement;

import static examplecalculator.exampleelement.IElement.TypeElement.NUMBER;

public class ElementNumber implements IElement<Double> {
    private String element;
    private Double value;

    public ElementNumber(String element) {
        this.element = element;
        this.value = Double.parseDouble(element);
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
