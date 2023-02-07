package expression.exceptions;

import expression.TripleExpression;

public class CheckedNegate extends CheckedUnaryOperation {

    public CheckedNegate(TripleExpression expr) {
        super(expr);
    }

    @Override
    public String getOperatorToString() {
        return OperationStrings.UnaryMinus;
    }

    @Override
    public int calculate(int x) {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException(this.toString());
        }
        return -x;
    }
}
