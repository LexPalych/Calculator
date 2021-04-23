package examplecalculator.objectmodel;

import static examplecalculator.function.ActionFunction.getMathAction;
import static examplecalculator.objectmodel.Element.TypeElement.SIGN;

import examplecalculator.function.ActionFunction;

public final class SignElement implements Element<ActionFunction.Actions> {

    private String element;
    private ActionFunction.Actions value;

    public SignElement(String element) {
        this.element = element;
        this.value = getMathAction(element);
    }

    @Override
    public ActionFunction.Actions getValue() {
        return value;
    }

    @Override
    public void setValue(ActionFunction.Actions value) {
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
        return SIGN;
    }
}