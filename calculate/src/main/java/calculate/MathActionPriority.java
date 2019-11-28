package calculate;

import java.util.List;
import java.util.function.BiFunction;

import static calculate.MathActionPriority.Priorities.*;
import static calculate.functions.MathFunctions.*;

public class MathActionPriority {
    /**
     * Присваивает приоритет (порядок действия) знака логического действия примера
     * @param symbol - знак
     * @return - возвращает приоритет знака
     */
//    public static Priorities getPriority(final char symbol) {
//        switch (symbol) {
//            case '!':
//                return FIRST;
//
//            case '^':
//                return SECOND;
//
//            case '/':
//                return THIRD;
//
//            case '*':
//                return FOURTH;
//
//            case '-':
//                return FIFTH;
//
//            case '+':
//                return SIXTH;
//
//            default:
//                throw new SecurityException("Неизестный сивол " + symbol);
//        }
//    }

    public static final List<BiFunction> PRIORITY_LIST = List.of(FACTORIAL, EXPONENTIATION, DIVISION, MULTIPLICATION, SUBTRACTION, ADDITIONAL);

    public static Priorities getPriority(final String symbol) {
        switch (symbol) {
            case "!":
                return FIRST;

            case "^":
                return SECOND;

            case "/":
                return THIRD;

            case "*":
                return FOURTH;

            case "-":
                return FIFTH;

            case "+":
                return SIXTH;

            default:
                throw new SecurityException("Неизестный сивол " + symbol);
        }
    }


    public enum Priorities {
        FIRST,
        SECOND,
        THIRD,
        FOURTH,
        FIFTH,
        SIXTH
    }
}