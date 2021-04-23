package examplecalculator.objectmodel;

import static examplecalculator.function.MathFunction.getFunctionValue;
import static examplecalculator.objectmodel.Element.TypeElement.FUNCTION;

public final class FunctionElement implements Element<Double> {

    private String element;
    private Double value;

    public FunctionElement(String element) {
        this.element = element;
        this.value = getFunctionValue(element);
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
        return FUNCTION;
    }
}