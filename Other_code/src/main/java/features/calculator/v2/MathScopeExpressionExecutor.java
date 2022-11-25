package features.calculator.v2;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static features.calculator.v2.MathScopeExpressionExecutor.MathOperator.*;

// "10-5+3*10/2"
// "10-5+3*5"
// "10-5+15"
// "5+15"
// todo - парсить и негативные числа.
public class MathScopeExpressionExecutor {
    public static void main(String[] args) {
        val src = "2/2-5+3*10/4-2";
//        val src = "-4+7.5-2";
//        val src = "3.5-2";
        System.out.println("src = " + src);
        val executor = new MathScopeExpressionExecutor();
        executor.execute(new ScopeExpression("${expr_00}", src));
    }
    
    public BigDecimal execute(ScopeExpression expression) {
        val exprInfo = ExprExeInfo.of(expression.expression);
        
        // if expression doesn't contains operator(suddenly) return expression as number.
        if (exprInfo.getAllLevelsOperatorCount() == 0) return new BigDecimal(exprInfo.expressionCurrent);
        
        // step 1 - find all middle-Level operators
//        executeAllOperatorsOnLevel(exprInfo, ExecutionOperatorLevel.MIDDLE);
//        executeAllOperatorsOnLevel(exprInfo, ExecutionOperatorLevel.LOW);
        executeAllOperatorsOnLevelV2(exprInfo, ExecutionOperatorLevel.MIDDLE);
        executeAllOperatorsOnLevelV2(exprInfo, ExecutionOperatorLevel.LOW);
        
        System.out.println("exprInfo.expressionOriginal = " + exprInfo.expressionOriginal);
        System.out.println("exprInfo.expressionCurrent  = " + exprInfo.expressionCurrent);
        return new BigDecimal(exprInfo.expressionCurrent);
    }

//    private void executeAllOperatorsOnLevel(ExprExeInfo exprInfo, ExecutionOperatorLevel level) {
//        while (level.operatorCounterGetter.apply(exprInfo) != 0) {
//            var currExp = exprInfo.expressionCurrent;
//            int prevOperatorIndex = -1;
//            boolean isPrevOperatorFound = false;
//
//            for (int i = 0; i < currExp.length(); i++) {
//                val c = currExp.charAt(i);
//                val operator = MathOperator.of(c);
//
//                /* skip number when search operator char. */
//                if (operator == null) continue;
//
//                /* skip not our level operators but remember that we found it. */
//                if (!level.operators.contains(operator)) {
//                    prevOperatorIndex = i;
//                    isPrevOperatorFound = true;
//                    continue;
//                }
//                /* if we find our operator, we don't need a next iterations of for loop, just do work and break. */
//
//                // 5-5+3*10/4-2"
//                val leftNum = extractWholeNumberFromString(currExp, prevOperatorIndex);
//                val rightNum = extractWholeNumberFromString(currExp, i);
//                val rsl = operator.operatorDecimal.apply(leftNum, rightNum);
//
//                level.operatorCounterDecrementFunc.accept(exprInfo);
//
//                val nextOperatorIndex = (isPrevOperatorFound ? prevOperatorIndex + 1 : 0)
//                        + 1 // +1 for operator char offset
//                        + (leftNum.toString().length())
//                        + (rightNum.toString().length());
//
//                exprInfo.expressionCurrent = (!isPrevOperatorFound ? "" : currExp.substring(0, prevOperatorIndex + 1))
//                        + rsl + currExp.substring(nextOperatorIndex);
//
//                System.out.println("nextOperatorIndex          =  " + nextOperatorIndex);
//                System.out.println("exprInfo.expressionCurrent =  " + exprInfo.expressionCurrent);
//
//                exprInfo.executionHistory.add(new ExpressionExecuteStep(
//                        currExp, exprInfo.expressionCurrent, operator, leftNum, rightNum, rsl));
//
//                break;
//            }
//        }
//    }
    
