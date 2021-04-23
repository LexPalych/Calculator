package examplecalculator.objectmodel;

import static examplecalculator.element.ElementCalculator.getExampleValue;
import static examplecalculator.objectmodel.Element.TypeElement.BRACKET;

public final class BracketElement implements Element<Double> {

    private String element;
    private Double value;

    public BracketElement(String element) {
        this.element = element;
        this.value = getExampleValue(element.substring(1, element.length() - 1));
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String getElement() {
        return element;
    }

    @Override
    public void setElement(String element) {
        this.element = element;
    }

    @Override
    public TypeElement getTypeElement() {
        return BRACKET;
    }
}
