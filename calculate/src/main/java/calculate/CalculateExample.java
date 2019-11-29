package calculate;

import calculate.element.Element;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import static calculate.ExampleValidation.checkExample;
import static calculate.SymbolType.getCreateElementFunction;
import static calculate.element.CalculateElement.calculateElement;
import static calculate.element.Element.TypeElement.SIGN;

public class CalculateExample {
    /**
     * ��������� ������ �� ������������ ������ � ���������� ��� ����������
     * @param example - ������
     * @return - ���������� �������� ��������� ����������
     */
    public static double calculateExample(final String example) {
        checkExample(example);

        return calculate(example);
    }

    /**
     * ��������� ������ (��������� �������� �������) �� ������ ���������:
     * �����, ����� �������������� ��������, ��������� � �������, �������
     * �������� ����� ���������, ��� ��������� � ������� � �������, ���������� �������������
     * @param subExample - ������ (��������� �������� �������)
     * @return - ���������� ������ ��������� �������, ��������� �� �������� �������� � ������ (������-�������) ����� ����
     */
    public static Double calculate(final String subExample) {
        Element element;

        List<Element> elementList = new LinkedList<>();

        int i = 0;

        while (i < subExample.length()) {
            char symbol = subExample.charAt(i);

            Function<String, Element> createElement = getCreateElementFunction(symbol);
            element = createElement.apply(subExample.substring(i));

            elementList.add(element);
            i += element.getElement().length();
        }

        //���� ������ ������� ������� (���������� �������� �������) �������� ������ �������� (������� ����� ����� "-"),
        //�� �� ������� ������� ���������� ���� ("0.0"),
        //����� ���������� ������� "�����-����-�����-����-�����"
        if (elementList.get(0).getTypeElement() == SIGN) {
            elementList.add(0, new Element<>("0.0", 0.0));
        }

        return calculateElement(elementList);
    }
}