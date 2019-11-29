package calculate;

import calculate.element.Element;

import java.util.List;
import java.util.function.Function;

import static calculate.SymbolType.Symbol.*;
import static calculate.element.ElementCreator.*;
import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;

/**
 * Класс типов символов примера: знак/число/буква/скобочка
 */
public class SymbolType {
    public static final Function<String, Element> DIGIT_FUNCTION = string -> getExampleNumber(string);
    public static final Function<String, Element> LETTER_FUNCTION = string -> getExampleFunction(string);
    public static final Function<String, Element> BRACKET_FUNCTION = string -> getExampleBracket(string);
    public static final Function<String, Element> SIGN_FUNCTION = string -> getExampleSign(string);
    
    public static Function<String, Element> getCreateElementFunction(final char symbol) {
        List<Character> signList = List.of('+', '-', '*', '/', '^', '!');

        if (signList.contains(symbol)) {
            return SIGN_FUNCTION;

        } else if (isDigit(symbol) || symbol == '.') {
            return DIGIT_FUNCTION;

        } else if (isLetter(symbol)) {
            return LETTER_FUNCTION;

        } else if (symbol == '(') {
            return BRACKET_FUNCTION;

        } else {
            throw new SecurityException("Неизестный сивол " + symbol);
        }
    }

    public static Symbol getSymbolType(final char symbol) {
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

    public enum Symbol {
        SIGN,
        DIGIT,
        LETTER,
        BRACKET,
    }
}