    private void executeAllOperatorsOnLevelV2(ExprExeInfo exprInfo, ExecutionOperatorLevel level) {
        while (level.operatorCounterGetter.apply(exprInfo) != 0) {
            var currExpr = exprInfo.expressionCurrent;
            
            val indexes = findOperatorIndexesForExpr(currExpr, level.operators);
            val operator = MathOperator.of(currExpr.charAt(indexes.currOperatorIndex));
            val leftNum = parseNumber(currExpr, indexes.prevOperatorIndex, indexes.currOperatorIndex);
            val rightNum = parseNumber(currExpr, indexes.currOperatorIndex + 1, indexes.nextOperatorIndex);
            val rsl = operator.func.apply(leftNum, rightNum);
            
            level.operatorCounterDecrementFunc.accept(exprInfo);
            // escape case: prev == 1 -- it's
            val prev = indexes.prevOperatorIndex == 0 ? 0 : indexes.prevOperatorIndex + 1;
//            exprInfo.expressionCurrent = (!isPrevOperatorFound ? "" : currExpr.substring(0, prevOperatorIndex + 1))
//                    + rsl + currExpr.substring(nextOperatorIndex);
            exprInfo.expressionCurrent = currExpr.substring(0, prev) + rsl + currExpr.substring(indexes.nextOperatorIndex);
            
            exprInfo.executionHistory.add(new ExpressionExecuteStep(
                    currExpr, exprInfo.expressionCurrent, operator, leftNum, rightNum, rsl));
            
            System.out.println("currExpr = " + currExpr);
            System.out.println("leftNum  = " + leftNum);
            System.out.println("rightNum = " + rightNum);
            System.out.println("exprInfo.expressionCurrent =  " + exprInfo.expressionCurrent);
            
        }
    }
    
    /**
     * @param expr       -
     * @param startIndex start point for collection number chars.
     * @return -
     */
    // todo - rewrite to findOperatorsIndex(prev, our, next operators)
    private BigDecimal extractWholeNumberFromString(String expr, int startIndex) {
        val numberChars = new StringBuilder();
        for (int i = startIndex + 1; i < expr.length(); i++) {
            val c = expr.charAt(i);
            // todo - parse negative nums
            val operator = MathOperator.of(c);
            if (operator != null)
                if (operator == MINUS) numberChars.append(c);
                else break;
            if (isNumberChar(c)) numberChars.append(c);
        }
        return new BigDecimal(numberChars.toString());
    }
    
    // todo tests for many cases
    private OperatorIndexes findOperatorIndexesForExpr(String expr, Set<MathScopeExpressionExecutor.MathOperator> operators) {
        int prev = 0;
        int curr = -1;
        int next = expr.length();
        for (int i = 0; i < expr.length(); i++) {
            val c = expr.charAt(i);
            
            val currCharOperator = MathScopeExpressionExecutor.MathOperator.of(c);
            if (currCharOperator == null) continue;
            
            char prevChar = (i == 0) ? 0 : expr.charAt(i - 1);
            // skip Minus operator char after other operator - case: negative number
            if (currCharOperator == MINUS && (MathScopeExpressionExecutor.MathOperator.isOperatorChar(prevChar) || prevChar == 0))
                continue;
            
            if (curr == -1 && operators.contains(currCharOperator)) {
                curr = i;
                continue;
            }
            // case: searching for prevOperatorIndex
            if (curr == -1) {
                prev = i;
            } else {
                next = i;
                break;
            }
        }
        return new OperatorIndexes(prev, curr, next);
    }
    
    private BigDecimal parseNumber(String expr, int startIndex, int endIndex) {
        return new BigDecimal(expr.substring(startIndex, endIndex));
    }
    
    
    private static boolean isNumberChar(char c) {
        return NUMBERS_CHARS.contains(c);
    }
    
