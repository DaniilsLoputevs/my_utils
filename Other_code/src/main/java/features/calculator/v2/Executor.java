package features.calculator.v2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.val;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import static features.calculator.v2.Executor.MathOperator.*;

public class Executor {
    public static void main(String[] args) {
        val src = "10/2-5+3*10/4-2";
        System.out.println("src = " + src);
        val executor = new Executor();
        executor.execute(new ScopeExpression("${expr_00}", src));
    }
    
    // "10-5+3*10/2"
    // "10-5+3*5"
    // "10-5+15"
    // "5+15"
    
    /**
     * 2*3+10*3-6
     */
    public BigDecimal execute(ScopeExpression expression) {
        val exprInfo = ExpressionInfo.of(expression.expression);
    
        // if expression doesn't contains operator(suddenly) return expression as number.
        while (exprInfo.middleLevelOperatorsCount != 0) {
            var currExp = exprInfo.expressionCurrent;
            
            // step 1 - find all middle-Level operators
            var chars = currExp.toCharArray();
            int prevOperatorIndex = -1;
            boolean isPrevOperatorFound = false;
            
            
            for (int i = 0; i < chars.length; i++) {
                val c = chars[i];
                val operator = MathOperator.of(c);
                if (operator == null) continue; // skip number when search operator char.
                
                
                // skip not our level operators
                if (operator != MathOperator.DIVISION && operator != MULTIPLICATION) {
                    prevOperatorIndex = i;
                    isPrevOperatorFound = true;
                    continue;
                }
                
                val leftNum = extractWholeNumberFromString(currExp, prevOperatorIndex);
                val rightNum = extractWholeNumberFromString(currExp, i);
                val rsl = operator.operatorDecimal.apply(leftNum, rightNum);
                
                exprInfo.allOperatorsCount--;
                exprInfo.middleLevelOperatorsCount--;
                
                val nextOperatorIndex = (isPrevOperatorFound ? prevOperatorIndex +1: 0)
                        + 1 // +1 for operator char offset
                        + (leftNum.toString().length())
                        + (rightNum.toString().length());
                
                exprInfo.expressionCurrent = (!isPrevOperatorFound ? "" : currExp.substring(0, prevOperatorIndex+1))
                        + rsl + currExp.substring(nextOperatorIndex);
                
                System.out.println("nextOperatorIndex          =  " + nextOperatorIndex);
                System.out.println("exprInfo.expressionCurrent =  " + exprInfo.expressionCurrent);
                
                exprInfo.executionHistory.add(new ExpressionExecuteStep(
                        currExp, "", operator, leftNum, rightNum, rsl));
                
                break;
            }
        }
        System.out.println(exprInfo.expressionOriginal);
        System.out.println(exprInfo.expressionCurrent);
        return new BigDecimal(exprInfo.expressionCurrent);
    }

//    public BigDecimal execute(ScopeExpression expression) {
//        var tempExp = expression.expression;
//        val expressionInfo = ExpressionInfo.of(tempExp);
//
//        // if expression doesn't contains operator(suddenly) return expression as number.
//        if (expressionInfo.allOperatorsCount == 0) return new BigDecimal(expressionInfo.expressionOriginal);
//
//        // step 1 - find all High-Level operators
//        var chars = tempExp.toCharArray();
//        int prevOperatorIndex = -1;
//
//
//        for (int i = 0; i < chars.length; i++) {
//            val c = chars[i];
//            val operator = MathOperator.of(c);
//            if (operator == null) continue; // skip number when search operator char.
//
//            prevOperatorIndex = i;
//            // skip not our level operators
//            if (operator != MathOperator.DIVISION && operator != MULTIPLICATION) continue;
//
//            val leftNum = extractWholeNumberFromString(tempExp, prevOperatorIndex);
//            val rightNum = extractWholeNumberFromString(tempExp, i);
//            val rsl = operator.operatorDecimal.apply(leftNum, rightNum);
//            expressionInfo.allOperatorsCount--;
//        }
//        return null;
//    }
    
