package calculate;

import java.util.List;
import java.util.function.BiFunction;

import static calculate.ActionPriority.Priorities.*;
import static calculate.ActionPriority.getPriority;
import static functions.MathFunctions.*;

public class CalculateExampleElement {
    static double calculateExpressionValue(List<Double> numberList, List<Character> signList) {
        List<Double> newNumberList = numberList;
        List<Character> newSignList = signList;
        BiFunction<Double, Double, Double> function;
        ActionPriority.Priorities actionPriority;
        double value;
        int i = 1;

        List<ActionPriority.Priorities> prioritiesList = List.of(FIRST, SECOND, THIRD, FOURTH, FIFTH, SIXTH);

        for (int n = 1; n < prioritiesList.size(); n++) {
            while (i < newSignList.size()) {
                actionPriority = prioritiesList.get(n);

                if (actionPriority == getPriority(newSignList.get(i))) {
                    function = getFunction(actionPriority);
                    value = function.apply(newNumberList.get(i-1), newNumberList.get(i));
                    newNumberList.set(i-1, value);

                    newNumberList.remove(i);
                    newSignList.remove(i);

                } else {
                    i++;
                }

            }

            i = 1;

        }

//        while (i < newSignList.size()) {
//            actionPriority = getPriority(newSignList.get(i));
//            function = getFunction(actionPriority);
//            if (actionPriority == FIRST) {
//                value = function.apply(newNumberList.get(i-1), 1.0);
//                newNumberList.set(i-1, value);
//
//                newSignList.remove(i);
//
//            } else {
//                i++;
//            }
//        }
//        i = 1;
//
//        while (i < newSignList.size()) {
//            actionPriority = getPriority(newSignList.get(i));
//            function = getFunction(actionPriority);
//            if (actionPriority == SECOND) {
//                value = function.apply(newNumberList.get(i-1), newNumberList.get(i));
//                newNumberList.set(i-1, value);
//
//                newNumberList.remove(i);
//                newSignList.remove(i);
//
//            } else {
//                i++;
//            }
//        }
//        i = 1;
//
//        while (i < newSignList.size()) {
//            actionPriority = getPriority(newSignList.get(i));
//            function = getFunction(actionPriority);
//            if (actionPriority == THIRD) {
//                value = function.apply(newNumberList.get(i-1), newNumberList.get(i));
//                newNumberList.set(i-1, value);
//
//                newNumberList.remove(i);
//                newSignList.remove(i);
//
//            } else {
//                i++;
//            }
//        }
//        i = 1;
//
//        while (i < newSignList.size()) {
//            actionPriority = getPriority(newSignList.get(i));
//            function = getFunction(actionPriority);
//            if (actionPriority == FOURTH) {
//                value = function.apply(newNumberList.get(i-1), newNumberList.get(i));
//                newNumberList.set(i-1, value);
//
//                newNumberList.remove(i);
//                newSignList.remove(i);
//
//            } else {
//                i++;
//            }
//        }
//        i = 1;
//
//        while (i < newSignList.size()) {
//            actionPriority = getPriority(newSignList.get(i));
//            function = getFunction(actionPriority);
//            if (actionPriority == FIFTH) {
//                value = function.apply(newNumberList.get(i-1), newNumberList.get(i));
//                newNumberList.set(i-1, value);
//
//                newNumberList.remove(i);
//                newSignList.remove(i);
//
//            } else {
//                i++;
//            }
//        }
//        i = 1;
//
//        while (i < newSignList.size()) {
//            actionPriority = getPriority(newSignList.get(i));
//            function = getFunction(actionPriority);
//            if (actionPriority == SIXTH) {
//                value = function.apply(newNumberList.get(i-1), newNumberList.get(i));
//                newNumberList.set(i-1, value);
//
//                newNumberList.remove(i);
//                newSignList.remove(i);
//
//            } else {
//                i++;
//            }
//        }

        return newNumberList.get(0);
    }

}
