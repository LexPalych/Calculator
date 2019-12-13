package examplecalculator.exampleelement;

import static examplecalculator.ExampleElementListCreator.createElementList;
import static examplecalculator.exampleelement.Element.TypeElement.BRACKET;

public class ElementBracket implements Element<Double> {
    private String element;
    private Double value;

    ElementBracket(String element) {
        this.element = element;
        this.value = createElementList(element.substring(1, element.length()-1));
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
