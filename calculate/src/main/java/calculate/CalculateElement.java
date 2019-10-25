package calculate;

import java.util.List;
import java.util.function.BiFunction;

import static calculate.MathActionPriority.Priorities.*;
import static calculate.MathActionPriority.getPriority;
import static functions.MathFunctions.*;

public class CalculateElement {
    static double calculateElement(final List<Double> numberList, final List<Character> signList) {
        BiFunction<Double, Double, Double> function;
        MathActionPriority.Priorities actionPriority;
        double value;
        int i = 1;

        List<MathActionPriority.Priorities> prioritiesList = List.of(FIRST, SECOND, THIRD, FOURTH, FIFTH, SIXTH);

        for (MathActionPriority.Priorities priority : prioritiesList) {
            while (i < signList.size()) {

                if (priority == getPriority(signList.get(i))) {
                    if (priority == FIRST) {
                        numberList.set(i - 1, getFactorial(numberList.get(i - 1)));
                        signList.remove(i);

                    } else {
                        function = getFunction(priority);
                        value = function.apply(numberList.get(i - 1), numberList.get(i));
                        numberList.set(i - 1, value);

                        numberList.remove(i);
                        signList.remove(i);
                    }

                } else {
                    i++;
                }
            }
            i = 1;
        }
        return numberList.get(0);
    }

}
