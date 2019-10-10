import java.util.List;

public class CalculateExpressionElement {
    static double calculateExpressionValue(List<Double> numberList, List<Character> signList) {
        List<Double> newNumberList = numberList;
        List<Character> newSignList = signList;
        double value;
        int i = 1;

        while (i < newSignList.size()) {
            if (newSignList.get(i) == '^') {
                value = Math.pow(newNumberList.get(i-1), newNumberList.get(i));
                newNumberList.add(i-1, value);

                newNumberList.removeAll(newNumberList.subList(i, i+1));
                newSignList.removeAll(newSignList.subList(i, i+1));

            } else {
                i++;
            }
        }

        while (i < newSignList.size()) {
            if (newSignList.get(i) == '*') {
                value = newNumberList.get(i-1) * newNumberList.get(i);
                newNumberList.add(i-1, value);

                newNumberList.removeAll(newNumberList.subList(i, i+1));
                newSignList.removeAll(newSignList.subList(i, i+1));

            } else {
                i++;
            }
        }

        while (i < newSignList.size()) {
            if (newSignList.get(i) == '/') {
                value = newNumberList.get(i-1) / newNumberList.get(i);
                newNumberList.add(i-1, value);

                newNumberList.removeAll(newNumberList.subList(i, i+1));
                newSignList.removeAll(newSignList.subList(i, i+1));

            } else {
                i++;
            }
        }

        while (i < newSignList.size()) {
            if (newSignList.get(i) == '+') {
                value = newNumberList.get(i-1) + newNumberList.get(i);
                newNumberList.add(i-1, value);

                newNumberList.removeAll(newNumberList.subList(i, i+1));
                newSignList.removeAll(newSignList.subList(i, i+1));

            } else {
                i++;
            }
        }

        while (i < newSignList.size()) {
            if (newSignList.get(i) == '-') {
                value = newNumberList.get(i-1) - newNumberList.get(i);
                newNumberList.add(i-1, value);

                newNumberList.removeAll(newNumberList.subList(i, i+1));
                newSignList.removeAll(newSignList.subList(i, i+1));

            } else {
                i++;
            }
        }

        return newNumberList.get(0);
    }

}
