import org.junit.jupiter.api.Test;

import static calculate.CalculateExample.calculateExample;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NegativeChecks {

    @Test
    void badSymbol() {
        String expression = "1+2";
        double value = calculateExample(expression);
        double result = 3;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

}
