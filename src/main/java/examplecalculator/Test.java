package examplecalculator;

import static examplecalculator.ExampleCalculator.calculate;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test {
    public static void main(String[] args) {
        String expression = "-1.1+0.1+2+3*8/2-5*exp(0)+sin(30)-tan(0)-(1.5-0.5*2-1+3!)-(1+(2+((2+3)*2)/2-4)*3)";
        double value = calculate(expression);
        double result = -7;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
        System.out.println(value);
    }

}
