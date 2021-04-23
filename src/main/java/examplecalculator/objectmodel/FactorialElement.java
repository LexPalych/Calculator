package examplecalculator.objectmodel;

import static examplecalculator.objectmodel.Element.TypeElement.FACTORIAL;

public final class FactorialElement implements Element<Double> {

    private String element;
    private Double value;

    public FactorialElement(String element) {
        this.element = element;
    }

    public FactorialElement(String element, Double value) {
        this.element = element;
        this.value = getFactorial(value);
    }

    /**
     * Находит факториал числа
     *
     * @param number - число
     * @return - возвращает факториал числа типом Double
     */
    private static double getFactorial(final double number) {
        if (number < 0) {
            throw new ArithmeticException("Отрицательный аргумент факториала");
        }

        if (number % 1 != 0) {
            throw new ArithmeticException("Аргумент факториала не является целым числом");
        }

        if (number == 0 || number == 1) {
            return 1.0;
        } else {
            return number * getFactorial(number - 1);
        }
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
        return FACTORIAL;
    }
}