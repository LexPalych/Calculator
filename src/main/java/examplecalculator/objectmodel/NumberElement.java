package examplecalculator.objectmodel;

import static examplecalculator.objectmodel.Element.TypeElement.NUMBER;

public final class NumberElement implements Element<Double> {
    private String element;
    private Double value;

    public NumberElement(String element) {
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