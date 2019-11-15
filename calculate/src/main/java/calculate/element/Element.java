package calculate.element;

/**
 * Элемент примера: число/знак
 */
public class Element {
    private String element;
    private TypeElement typeElement;
    private Double valueElement;

    public int getLength() {
        return element.length();
    }

    public TypeElement getTypeElement() {
        return typeElement;
    }

    public Double getValueElement() {
        return valueElement;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public void setTypeElement(TypeElement typeElement) {
        this.typeElement = typeElement;
    }

    public void setValueElement(Double valueElement) {
        this.valueElement = valueElement;
    }

    public enum TypeElement {
        NUMBER,
        FUNCTION,
        BRACKET,
        SIGN
    }

}


