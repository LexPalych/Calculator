package examplecalculator.exampleelement;

import static examplecalculator.ExampleCalculator.calculate;
import static examplecalculator.exampleelement.IElement.TypeElement.BRACKET;

public class ElementBracket implements IElement<Double> {
    private String element;
    private Double value;

    public ElementBracket(String element) {
        this.element = element;
        this.value = calculate(element.substring(1, element.length()-1));
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
        return BRACKET;
    }
}
