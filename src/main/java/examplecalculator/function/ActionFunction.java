package examplecalculator.function;

import static examplecalculator.function.ActionFunction.Actions.ADDITIONAL;
import static examplecalculator.function.ActionFunction.Actions.DIVISION;
import static examplecalculator.function.ActionFunction.Actions.EXPONENTIATION;
import static examplecalculator.function.ActionFunction.Actions.MULTIPLICATION;
import static examplecalculator.function.ActionFunction.Actions.SUBTRACTION;

import examplecalculator.ExampleException;
import java.util.Stack;
import java.util.function.BiFunction;

public final class ActionFunction {

    public static final Stack<Actions> ACTION_STACK = getActionStack();

//    public static final BiFunction<Double, Double, Double> ADDITIONAL = Double::sum;
//    public static final BiFunction<Double, Double, Double> SUBTRACTION = (x, y) -> x - y;
//    public static final BiFunction<Double, Double, Double> MULTIPLICATION = (x, y) -> x * y;
//    public static final BiFunction<Double, Double, Double> DIVISION = (x, y) -> x / y;
//    public static final BiFunction<Double, Double, Double> EXPONENTIATION = Math::pow;

    private static Stack<Actions> getActionStack() {
        Stack<Actions> actionStack = new Stack<>();
        actionStack.push(EXPONENTIATION);
        actionStack.push(DIVISION);
        actionStack.push(MULTIPLICATION);
        actionStack.push(SUBTRACTION);
        actionStack.push(ADDITIONAL);

        return actionStack;
    }

    public static Actions getMathAction(final String sign) {
        return switch (sign) {
            case "+" -> ADDITIONAL;
            case "-" -> SUBTRACTION;
            case "*" -> MULTIPLICATION;
            case "/" -> DIVISION;
            case "^" -> EXPONENTIATION;
            default -> throw new ExampleException("Неизвестный знак дейстия");
        };
    }

    public enum Actions {
        ADDITIONAL(Double::sum),
        SUBTRACTION((x, y) -> x - y),
        MULTIPLICATION((x, y) -> x * y),
        DIVISION((x, y) -> x / y),
        EXPONENTIATION(Math::pow);

        BiFunction<Double, Double, Double> action;

        Actions(BiFunction<Double, Double, Double> action) {
            this.action = action;
        }

        public BiFunction<Double, Double, Double> getAction() {
            return action;
        }
    }
}