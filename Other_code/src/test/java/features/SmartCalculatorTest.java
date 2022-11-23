package features;

import features.calculator.v1.SmartCalculator;
import org.junit.Test;

/**
 * Operators priority: (1 - max priority)
 * 1 - () - brackets(Parentheses)
 * 2 - ^  - exponentiation
 * 3 - /* - Multiplication/Division
 * 4 - +- - Addition/Subtraction
 */
import static org.junit.Assert.*;

public class SmartCalculatorTest {
    private final SmartCalculator calculator = new SmartCalculator();
    

    @Test
    public void fivePlusTwo() { //                                 1
        assertEquals("7", calculator.accept("5+2") );
    }
    @Test
    public void fivePlusTwoPlusTwo() { //                          1 2
        assertEquals("9", calculator.accept("5+2+2") );
    }
    @Test
    public void fivePlusTwoMinusTwo() { //                         1 2
        assertEquals("5", calculator.accept("5+2-2") );
    }
    @Test
    public void fivePlusTwoMultipleFive() { //                      2 1
        assertEquals("17", calculator.accept("5+2*6") );
    }
    @Test
    public void fivePlusTwoMultipleSixDivideThree() { //           3 1 2
        assertEquals("9", calculator.accept("5+2*6/3") );
    }
    
    @Test
    public void fiveExponentTwoPlusTwoMultipleSixDivideThree() {
        assertEquals("29", calculator.accept("5^2+2*6/3") );
    }
    @Test
    public void fiveExponentBracketUpTwoPlusTwoMultipleSixBracketDownDivideThree() {
        assertEquals("29", calculator.accept("5^(2+2*6)/3") );
    }
    @Test
    public void fiveExponentBracketUpBracketUpTwoPlusTwoBracketDownMultipleSixDivideThree() {
        assertEquals("29", calculator.accept("5^((2+2)*6)/3") );
    }
    @Test
    public void smart1() {
        assertEquals("-1", calculator.accept("2+(3*4-5+(6+4)+(10-7*2))-5") );
    }
    @Test
    public void brackets1() {
        assertEquals("-1", calculator.accept("2+(((3*4-5+6)+4)+(10-7*2))-5") );
    }
    @Test
    public void brackets2() { //                                       0 1   1 1   1   0
        assertEquals("-1", calculator.accept("2+( ( (1+3)+(4+4)+2 ) + (6+7) )*2") );
    }
}