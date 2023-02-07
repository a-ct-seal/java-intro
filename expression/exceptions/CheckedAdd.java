package expression.exceptions;

import expression.TripleExpression;

public class CheckedAdd extends CheckedBinaryOperation {
    public CheckedAdd(TripleExpression leftOperand, TripleExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String getOperatorToString() {
        return OperationStrings.Add;
    }

    @Override
    public int calculate(int x, int y) {
        if (Checkers.checkSum(x, y)) {
            return x + y;
        }
        throw new OverflowException(this.toString());
    }

    @Override
    public int getOperatorPriority() {
        return OperationPriority.Add;
    }

    @Override
    public boolean isCommutative() {
        return true;
    }

    @Override
    public boolean isRightCommutative() {
        return true;
    }

    @Override
    public boolean isPriorityCommutative() {
        return true;
    }
}
