package calculate;

import java.util.List;

import static calculate.ActionPriority.PriorityAction.*;
import static calculate.ActionPriority.getPriority;
import static functions.MathFunctions.*;

public class CalculateExampleElement {
    static double calculateExpressionValue(List<Double> numberList, List<Character> signList) {
        List<Double> newNumberList = numberList;
        List<Character> newSignList = signList;
        double value;
        int i = 1;

        while (i < newSignList.size()) {
            if (getPriority(newSignList.get(i)) == FIRST) {
                value = FACTORIAL.apply(newNumberList.get(i-1));
                newNumberList.set(i-1, value);

                newSignList.remove(i);

            } else {
                i++;
            }
        }
        i = 1;

        while (i < newSignList.size()) {
            if (getPriority(newSignList.get(i)) == SECOND) {
                value = EXPONENTIATION.apply(newNumberList.get(i-1), newNumberList.get(i));
                newNumberList.set(i-1, value);

                newNumberList.remove(i);
                newSignList.remove(i);

            } else {
                i++;
            }
        }
        i = 1;

        while (i < newSignList.size()) {
            if (getPriority(newSignList.get(i)) == THIRD) {
                value = DIVISION.apply(newNumberList.get(i-1), newNumberList.get(i));
                newNumberList.set(i-1, value);

                newNumberList.remove(i);
                newSignList.remove(i);

            } else {
                i++;
            }
        }
        i = 1;

        while (i < newSignList.size()) {
            if (getPriority(newSignList.get(i)) == FOURTH) {
                value = MULTIPLICATION.apply(newNumberList.get(i-1), newNumberList.get(i));
                newNumberList.set(i-1, value);

                newNumberList.remove(i);
                newSignList.remove(i);

            } else {
                i++;
            }
        }
        i = 1;

        while (i < newSignList.size()) {
            if (getPriority(newSignList.get(i)) == FIFTH) {
                value = SUBTRACTION.apply(newNumberList.get(i-1), newNumberList.get(i));
                newNumberList.set(i-1, value);

                newNumberList.remove(i);
                newSignList.remove(i);

            } else {
                i++;
            }
        }
        i = 1;

        while (i < newSignList.size()) {
            if (getPriority(newSignList.get(i)) == SIXTH) {
                value = ADDITIONAL.apply(newNumberList.get(i-1), newNumberList.get(i));
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
