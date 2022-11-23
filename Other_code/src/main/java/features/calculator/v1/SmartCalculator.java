package features.calculator.v1;


import lombok.AllArgsConstructor;
import lombok.val;
//import lombok.var;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.function.BinaryOperator;

public class SmartCalculator {
    private static final String
            NUMBERS_CHARS = "0123456789.",
            OPERATORS_CHARS = "+-*/"
//            NUMBERS_CHARS = "+-*/^%", // expand version
    ;
    
    public String accept(String mathExpression) {
        val expression = clearEmptySpaces(mathExpression);
        if (!isFromNumberChar(expression)) return "Invalid: expression start from NuN";
        
        val planMathOperations = new ArrayList<MathAction>();
        int openBracketCountPriorityModifier = 0;
        StringBuilder currentNumberCollector = new StringBuilder();
    
    
       
        
        return null;
    }
    
    private Object parseExpression(String expression) {
        
        var numberBuffer = new StringBuffer();
        for (var symbol : expression.toCharArray()) {
            val symbolStr = "" + symbol;
        
            // is number
            if (NUMBERS_CHARS.contains(symbolStr)) {
                numberBuffer.append(symbol);
            
                // is operator
            } else if (OPERATORS_CHARS.contains(symbolStr)) {
                
                
                // is bracket
            }
//            else if (OPERATORS_CHARS.contains(symbolStr)) {
//            }
        
        }
        return null;
    }
    
    
    
    private String clearEmptySpaces(String expression) {
        return  expression.replace(" ", "");
    }
    private boolean isFromNumberChar(String expression) {
        return false; // todo - impl - regexp
    }
}

class MathAction implements Comparable<MathAction>{
    private Number leftNumber;
    private Number rightNumber;
    private BinaryOperator<Number> operator;
    
    private int executionPriority;
    private String operatorSymbol;
    private Number executeResult;
    
    public void setMissingNumber(Number missingNumber) {
        if (this.rightNumber == null && this.leftNumber == null) throw new RuntimeException("both numbers are missing");
        if (this.leftNumber == null) this.leftNumber = missingNumber;
        if (this.rightNumber == null) this.rightNumber= missingNumber;
    }
    
    public Number execute() {
        if (executeResult != null) return executeResult;
//        val t = new BigDecimal("123").subtract();
//        val t = new Long("123").intValue();
//        Long.
        
        return (executeResult = operator.apply(leftNumber, rightNumber));
    }
    
    @Override public int compareTo(@NotNull MathAction o) {
        return Integer.compare(this.executionPriority, o.executionPriority);
    }
}

/**
 * Operators priority: (1 - max priority)
 * 1 - () - brackets(Parentheses)
 * 2 - ^  - exponent
 * 3 - /* - Multiplication/Division
 * 4 - +- - Addition/Subtraction
 */
@AllArgsConstructor
@Deprecated
enum MathOperator {
//    BRACKET(),
//    EXPONENT(),
//
//    MULTIPLICATION(), DIVISION(),
//    PLUS("+", 1_000, (number, number2) ->  ), MINUS(),
    ;
    
    private String operatorSymbol;
    private int operatorPriorityModifier;
    private BinaryOperator<Number> operatorInteger;
    private BinaryOperator<Number> operatorDecimal;
    
//    MathOperator() {
//        new BigInteger()
//    }
//
//    private static Number integerPlusOperator(Number leftNumber, Number rightNumber) {
//        return
//    }
    
}
