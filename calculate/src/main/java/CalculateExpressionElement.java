import java.util.List;

public class CalculateExpressionElement {
    static double calculateExpressionValue(List<Double> numberList, List<Character> signList) {
        List<Double> newNumberList = numberList;
        List<Character> newSignList = signList;
        double value;
        int i = 1;

        while (i < newSignList.size()) {
            if (newSignList.get(i) == '!') {
                value = Functions.FACTORIAL.apply(newNumberList.get(i-1));
                newNumberList.set(i-1, value);

                newSignList.remove(i);

            } else {
                i++;
            }
        }
        i = 1;

        while (i < newSignList.size()) {
            if (newSignList.get(i) == '^') {
                value = Functions.EXPONENTIATION.apply(newNumberList.get(i-1), newNumberList.get(i));
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
                value = Functions.DIVISION.apply(newNumberList.get(i-1), newNumberList.get(i));
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
                value = Functions.MULTIPLICATION.apply(newNumberList.get(i-1), newNumberList.get(i));
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
                value = Functions.SUBTRACTION.apply(newNumberList.get(i-1), newNumberList.get(i));
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
                value = Functions.ADDITIONAL.apply(newNumberList.get(i-1), newNumberList.get(i));
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
