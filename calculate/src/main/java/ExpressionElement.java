import java.util.List;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;

public class ExpressionElement {
    private static String expression;
    private int length;

    private String firstSymbol;
    private String lastSymbol;

    private int firstSymbolIndex;
    private int lastSymbolIndex;

    private Character sign;
    private Double number;
    private Double function;

    private static char getChar(int index) {
        return expression.charAt(index);
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getFirstSymbol() {
        return firstSymbol;
    }

    public void setFirstSymbol(String firstSymbol) {
        this.firstSymbol = firstSymbol;
    }

    public String getLastSymbol() {
        return lastSymbol;
    }

    public void setLastSymbol(String lastSymbol) {
        this.lastSymbol = lastSymbol;
    }

    public int getFirstSymbolIndex() {
        return firstSymbolIndex;
    }

    public void setFirstSymbolIndex(int firstSymbolIndex) {
        this.firstSymbolIndex = firstSymbolIndex;
    }

    public int getLastSymbolIndex() {
        return lastSymbolIndex;
    }

    public void setLastSymbolIndex(int lastSymbolIndex) {
        this.lastSymbolIndex = lastSymbolIndex;
    }

    public Character getSign() {
        return sign;
    }

    public void setSign(Character sign) {
        this.sign = sign;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public Double getFunction() {
        return function;
    }

    public void setFunction(Double function) {
        this.function = function;
    }

    public static SymbolType checkSymbolType(final char symbol) {
        List<Character> signList = List.of('+', '-', '*', '/', '^', '!');

        if (signList.contains(symbol)) {
            return SymbolType.SIGN;

        } else if (isDigit(symbol) || symbol == '.') {
            return SymbolType.DIGIT;

        } else if (isLetter(symbol)) {
            return SymbolType.LETTER;

        } else if (symbol == '(') {
            return SymbolType.BRACKET;

        } else {
            throw new SecurityException("Неизестный сивол " + symbol);
        }
    }

    public static SymbolType getSymbolType(final int index) {
        return checkSymbolType(getChar(index));
    }
}
