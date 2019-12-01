package examplecalculator;

import examplecalculator.exampleelement.ElementNumber;
import examplecalculator.exampleelement.IElement;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import static examplecalculator.ExampleValidation.assertExample;
import static examplecalculator.exampleelement.CalculateElement.calculateElement;
import static examplecalculator.exampleelement.IElement.TypeElement.SIGN;
import static examplecalculator.exampleelement.ElementCreator.createElementFunction;

public class ExampleCalculator {
    /**
     * ��������� ������ �� ������������ ������ � ���������� ��� ����������
     * @param example - ������
     * @return - ���������� �������� ��������� ����������
     */
    public static double calculateExample(final String example) {
        assertExample(example);

        return calculate(example);
    }

    /**
     * ��������� ������ (��������� �������� �������) �� ��������:
     * �����, ����� �������������� ��������, ��������� � �������, �������
     * �������� ����� ���������, ��� ��������� � ������� � �������, ���������� �������������
     * �������� ���������� �������� � ������ ��� ����������� ������� �������� ������� (���������� �������� �������)
     * @param subExample - ������ (��������� �������� �������)
     * @return - ���������� ������ ��������� �������, ��������� �� �������� �������� � ������ (������-�������) ����� ����
     */
    public static Double calculate(final String subExample) {
        List<IElement> elementList = new LinkedList<>();
        IElement element;
        int i = 0;

        while (i < subExample.length()) {
            char currentSymbol = subExample.charAt(i);

            //� ����������� �� ����, ����� ������� ������, ���������� �������, ������� ������ ������� �������
            Function<String, IElement> createElementFunction = createElementFunction(currentSymbol);
            element = createElementFunction.apply(subExample.substring(i));
            elementList.add(element);

            //�������� ����������� �� ������ �������, �������� ����� ����� ���������� ������� �������� �������
            i += element.getElement().length();
        }

        //���� ������ ������� ������� (���������� �������� �������) �������� ������ �������� (������� ����� ����� "-"),
        //�� �� ������� ������� ���������� ���� ("0.0"),
        //����� ���������� ������� "�����-����-�����-...-����-...-�����"
        if (elementList.get(0).getTypeElement() == SIGN) {
            elementList.add(0, new ElementNumber("0.0", 0.0));
        }

        return calculateElement(elementList);
    }
}