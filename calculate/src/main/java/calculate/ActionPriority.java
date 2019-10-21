package calculate;

import static calculate.ActionPriority.PriorityAction.*;

public class ActionPriority {
    public static PriorityAction getPriority(final char symbol) {
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

    public enum PriorityAction {
        FIRST,
        SECOND,
        THIRD,
        FOURTH,
        FIFTH,
        SIXTH
    }
}
