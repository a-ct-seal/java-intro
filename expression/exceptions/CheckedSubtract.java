package expression.exceptions;

import expression.OperationPriority;
import expression.OperationStrings;
import expression.TripleExpression;

public class CheckedSubtract extends CheckedBinaryOperation {
    public CheckedSubtract(TripleExpression leftOperand, TripleExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String getOperatorToString() {
        return OperationStrings.Subtract;
    }

    @Override
    public int calculate(int x, int y) {
        if (!Checkers.checkSub(x, y)) {
            throw new OverflowException(this.toString());
        }
        return x - y;
    }

    @Override
    public int getOperatorPriority() {
        return OperationPriority.Subtract;
    }

    @Override
    public boolean isCommutative() {
        return false;
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
