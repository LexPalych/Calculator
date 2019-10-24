package calculate;

import java.util.List;
import java.util.function.BiFunction;

import static calculate.MathActionPriority.Priorities.*;
import static calculate.MathActionPriority.getPriority;
import static functions.MathFunctions.*;

public class CalculateElement {
    static double calculateExpressionValue(final List<Double> numberList, final List<Character> signList) {
        BiFunction<Double, Double, Double> function;
        MathActionPriority.Priorities actionPriority;
        double value;
        int i = 1;

        List<MathActionPriority.Priorities> prioritiesList = List.of(FIRST, SECOND, THIRD, FOURTH, FIFTH, SIXTH);

        for (int n = 1; n < prioritiesList.size(); n++) {
            while (i < signList.size()) {
                actionPriority = prioritiesList.get(n);

                if (actionPriority == getPriority(signList.get(i))) {
                    function = getFunction(actionPriority);
                    value = function.apply(numberList.get(i-1), numberList.get(i));
                    numberList.set(i-1, value);

                    numberList.remove(i);
                    signList.remove(i);

                } else {
                    i++;
                }
            }
            i = 1;
        }
        return numberList.get(0);
    }

}
