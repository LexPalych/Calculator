package examplecalculator;

import static examplecalculator.element.ElementListCreator.createElementList;
import static examplecalculator.ExampleValidation.assertExample;

public final class ExampleCalculator {
    /**
     * Проверяет пример на корректность записи и производит его вычисление
     * Вынесен отдельно, чтобы в итоговой библиотеке был всего один публичный метод
     * @param example - пример
     * @return - возвращает итоговый результат вычислений
     */
    public static Double calculateExample(final String example) {
        assertExample(example);
        return createElementList(example);
    }
}