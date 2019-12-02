package examplecalculator;

import examplecalculator.exampleelement.ElementNumber;
import examplecalculator.exampleelement.Element;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import static examplecalculator.ExampleValidation.assertExample;
import static examplecalculator.exampleelement.ElementCalculator.calculateElement;
import static examplecalculator.exampleelement.Element.TypeElement.SIGN;
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
        List<Element> elementList = new LinkedList<>();
        Element element;
        int i = 0;

        while (i < subExample.length()) {
            char currentSymbol = subExample.charAt(i);

            //� ����������� �� ����, ����� ������� ������, ���������� �������, ������� ������ ������� �������
            Function<String, Element> createElementFunction = createElementFunction(currentSymbol);
            element = createElementFunction.apply(subExample.substring(i));
            elementList.add(element);

            //�������� ����������� �� ������ �������, �������� ����� ����� ���������� ������� �������� �������
            i += element.getElement().length();
        }

        //���� ������ ������� ������� (���������� �������� �������) �������� ������ �������� (������� ����� ����� "-"),
        //�� �� ������� ������� ���������� ���� ("0"),
        //����� ���������� ������� "�����-����-�����-...-����-...-�����"
        if (elementList.get(0).getTypeElement() == SIGN) {
            elementList.add(0, new ElementNumber("0"));
        }

        return calculateElement(elementList);
    }
}