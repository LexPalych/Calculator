import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FirstTest {
//    @Test
//    void third() {
//        var expression1 = "-1.1+0.1+2+3*8/2-5*e^0+sin(30)-tan(0)-(1.5-0.5*2-1+3!)-(1+(2+((2+3)*2)/2-4)*3)"; // -7
//        var expression2 = "1-2*3*1-1*2/2-1*2*4-2"; // -16
//        var expression3 = "-1.1+0.1+2+3*8/2-5*e^0+sin(-30)-tan(0)-(1.5-0.5*2-1+3!)-(-1+(-2+((2+3)*2)/2-4)*3)"; // 6
//        var expression4 = "11-5*2/4/8*16+4+(7-9/3*4)-3*3"; // -4
//
//        var answer1 = -7;
//        var answer2 = -16;
//        var answer3 = 6;
//        var answer4 = -4;
//
////        var expressionResult1 = new CalculateString(expression1).calculateString();
////        var expressionResult2 = new CalculateString(expression2).calculateString();
////        var expressionResult3 = new CalculateString(expression3).calculateString();
////        var expressionResult4 = new CalculateString(expression4).calculateString();
//
////        assertTrue(expressionResult1 == -7);
////        assertTrue(expressionResult2 == -16);
////        assertTrue(expressionResult3 == 6);
////        assertTrue(expressionResult4 == -4);
//
//        var expressions = List.of(expression1, expression2, expression3, expression4);
//        var answers = List.of(answer1, answer2, answer3, answer4);
//
//        for (int i = 0; i < expressions.size(); i++) {
//            assertTrue(new CalculateString(expressions.get(i)).calculateString() == answers.get(i));
//        }
//    }

    @Test
    void addition() {
        String expression = "1+2";
        double value = new CalculateString(expression).calculateString();
        double result = 3;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void subtraction() {
        String expression = "9-3";
        double value = new CalculateString(expression).calculateString();
        double result = 6;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void multiplication() {
        String expression = "7*8";
        double value = new CalculateString(expression).calculateString();
        double result = 56;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void division() {
        String expression = "18/3";
        double value = new CalculateString(expression).calculateString();
        double result = 6;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void exponentiation() {
        String expression = "2^3";
        double value = new CalculateString(expression).calculateString();
        double result = 8;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void factorial() {
        String expression = "3!";
        double value = new CalculateString(expression).calculateString();
        double result = 6;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void firstMinus() {
        String expression = "-5+3";
        double value = new CalculateString(expression).calculateString();
        double result = -2;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void firstBracket() {
        String expression = "(2+3)";
        double value = new CalculateString(expression).calculateString();
        double result = 5;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void function() {
        String expression = "sin(30)";
        double value = new CalculateString(expression).calculateString();
        double result = 0.5;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

    @Test
    void multiExample() {
        String expression = "7+8+3*5-30/3+2^3";
        double value = new CalculateString(expression).calculateString();
        double result = 28;
        assertTrue(value == result, "Ожидалось " + result + ", а получилось " + value);
    }

}
