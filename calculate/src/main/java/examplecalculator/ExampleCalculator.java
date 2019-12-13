package examplecalculator;

import static examplecalculator.element.ElementListCreator.createElementList;
import static examplecalculator.ExampleValidation.assertExample;

public class ExampleCalculator {
    /**
     * ѕровер€ет пример на корректность записи и производит его вычисление
     * ¬ынесен отдельно, чтобы в итоговой библиотеке был всего один публичный метод
     * @param example - пример
     * @return - возвращает итоговый результат вычислений
     */
    public static Double calculateExample(final String example) {
        assertExample(example);
        return createElementList(example);
    }
}