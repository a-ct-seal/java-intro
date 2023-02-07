package expression.exceptions;

import expression.TripleExpression;

public class CheckedMultiply extends CheckedBinaryOperation {
    public CheckedMultiply(TripleExpression leftOperand, TripleExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String getOperatorToString() {
        return OperationStrings.Multiply;
    }

    @Override
    public int calculate(int x, int y) {
        if (Checkers.checkMul(x, y)) {
            return x * y;
        }
        throw new OverflowException(this.toString());
    }

    @Override
    public int getOperatorPriority() {
        return OperationPriority.Multiply;
    }

    @Override
    public boolean isCommutative() {
        return true;
    }

    @Override
    public boolean isPriorityCommutative() {
        return true;
    }

    @Override
    public boolean isRightCommutative() {
        return true;
    }
}
