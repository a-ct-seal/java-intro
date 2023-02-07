package expression.exceptions;

import expression.TripleExpression;

public abstract class CheckedUnaryOperation implements CheckedMathExpression {
    private final CheckedMathExpression expr;
    public abstract int calculate(int x);
    public abstract String getOperatorToString();

    public CheckedUnaryOperation(TripleExpression expr) {
        this.expr = (CheckedMathExpression) expr;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return calculate(expr.evaluate(x, y, z));
    }

    @Override
    public int getOperatorPriority() {
        return OperationPriority.UnaryOperation;
    }

    @Override
    public boolean isCommutative() {
        return false;
    }

    @Override
    public boolean isRightCommutative() {
        return false;
    }

    @Override
    public String toString() {
        return getOperatorToString() + "(" + expr.toString() + ")";
    }

    @Override
    public String toMiniString() {
        if (expr.getOperatorPriority() <= getOperatorPriority()) {
            return getOperatorToString() + " " + expr.toMiniString();
        }
        return getOperatorToString() + "(" + expr.toMiniString() + ")";
    }

    @Override
    public boolean isPriorityCommutative() {
        return true;
    }
}
