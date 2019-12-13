package examplecalculator;

import static examplecalculator.ExampleElementListCreator.createElementList;
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