public class ExampleElement {
    private static String example;
    private int lengthExample;

    private Character sign;
    private Double number;
    private Double function;

    public void setExpression(String expression) {
        this.example = expression;
    }

    public int getLengthExample() {
        return lengthExample;
    }

    public void setLengthExample(int lengthExample) {
        this.lengthExample = lengthExample;
    }

    public Character getSign() {
        return sign;
    }

    public void setSign(Character sign) {
        this.sign = sign;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public Double getFunction() {
        return function;
    }

    public void setFunction(Double function) {
        this.function = function;
    }

}
