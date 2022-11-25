package features.calculator.v2;

import features.calculator.SmartMathCalculator;
import lombok.AllArgsConstructor;
import lombok.val;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
public class SmartCalculatorV2 implements SmartMathCalculator {
    private MathScopeExpressionParser parser;
    private MathScopeExpressionExecutor executor;
    
    public SmartCalculatorV2() {
        this.parser = new MathScopeExpressionParser();
        this.executor = new MathScopeExpressionExecutor();
    }
    
    @Override public String accept(String mathExpression) {
        mathExpression = clearEmptySpaces(mathExpression);
        val parsed = parser.parse(mathExpression);
        val expressions = new ArrayList<>(parsed.values());
        Collections.reverse(expressions);
        
        for (val expr : expressions) {
            expr.executionResult = executor.execute(expr);
            applyExprResultForAllDependsOn(expr, expressions);
        }
        
        return parsed.get("${exp_0}").executionResult.toString();
    }
    // todo - збс! с Блэк-Джеком, Шлюхами, Казино и Map<String, List<ScopeExpression>>
    private void applyExprResultForAllDependsOn(ScopeExpression executedExpr, Iterable<ScopeExpression> expressions) {
        expressions.forEach(it -> it.applyExpressionResult(executedExpr));
    }
    
    private String clearEmptySpaces(String expression) {
        return  expression.replace(" ", "");
    }
}
