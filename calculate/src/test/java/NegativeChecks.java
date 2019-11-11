import org.junit.jupiter.api.Test;

import static calculate.CalculateExample.calculateExample;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NegativeChecks {

    @Test
    void badExample() {
        String expression = "+1%^&(((()))";
        double value = calculateExample(expression);
        double result = 3;
        assertTrue(value == result, "ќжидалось " + result + ", а получилось " + value);
    }

}
