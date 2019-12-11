//package examplecalculator.exampleelement;
//
//import java.util.function.BiFunction;
//
//import static examplecalculator.exampleelement.Element.TypeElement.FACTORIAL;
//import static examplecalculator.examplefunctions.MathActions.getMathFunction;
//
//public class ElementFactorial implements Element<BiFunction> {
//    private String element;
//    private BiFunction value;
//
//    ElementFactorial(String element) {
//        this.element = element;
//        this.value = getMathFunction(element);
//    }
//
////    public ElementFactorial(Double elementValue) {
////        this.value = FIND_FACTORIAL.apply(elementValue, null);
////    }
//
//    @Override
//    public BiFunction getValue() {
//        return value;
//    }
//
//    @Override
//    public String getElement() {
//        return element;
//    }
//
//    @Override
//    public void setValue(BiFunction value) {
//        this.value = value;
//    }
//
//    @Override
//    public void setElement(String element) {
//        this.element = element;
//    }
//
//    @Override
//    public TypeElement getTypeElement() {
//        return FACTORIAL;
//    }
//}