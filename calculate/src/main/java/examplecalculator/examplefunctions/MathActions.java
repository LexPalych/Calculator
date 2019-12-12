package examplecalculator.examplefunctions;

import examplecalculator.ExampleException;

import examplecalculator.exampleelement.Element;
import java.util.List;
import java.util.function.BiFunction;

public class MathActions {
//    static final BiFunction<Double, Double, Double> ADDITIONAL = Double::sum;
//    static final BiFunction<Double, Double, Double> SUBTRACTION = (x, y) -> x - y;
//    static final BiFunction<Double, Double, Double> MULTIPLICATION = (x, y) -> x * y;
//    static final BiFunction<Double, Double, Double> DIVISION = (x, y) -> x / y;
//    static final BiFunction<Double, Double, Double> EXPONENTIATION = Math::pow;
//    public static final BiFunction<Double, Double, Double> FIND_FACTORIAL = (x, y) -> getFactorial(x);

    static final BiFunction<List<Element>, Integer, List<Element>> ADDITIONAL = (list, i) -> {
        Double value = (Double) list.get(i-1).getValue() + (Double) list.get(i+1).getValue();
        return refreshList(value, list, i);
    };


    static final BiFunction<List<Element>, Integer, List<Element>> SUBTRACTION = (list, i) -> {
        Double value = (Double) list.get(i-1).getValue() - (Double) list.get(i+1).getValue();
        return refreshList(value, list, i);
    };


    static final BiFunction<List<Element>, Integer, List<Element>> MULTIPLICATION = (list, i) -> {
        Double value = (Double) list.get(i-1).getValue() * (Double) list.get(i+1).getValue();
        return refreshList(value, list, i);
    };


    static final BiFunction<List<Element>, Integer, List<Element>> DIVISION = (list, i) -> {
        Double value = (Double) list.get(i-1).getValue() / (Double) list.get(i+1).getValue();
        return refreshList(value, list, i);
    };


    static final BiFunction<List<Element>, Integer, List<Element>> EXPONENTIATION = (list, i) -> {
        Double value = Math.pow((Double) list.get(i-1).getValue(), (Double) list.get(i+1).getValue());
        return refreshList(value, list, i);
    };


    static final BiFunction<List<Element>, Integer, List<Element>> FIND_FACTORIAL = (list, i) -> {
        Double value = getFactorial((Double) list.get(i-1).getValue());
        list.get(i-1).setValue(value);
        list.remove(list.get(i));

        return list;
    };


    private static List<Element> refreshList(final Double value, final List<Element> elementList, final Integer index) {
        elementList.get(index-1).setValue(value);

        elementList.remove(elementList.get(index));
        elementList.remove(elementList.get(index));

        return elementList;
    }

    public static BiFunction<List<Element>, Integer, List<Element>> getMathFunction(final String sign) {
        switch (sign) {
            case "+":
                return ADDITIONAL;

            case "-":
                return SUBTRACTION;

            case "*":
                return MULTIPLICATION;

            case "/":
                return DIVISION;

            case "^":
                return EXPONENTIATION;

            case "!":
                return FIND_FACTORIAL;

            default:
                throw new ExampleException("Неизвестный знак дейстия");

        }
    }

    /**
     * Находит факториал числа
     * @param number - число
     * @return - возвращает факториал числа типом Double
     */
    private static Double getFactorial(final double number) {
        if (number < 0)
            throw new ArithmeticException("Отрицательный аргумент факториала");

        if (number % 1 !=0)
            throw new ArithmeticException("Аргумент факториала не является целым числом");

        if (number == 0 || number == 1)
            return 1.0;

        else
            return number * getFactorial(number-1);
    }

}