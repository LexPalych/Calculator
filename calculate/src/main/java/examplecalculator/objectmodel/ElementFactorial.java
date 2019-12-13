package examplecalculator.objectmodel;

import static examplecalculator.objectmodel.Element.TypeElement.FACTORIAL;
import static examplecalculator.functions.MathActions.getFactorial;

public class ElementFactorial implements Element<Double> {
    private String element;
    private Double value;

    public ElementFactorial(String element) {
        this.element = element;
    }

    public ElementFactorial(String element, Double value) {
        this.element = element;
        this.value = getFactorial(value);
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