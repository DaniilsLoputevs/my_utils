package features;

import features.calculator.SmartMathCalculator;
import features.calculator.v1.SmartCalculator;
import features.calculator.v2.SmartCalculatorV2;
import org.junit.Test;

/**
 * Operators priority: (1 - max priority)
 * 1 - () - brackets(Parentheses)
 * 2 - ^  - exponentiation
 * 3 - /* - Multiplication/Division
 * 4 - +- - Addition/Subtraction
 * todo - validation - brackets pair
 */
import static org.junit.Assert.*;

public class SmartCalculatorTest {
    private final SmartMathCalculator calculator = new SmartCalculatorV2();
    
    private void test(String expected, String expr) {
        assertEquals(expected, calculator.accept(expr));
    }
    
    
    @Test public void fivePlusTwo() { test("7", "5+2"); }
    
    @Test public void fivePlusTwoPlusTwo() { test("9", "5+2+2"); }
    
    @Test public void fivePlusTwoMinusTwo() { test("5", "5+2-2"); }
    
    @Test public void fivePlusTwoMultipleFive() { test("17", "5+2*6"); }
    
    @Test public void fivePlusTwoMultipleSixDivideThree() { test("9", "5+2*6/3"); }
    
//    @Test public void fiveExponentTwoPlusTwoMultipleSixDivideThree() { test("29", "5^2+2*6/3"); }
    
//    @Test public void fiveExponentBracketUpTwoPlusTwoMultipleSixBracketDownDivideThree() { test("29", "5^(2+2*6)/3"); }
    
//    @Test public void fiveExponentBracketUpBracketUpTwoPlusTwoBracketDownMultipleSixDivideThree() { test("29", "5^((2+2)*6)/3"); }
    
    @Test public void smart1() { test("38", "2+(3*4-5+(6+4)+(10-7*2))-5"); }
    
    @Test public void brackets1() {
        test("38", "2+(((3*4-5+6)+4)+(10-7*2))-5");
    }
    
    @Test public void brackets2() {
        test("56", "2+( ( (1+3)+(4+4)+2 ) + (6+7) )*2");
    }
}