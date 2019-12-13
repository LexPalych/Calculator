package examplecalculator;

import static examplecalculator.element.ElementListCreator.createElementList;
import static examplecalculator.ExampleValidation.assertExample;

public class ExampleCalculator {
    /**
     * ��������� ������ �� ������������ ������ � ���������� ��� ����������
     * ������� ��������, ����� � �������� ���������� ��� ����� ���� ��������� �����
     * @param example - ������
     * @return - ���������� �������� ��������� ����������
     */
    public static Double calculateExample(final String example) {
        assertExample(example);
        return createElementList(example);
    }
}