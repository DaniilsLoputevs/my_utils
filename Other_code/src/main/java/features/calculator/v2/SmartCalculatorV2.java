package features.calculator.v2;

import features.calculator.SmartMathCalculator;
import lombok.AllArgsConstructor;
import lombok.val;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

@AllArgsConstructor
public class SmartCalculatorV2 implements SmartMathCalculator {
    private MathScopeExpressionParser parser;
    private MathScopeExpressionExecutor executor;
    
    private LinkedHashMap<String, LinkedHashMap<String, ScopeExpression>> history = new LinkedHashMap<>();
    
    public SmartCalculatorV2() {
        this.parser = new MathScopeExpressionParser();
        this.executor = new MathScopeExpressionExecutor();
    }
    
    @Override public String accept(String mathExpression) {
        mathExpression = prepareExpression(mathExpression);
        if (mathExpression.startsWith("Error:")) return mathExpression;
        
        val parsed = parser.parse(mathExpression);
        val expressions = new ArrayList<>(parsed.values());
        Collections.reverse(expressions);
        
        for (val expr : expressions) {
            expr.executionResult = executor.execute(expr);
            applyExprResultForAllDependsOn(expr, expressions);
        }
        
        history.put(mathExpression, parsed);
        return parsed.get("${exp_0}").executionResult.toString();
    }
    
    // todo - збс! с Блэк-Джеком, Шлюхами, Казино и Map<String, List<ScopeExpression>>
    private void applyExprResultForAllDependsOn(ScopeExpression executedExpr, Iterable<ScopeExpression> expressions) {
        expressions.forEach(it -> it.applyExpressionResult(executedExpr));
    }
    
    private String prepareExpression(String expr) {
        expr = clearEmptySpaces(expr);
        return checkOpenCloseBrackets(expr);
    }
    
    private String checkOpenCloseBrackets(String expr) {
        int openCount = 0;
        int closeCount = 0;
        for (val c : expr.toCharArray()) {
            if (c == '(') openCount++;
            if (c == ')') closeCount++;
        }
        return (openCount == closeCount) ? expr : "Error: found brackets (open=" + openCount + ", close=" + closeCount + ')';
    }
    
    private String clearEmptySpaces(String expression) {
        return expression.replace(" ", "");
    }
}
