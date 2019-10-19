package calculate;

import java.util.List;

import static calculate.SymbolType.*;
import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;

public class SymbolValidation {
    public static SymbolType getSymbolType(final char symbol) {
        List<Character> signList = List.of('+', '-', '*', '/', '^', '!');

        if (signList.contains(symbol)) {
            return SIGN;

        } else if (isDigit(symbol) || symbol == '.') {
            return DIGIT;

        } else if (isLetter(symbol)) {
            return LETTER;

        } else if (symbol == '(') {
            return BRACKET;

        } else {
            throw new SecurityException("Неизестный сивол " + symbol);
        }
    }
}

