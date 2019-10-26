package calculate;

import static calculate.MathActionPriority.Priorities.*;

public class MathActionPriority {
    public static Priorities getPriority(final char symbol) {
        if (symbol == '!') {
            return FIRST;

        } else if (symbol == '^') {
            return SECOND;

        } else if (symbol == '/') {
            return THIRD;

        } else if (symbol == '*') {
            return FOURTH;

        } else if (symbol == '-') {
            return FIFTH;

        } else if (symbol == '+') {
            return SIXTH;

        } else {
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