package expression.exceptions;

import expression.TripleExpression;

public class CheckedPow extends CheckedUnaryOperation {
    public CheckedPow(TripleExpression expr) {
        super(expr);
    }

    @Override
    public int calculate(int x) {
        int ans = 1;
        if (x < 0) {
            throw new DefinitionScopeException(this.toString());
        }
        if (x >= 10) {
            throw new OverflowException(this.toString());
        }
        for (int i = 0; i < x; i++) {
            ans *= 10;
        }
        return ans;
    }

    @Override
    public String getOperatorToString() {
        return OperationStrings.Pow;
    }
}

