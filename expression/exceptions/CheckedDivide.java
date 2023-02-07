package expression.exceptions;

import expression.TripleExpression;

public class CheckedDivide extends CheckedBinaryOperation {
    public CheckedDivide(final TripleExpression leftOperand, final TripleExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String getOperatorToString() {
        return OperationStrings.Divide;
    }

    @Override
    public int calculate(final int x, final int y) {
        if (y == 0) {
            throw new DivisionByZeroException(this.toString());
        }
        if (!Checkers.checkDiv(x, y)) {
            throw new OverflowException(this.toString());
        }
        return x / y;
    }

    @Override
    public int getOperatorPriority() {
        return OperationPriority.Divide;
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
    public boolean isPriorityCommutative() {
        return true;
    }
}
