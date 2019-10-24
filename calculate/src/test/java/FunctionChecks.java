import org.junit.jupiter.api.Test;

import static calculate.CalculateString.calculateString;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FunctionChecks {
    @Test
    void sin() {
        String expression = "sin(30)";
        double value = calculateString(expression);
        double result = 0.49999999999999994;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void cos() {
        String expression = "cos(60)";
        double value = calculateString(expression);
        double result = 0.5000000000000001;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void tan() {
        String expression = "tan(45)";
        double value = calculateString(expression);
        double result = 1;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void asin() {
        String expression = "asin(0.5)";
        double value = calculateString(expression);
        double result = 30;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void acos() {
        String expression = "acos(0.5)";
        double value = calculateString(expression);
        double result = 60;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void atan() {
        String expression = "atan(1)";
        double value = calculateString(expression);
        double result = 45;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void sinh() {
        String expression = "sinh(0)";
        double value = calculateString(expression);
        double result = 0.0;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void cosh() {
        String expression = "cosh(0)";
        double value = calculateString(expression);
        double result = 1.0;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void tanh() {
        String expression = "tanh(0)";
        double value = calculateString(expression);
        double result = 0.0;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void exp() {
        String expression = "exp(0)";
        double value = calculateString(expression);
        double result = 1;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void ln() {
        String expression = "ln(1)";
        double value = calculateString(expression);
        double result = 0;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void abs() {
        String expression = "abs(-10)";
        double value = calculateString(expression);
        double result = 10;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void sqrt() {
        String expression = "sqrt(16)";
        double value = calculateString(expression);
        double result = 4;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

}
