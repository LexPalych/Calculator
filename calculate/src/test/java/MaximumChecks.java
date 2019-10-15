import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MaximumChecks {
    @Test
    void maximumChecks1() {
        String expression = "-1.1+0.1+2+3*8/2-5*e^0+sin(30)-tan(0)-(1.5-0.5*2-1+3!)-(1+(2+((2+3)*2)/2-4)*3)";
        double value = CalculateString.calculate(expression);
        double result = -7;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void maximumChecks2() {
        String expression = "-1.1+0.1+2+3*8/2-5*e^0+sin(-30)-tan(0)-(1.5-0.5*2-1+3!)-(-1+(-2+((2+3)*2)/2-4)*3)";
        double value = CalculateString.calculate(expression);
        double result = 6;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

}
