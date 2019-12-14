import org.junit.jupiter.api.Test;

import static examplecalculator.ExampleCalculator.calculateExample;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FunctionChecks {
    Double INACCURACY = 0.00000000000001;
    
    @Test
    void sin() {
        String expression = "sin(30)";
        double value = calculateExample(expression);
        double result = 0.5;
        assertTrue((Math.abs(value - result) < INACCURACY), "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void cos() {
        String expression = "cos(60)";
        double value = calculateExample(expression);
        double result = 0.5;
        assertTrue((Math.abs(value - result) < INACCURACY), "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void tan() {
        String expression = "tan(45)";
        double value = calculateExample(expression);
        double result = 1;
        assertTrue((Math.abs(value - result) < INACCURACY), "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void asin() {
        String expression = "asin(0.5)";
        double value = calculateExample(expression);
        double result = 30;
        double INACCURACY = 0.01;
        assertTrue((Math.abs(value - result) < INACCURACY), "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void acos() {
        String expression = "acos(0.5)";
        double value = calculateExample(expression);
        double result = 60;
        assertTrue((Math.abs(value - result) < INACCURACY), "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void atan() {
        String expression = "atan(1)";
        double value = calculateExample(expression);
        double result = 45;
        assertTrue((Math.abs(value - result) < INACCURACY), "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void sinh() {
        String expression = "sinh(0)";
        double value = calculateExample(expression);
        double result = 0.0;
        assertTrue((Math.abs(value - result) < INACCURACY), "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void cosh() {
        String expression = "cosh(0)";
        double value = calculateExample(expression);
        double result = 1.0;
        assertTrue((Math.abs(value - result) < INACCURACY), "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void tanh() {
        String expression = "tanh(0)";
        double value = calculateExample(expression);
        double result = 0.0;
        assertTrue((Math.abs(value - result) < INACCURACY), "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void exp() {
        String expression = "exp(0)";
        double value = calculateExample(expression);
        double result = 1;
        assertTrue((Math.abs(value - result) < INACCURACY), "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void ln() {
        String expression = "ln(1)";
        double value = calculateExample(expression);
        double result = 0;
        assertTrue((Math.abs(value - result) < INACCURACY), "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void abs() {
        String expression = "abs(-10)";
        double value = calculateExample(expression);
        double result = 10;
        assertTrue((Math.abs(value - result) < INACCURACY), "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void sqrt() {
        String expression = "sqrt(16)";
        double value = calculateExample(expression);
        double result = 4;
        assertTrue((Math.abs(value - result) < INACCURACY), "Ожидалось " + result + ", а получилось " + value);
    }

}
