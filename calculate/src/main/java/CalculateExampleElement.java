import java.util.List;

public class CalculateExampleElement {
    static double calculateExpressionValue(List<Double> numberList, List<Character> signList) {
        List<Double> newNumberList = numberList;
        List<Character> newSignList = signList;
        double value;
        int i = 1;

        while (i < newSignList.size()) {
            if (newSignList.get(i) == '!') {
                value = MathFunctions.FACTORIAL.apply(newNumberList.get(i-1));
                newNumberList.set(i-1, value);

                newSignList.remove(i);

            } else {
                i++;
            }
        }
        i = 1;

        while (i < newSignList.size()) {
            if (newSignList.get(i) == '^') {
                value = MathFunctions.EXPONENTIATION.apply(newNumberList.get(i-1), newNumberList.get(i));
                newNumberList.set(i-1, value);

                newNumberList.remove(i);
                newSignList.remove(i);

            } else {
                i++;
            }
        }
        i = 1;

        while (i < newSignList.size()) {
            if (newSignList.get(i) == '/') {
                value = MathFunctions.DIVISION.apply(newNumberList.get(i-1), newNumberList.get(i));
                newNumberList.set(i-1, value);

                newNumberList.remove(i);
                newSignList.remove(i);

            } else {
                i++;
            }
        }
        i = 1;

        while (i < newSignList.size()) {
            if (newSignList.get(i) == '*') {
                value = MathFunctions.MULTIPLICATION.apply(newNumberList.get(i-1), newNumberList.get(i));
                newNumberList.set(i-1, value);

                newNumberList.remove(i);
                newSignList.remove(i);

            } else {
                i++;
            }
        }
        i = 1;

        while (i < newSignList.size()) {
            if (newSignList.get(i) == '-') {
                value = MathFunctions.SUBTRACTION.apply(newNumberList.get(i-1), newNumberList.get(i));
                newNumberList.set(i-1, value);

                newNumberList.remove(i);
                newSignList.remove(i);

            } else {
                i++;
            }
        }
        i = 1;

        while (i < newSignList.size()) {
            if (newSignList.get(i) == '+') {
                value = MathFunctions.ADDITIONAL.apply(newNumberList.get(i-1), newNumberList.get(i));
                newNumberList.set(i-1, value);

                newNumberList.remove(i);
                newSignList.remove(i);

            } else {
                i++;
            }
        }

        return newNumberList.get(0);
    }

}
