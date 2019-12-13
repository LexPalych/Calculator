package examplecalculator.examplefunctions;

import examplecalculator.exampleelement.Element;

import java.util.List;
import java.util.function.BiFunction;

import static examplecalculator.exampleelement.Element.TypeElement.SIGN;
import static examplecalculator.examplefunctions.MathActions.*;
import static java.util.stream.Collectors.toList;

/**
 * �����, ���������� ���������� � ���������� ����������� �������������� ��������
 */
public class ActionPriority {
    private static final List<BiFunction> PRIORITY_ORDER =  List.of(EXPONENTIATION, DIVISION, MULTIPLICATION, SUBTRACTION, ADDITIONAL);

    /**
     * ��������������� �� ������ ��������� ������ �����
     * @param elementList - ������ ���������
     * @return - ���������� ������ ������ � ���� �����
     */
    private static List<BiFunction> getSignList(final List<Element> elementList) {
        return elementList
                .stream()
                .filter(element -> element.getTypeElement() == SIGN)
                .map(element -> (BiFunction) element.getValue())
                .collect(toList());
    }

    /**
     * ��������� �������, � ������� ����� ����������� ��������:
     * �� ������ ������ ������, ������ �� ������� ���������� �� �������� � ��������,
     * ��������� ������ ��, ������� ������������ � ������ ���������.
     * ����� ��� ��������� ������ � ����� �������� ������ �������� �� ����� ������ ���������
     */
    public static List<BiFunction> getActionOrderList(final List<Element> elementList) {
        List<BiFunction> signList = getSignList(elementList);

        return PRIORITY_ORDER
                .stream()
                .filter(signList::contains)
                .collect(toList());
    }
}