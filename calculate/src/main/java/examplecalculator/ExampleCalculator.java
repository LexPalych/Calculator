package examplecalculator;

import examplecalculator.exampleelement.Element;
import examplecalculator.exampleelement.ElementFactorial;
import examplecalculator.exampleelement.ElementNumber;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import static examplecalculator.ExampleValidation.assertExample;
import static examplecalculator.exampleelement.Element.TypeElement.SIGN;
import static examplecalculator.exampleelement.ElementCalculator.calculateElement;
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

        return calculateElement(replaceElementList(elementList));
    }

    private static List<Element> replaceElementList(final List<Element> rowElementList) {
        replaceFirstElement(rowElementList);
        replaceFactorialElement(rowElementList);

        return rowElementList;
    }

    /**
     * ���������, ���� �� � ������ ������ ������� ���� SIGN (���� ����� "-")
     * ���� ����, �� � ������ ������ ���������� ���� ("0")
     * ����� ��� ���������� �������� "�����-����-�����-...-����-...-�����"
     * @param elementList - "�����" ������ ���������
     * @return - ������������ ������ ���������
     */
    private static List<Element> replaceFirstElement(final List<Element> elementList) {
        if (elementList.get(0).getTypeElement() == SIGN) {
            elementList.add(0, new ElementNumber("0"));
        }

        return elementList;
    }

    /**
     * ���������, ���� �� � "�����" ������ ��������� ���� ���������� "!"
     * ���� ����, �������� ���������� ������� ���� NUMBER �� ������� ���� FACTORIAL
     * ���� "!" ������������ � ������ ���������� �������� � ���������� � "�����" ������ ���������
     * ����� ��� ���������� �������� "�����-����-�����-...-����-...-�����"
     * @param elementList - "�����" ������ ���������
     * @return - ������������ ������ ���������
     */
    private static List<Element> replaceFactorialElement(final List<Element> elementList) {
        int i = 0;

        while (i < elementList.size()) {
            if (elementList.get(i).getElement().equals("!")) {
                elementList.set(i-1, new ElementFactorial(elementList.get(i-1).getElement()));
                elementList.remove(i);

            } else {
                i++;
            }
        }

        return elementList;
    }

}