    private static final Set<Character> NUMBERS_CHARS = "0123456789."
            .chars().mapToObj(c -> (char) c)
            .collect(Collectors.toUnmodifiableSet());
    
    
    @Getter
    private static class ExprExeInfo {
        private final String expressionOriginal;
        private String expressionCurrent;
        private final List<ExpressionExecuteStep> executionHistory = new ArrayList<>();
        
        //        private int highestLevelOperatorsCount = 0; // for expand version
        private int middleLevelOperatorsCount = 0;
        private int lowLevelOperatorsCount = 0;
        
        private ExprExeInfo(String expression) {
            this.expressionOriginal = expression;
        }
        
        public int decrementMiddleLevelOperatorsCount() {return this.middleLevelOperatorsCount--;}
        
        public int decrementLowLevelOperatorsCount() {return this.lowLevelOperatorsCount--;}
        
        public int getAllLevelsOperatorCount() { return middleLevelOperatorsCount + lowLevelOperatorsCount;}
        
        public static ExprExeInfo of(String exp) {
            val rsl = new ExprExeInfo(exp);
            rsl.expressionCurrent = exp;
            // todo - collect prev char and if curr == MINUS >> prev is Operator?
            //  -> true : skip Minus operator(because it's a negative num)
            //  -> false : it's Minus operator
            for (int i = 0; i < exp.length(); i++) {
                val operator = MathOperator.of(exp.charAt(i));
                if (operator == null) continue;
                if (operator == MULTIPLICATION || operator == DIVISION) rsl.middleLevelOperatorsCount++;
                if (operator == PLUS || operator == MINUS) rsl.lowLevelOperatorsCount++;
            }
            return rsl;
        }
    }
    
    @RequiredArgsConstructor
    private static class ExpressionExecuteStep {
        private final String expressionBefore;
        private final String expressionAfter;
        
        private final MathOperator exeOperator;
        private final BigDecimal exeLeftNumber;
        private final BigDecimal exeRightNumber;
        private final BigDecimal exeResult;
    }
    
    @RequiredArgsConstructor
    private enum ExecutionOperatorLevel {
        
        //        HIGH(),
        MIDDLE(Set.of(MULTIPLICATION, DIVISION), ExprExeInfo::getMiddleLevelOperatorsCount, ExprExeInfo::decrementMiddleLevelOperatorsCount),
        LOW(Set.of(PLUS, MINUS), ExprExeInfo::getLowLevelOperatorsCount, ExprExeInfo::decrementLowLevelOperatorsCount);
        
        private final Set<MathOperator> operators;
        private final Function<ExprExeInfo, Integer> operatorCounterGetter;
        private final Consumer<ExprExeInfo> operatorCounterDecrementFunc;
    }
    
    /**
     * Operators priority: (1 - max priority)
     * 2 - ^  - exponent
     * 3 - /* - Multiplication/Division
     * 4 - +- - Addition/Subtraction
     */
    @RequiredArgsConstructor
    @Getter
    enum MathOperator {
//        EXPONENT(),
        
        MULTIPLICATION('*', BigDecimal::multiply),
        DIVISION('/', BigDecimal::divide),
        
        PLUS('+', BigDecimal::add),
        MINUS('-', BigDecimal::subtract),
        ;
        
        
        private final Character operatorChar;
        private final BinaryOperator<BigDecimal> func;
        
        
        private static final Map<Character, MathOperator> operators =
                Arrays.stream(MathOperator.values())
                        .collect(Collectors.toMap(MathOperator::getOperatorChar, it -> it));
        
        private static final Set<Character> operatorChars = operators.keySet();
        
        
        public static boolean isOperatorChar(char c) {
            return operatorChars.contains(c);
        }
        
        public static MathOperator of(char c) {
            return operators.get(c);
        }
    }
    
    @ToString
    @RequiredArgsConstructor
    static class OperatorIndexes {
        /** If not found, default = 0 */
        private final int prevOperatorIndex;
        private final int currOperatorIndex;
        /** If not found, default = expression length */
        private final int nextOperatorIndex;
    }
}
