import org.junit.jupiter.api.Test;

import static calculate.CalculateExample.calculateExample;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BracketChecks {
    @Test
    void bracket1() {
        String expression = "(1+2)";
        double value = calculateExample(expression);
        double result = 3;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void bracket2() {
        String expression = "1-(2+5)";
        double value = calculateExample(expression);
        double result = -6;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void bracket3() {
        String expression = "1-(2+5)-4";
        double value = calculateExample(expression);
        double result = -10;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void bracket4() {
        String expression = "1-(2+5)+4";
        double value = calculateExample(expression);
        double result = -2;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void bracket5() {
        String expression = "2*(-2+5)-4";
        double value = calculateExample(expression);
        double result = 2;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void bracket16() {
        String expression = "10/(-2+7)+4";
        double value = calculateExample(expression);
        double result = 6;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void bracket7() {
        String expression = "(1+2)-(3-4)";
        double value = calculateExample(expression);
        double result = 4;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void bracket18() {
        String expression = "-(1+2)*(3-4)+5";
        double value = calculateExample(expression);
        double result = 8;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void bracket19() {
        String expression = "(1+((1+2)+2))";
        double value = calculateExample(expression);
        double result = 6;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

}