    /**
     * @param exp        -
     * @param startIndex start point for collection number chars.
     *                   //     * @param currOperatorIndex -
     * @return -
     */
//    private BigDecimal extractWholeNumberFromString(String exp, int prevOperatorIndex, int currOperatorIndex) {
    private BigDecimal extractWholeNumberFromString(String exp, int startIndex) {
//        if (startIndex == -1) startIndex = 0;
        
        val chars = exp.toCharArray();
        val numberChars = new StringBuilder();
        for (int i = startIndex + 1; i < chars.length; i++) {
            val c = chars[i];
            if (MathOperator.isOperatorChar(c)) break;
            if (isNumberChar(c)) numberChars.append(c);
        }
        return new BigDecimal(numberChars.toString());
    }
    
    private int countOperatorsInExpression(String exp) {
        int rsl = 0;
        for (int i = 0; i < exp.length(); i++)
            if (MathOperator.isOperatorChar(exp.charAt(i))) rsl++;
        return rsl;
    }
    
    private static boolean isNumberChar(char c) {
        return NUMBERS_CHARS.contains(c);
    }
    
    private static final Set<Character> NUMBERS_CHARS = "0123456789."
            .chars().mapToObj(c -> (char) c)
            .collect(Collectors.toUnmodifiableSet());


//    public BigDecimal execute() { return null;}
    
    @Setter
    private static class ExpressionInfo {
        private String expressionOriginal;
        private String expressionCurrent;
        private List<ExpressionExecuteStep> executionHistory = new ArrayList<>();
        
        
        //        private int highestLevelOperatorsCount = 0; // for expand version
        private int middleLevelOperatorsCount = 0;
        private int lowLevelOperatorsCount = 0;
        private int allOperatorsCount = 0;
        
        private ExpressionInfo(String expression) {
            this.expressionOriginal = expression;
        }
        
        public static ExpressionInfo of(String exp) {
            val rsl = new ExpressionInfo(exp);
            rsl.expressionCurrent = exp;
            for (int i = 0; i < exp.length(); i++) {
                val operator = MathOperator.of(exp.charAt(i));
                if (operator == null) continue;
                if (operator == MULTIPLICATION || operator == DIVISION) rsl.middleLevelOperatorsCount++;
                if (operator == PLUS || operator == MINUS) rsl.lowLevelOperatorsCount++;
            }
            rsl.allOperatorsCount = rsl.lowLevelOperatorsCount + rsl.middleLevelOperatorsCount;
            return rsl;
        }
    }
    
    @AllArgsConstructor
    private class ExpressionExecuteStep {
        private String expressionBefore;
        private String expressionAfter;
        
        private MathOperator exeOperator;
        private BigDecimal exeLeftNumber;
        private BigDecimal exeRightNumber;
        private BigDecimal exeResult;
    }
    
    /**
     * Operators priority: (1 - max priority)
     * 2 - ^  - exponent
     * 3 - /* - Multiplication/Division
     * 4 - +- - Addition/Subtraction
     */
    @AllArgsConstructor
    @Getter
    enum MathOperator {
//        EXPONENT(),
        
        MULTIPLICATION('*', 2_000, BigDecimal::multiply),
        DIVISION('/', 2_000, BigDecimal::divide),
        
        PLUS('+', 1_000, BigDecimal::add),
        MINUS('-', 1_100, BigDecimal::subtract),
        ;
        
        private final Character operatorChar;
        private final int operatorPriorityModifier;
        private final BinaryOperator<BigDecimal> operatorDecimal;
        
        private static final Map<Character, MathOperator> operators =
                Arrays.stream(MathOperator.values())
                        .collect(Collectors.toMap(MathOperator::getOperatorChar, it -> it));
        
        private static final Set<Character> operatorChars = operators.keySet();
        
        
        public static boolean isOperatorChar(char c) {
            return operatorChars.contains(c);
        }
        
        public static MathOperator of(char c) {
//            return Optional.ofNullable(operators.get(c)).orElseThrow(() -> new IllegalArgumentException("unknown operator char: " + c));
            return operators.get(c);
        }
    }
}
