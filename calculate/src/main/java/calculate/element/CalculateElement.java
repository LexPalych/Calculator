package calculate.element;

import calculate.MathActionPriority;

import java.util.List;
import java.util.function.BiFunction;

import static calculate.MathActionPriority.Priorities.*;
import static calculate.MathActionPriority.getPriority;
import static calculate.functions.MathFunctions.*;

public class CalculateElement {
    public static double calculateElement(final List<Double> numberList, final List<Character> signList) {
        BiFunction<Double, Double, Double> function;
        MathActionPriority.Priorities currentPriorities;
        double value;
        int i;

        List<MathActionPriority.Priorities> prioritiesList = List.of(FIRST, SECOND, THIRD, FOURTH, FIFTH, SIXTH);

        for (MathActionPriority.Priorities priority : prioritiesList) {
            i = 1;

            while (i < signList.size()) {
                currentPriorities = getPriority(signList.get(i));

                if (currentPriorities == FIRST) {
                    numberList.set(i - 1, getFactorial(numberList.get(i - 1)));
                    signList.remove(i);

                } else if (currentPriorities == priority) {
                    function = getFunction(priority);
                    value = function.apply(numberList.get(i - 1), numberList.get(i));
                    numberList.set(i - 1, value);

                    numberList.remove(i);
                    signList.remove(i);

                } else {
                    i++;
                }
            }
        }

        return numberList.get(0);
    }
}