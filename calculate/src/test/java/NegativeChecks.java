import org.junit.jupiter.api.Test;

import static examplecalculator.ExampleCalculator.calculate;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NegativeChecks {

    @Test
    void badExample() {
        String expression = "+1%^&(((()))";
        double value = calculate(expression);
        double result = 3;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

}
