package features.calculator.v2;

import java.util.ArrayList;
import java.util.List;

public class ScopeExpression {
    String name;
    String expression; // (3*4-5+$epx2 + $epx3)
    List<ScopeExpression> executionDependsOnExpressions = new ArrayList<>(); // [$epx2, $epx3]
    
    
    public ScopeExpression(String name, String expression) {
        this.name = name;
        this.expression = expression;
    }
    
    @Override public String toString() {
        return expression + " --- " + name;
    }
}
