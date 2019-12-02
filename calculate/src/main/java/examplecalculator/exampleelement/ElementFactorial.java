package examplecalculator.exampleelement;

import static examplecalculator.exampleelement.Element.TypeElement.FACTORIAL;
import static examplecalculator.examplefunctions.MathActions.getFactorial;

public class ElementFactorial implements Element<Double> {
    private String element;
    private Double value;

    public ElementFactorial(String element) {
        this.element = element + "!";
        this.value = getFactorial(Double.parseDouble(element));
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