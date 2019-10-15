import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleChecks {

    @Test
    void addition() {
        String expression = "1+2";
        double value = CalculateString.calculateString(expression);
        double result = 3;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void subtraction() {
        String expression = "9-3";
        double value = CalculateString.calculateString(expression);
        double result = 6;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void multiplication() {
        String expression = "7*8";
        double value = CalculateString.calculateString(expression);
        double result = 56;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void division() {
        String expression = "18/3";
        double value = CalculateString.calculateString(expression);
        double result = 6;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void exponentiation() {
        String expression = "2^3";
        double value = CalculateString.calculateString(expression);
        double result = 8;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void factorial() {
        String expression = "3!";
        double value = CalculateString.calculateString(expression);
        double result = 6;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

}
