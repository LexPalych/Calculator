import java.util.List;
import java.util.stream.Collectors;

public class CalculateExpressionElement {
    private List<Double> numberList;
    private List<Character> signList;

    public List<Double> getNumberList() {
        return numberList;
    }

    public void setNumberList(List<Double> numberList) {
        this.numberList = numberList;
    }

    public List<Character> getSignList() {
        return signList;
    }

    public void setSignList(List<Character> signList) {
        this.signList = signList;
    }

    private static double calculateExpressionValue(List<Double> numberList, List<Character> signList) {
        int i = 1;
        double totalValue = numberList.get(0);
        double currentNumber;
        char currentSign;
        char nextSign;
        int lastIndex = numberList.size()-1;

        while (i <= lastIndex) {
            currentNumber = numberList.get(i);
            currentSign = signList.get(i);
            nextSign = signList.get(i+1);

            if (currentSign == '+' && (nextSign == '+' || nextSign == '-' || i == lastIndex)) {
                totalValue += currentNumber;
            }

            if (checkSignInList(i, '+', "+-")) {
                value += number;

            } else if (checkSignInList(i, '-', "+-")) {
                value -= number;

            } else if (checkSignInList(i, '*', "+-*/")) {
                value *= number;

            } else if (checkSignInList(i, '/', "+-*/")) {
                if (number != 0)
                    value /= number;
                else
                    throw new ArithmeticException("Деление на ноль");

            } else if (checkSignInList(i, '+', "*/^")) {
                value += getExpressionValue(++i, lastElement);
                break;

            } else if (checkSignInList(i, '-', "*/^")) {
                var closeIndex = getLastIndex(i, lastElement);

                value -= getExpressionValue(++i, closeIndex);
                i = closeIndex + 1;
                continue;

            } else if (checkSignInList(i, '*', "^")) {
                value *= getExpressionValue(++i, i);

            } else if (checkSignInList(i, '/', "^")) {
                var denominator = getExpressionValue(++i, i);
                if (denominator != 0)
                    value /= denominator;
                else
                    throw new ArithmeticException("Деление на ноль");

            } else if (getSign(i) == '^') {
                value = Math.pow(value, numberList.get(i));
            }
            i++;
        }

        return value;
    }

    /**
     * Находит индекс последнего знака, до которого нужно производить расчёт
     * @param firstIndex
     * @param lastIndex
     * @return
     */
    private static int getLastIndex(final int firstIndex, final int lastIndex) {
        var i = firstIndex;

        while (true) {
            i++;
            var currentSign = getSign(i);

            if (currentSign == '+' || currentSign == '-')
                return i-1;

            else if (i == lastIndex)
                return i;
        }

    }

    /**
     * Проверяет, что по определённому индексу находится определённый знак;
     * Проверяет, является ли этот знак последним;
     * Если нет, проверяет, содержится ли следующий за ним знак в определённом списке
     * @param index - индекс знака
     * @param currentSign - знак
     * @param signList - список знаков
     * @return - возвращает true, если по определённому индексу находитс определённый знак
     *          и следующий за ним знак находится в определённом списке знаков, либо если этот знак является последним
     */
    private static boolean checkSignInList(final int index, final char currentSign, final String signList) {
        var nextSign = getSign(index+1);
        if (nextSign != null) {
            var signInList =  signList
                    .chars()
                    .mapToObj(i -> (char) i)
                    .collect(Collectors.toList())
                    .contains(nextSign);

            return (getSign(index) == currentSign) && signInList;
        }

        return (getSign(index) == currentSign);
    }
}
