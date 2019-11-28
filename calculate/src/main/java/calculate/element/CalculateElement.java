package calculate.element;

import java.util.List;
import java.util.function.BiFunction;

import static calculate.MathActionPriority.PRIORITY_LIST;
import static calculate.element.Element.TypeElement.SIGN;
import static calculate.functions.MathFunctions.FACTORIAL;

public class CalculateElement {
    /**
     * Выполняет расчёт примера в соответстии с порядком дейстий
     */
    public static double calculateElement(final List<Element> elementList) {
        double value;
        int i;
        Element element;

        for (BiFunction function : PRIORITY_LIST) {
            i = 0;

            while (i < elementList.size()) {
                element = elementList.get(i);

                if (element.getTypeElement() == SIGN && element.getValue() == FACTORIAL) {
//                    value = getFactorial((Double) elementList.get(i-1).getValue());

                    value = (double) function.apply(elementList.get(i-1).getValue(), null);
                    elementList.get(i-1).setValue(value);
//                    elementList.set(i-1, elementList.get(i-1));

                    elementList.remove(i);

                } else if (element.getTypeElement() == SIGN && element.getValue() == function) {

                    value = (double) function.apply(elementList.get(i-1).getValue(), elementList.get(i+1).getValue());

                    elementList.get(i-1).setValue(value);
//                    elementList.set(i-1, elementList.get(i-1));

                    elementList.remove(i);
                    elementList.remove(i);

                } else {
                    i++;
                }
            }
        }

        return (double) elementList.get(0).getValue();

    }
}