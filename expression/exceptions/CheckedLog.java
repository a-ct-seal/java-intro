package expression.exceptions;

import expression.TripleExpression;

public class CheckedLog extends CheckedUnaryOperation {
    public CheckedLog(TripleExpression expr) {
        super(expr);
    }

    @Override
    public int calculate(int x) {
        if (x < 1) {
            throw new DefinitionScopeException(this.toString());
        }
        return Integer.toString(x).length() - 1;
    }

    @Override
    public String getOperatorToString() {
        return OperationStrings.Log;
    }
}
