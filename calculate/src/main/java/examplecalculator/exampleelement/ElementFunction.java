package examplecalculator.exampleelement;

import static examplecalculator.exampleelement.Element.TypeElement.FUNCTION;
import static examplecalculator.examplefunctions.CalculateFunction.getFunctionValue;

public class ElementFunction implements Element<Double> {
    private String element;
    private Double value;

    public ElementFunction(String element) {
        this.element = element;
        this.value = getFunctionValue(element);
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
        return FUNCTION;
    }
}
