import java.util.List;

public class CalculateExpressionElement {
    static double calculateExpressionValue(List<Double> numberList, List<Character> signList) {
        List<Double> newNumberList = numberList;
        List<Character> newSignList = signList;
        double value;
        int i = 1;

        while (i < newSignList.size()) {
            if (newSignList.get(i) == '!') {
                value = CalculateString.factorial(newNumberList.get(i-1));
                newNumberList.set(i-1, value);

                newSignList.remove(i);

            } else {
                i++;
            }
        }
        i = 1;

        while (i < newSignList.size()) {
            if (newSignList.get(i) == '^') {
                value = Math.pow(newNumberList.get(i-1), newNumberList.get(i));
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
                value = newNumberList.get(i-1) / newNumberList.get(i);
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
                value = newNumberList.get(i-1) * newNumberList.get(i);
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
                value = newNumberList.get(i-1) - newNumberList.get(i);
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
                value = newNumberList.get(i-1) + newNumberList.get(i);
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
