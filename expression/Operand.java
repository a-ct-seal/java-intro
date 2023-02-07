package expression;

import expression.exceptions.CheckedMathExpression;

public abstract class Operand implements MathExpression, CheckedMathExpression {
    @Override
    public boolean isPriorityCommutative() {
        return true;
    }
    @Override
    public abstract String toString();
    @Override
    public String toMiniString() {
        return toString();
    }

    @Override
    public int getOperatorPriority() {
        return OperationPriority.Operand;
    }

    @Override
    public boolean isCommutative() {
        return true;
    }

    @Override
    public boolean isRightCommutative() {
        return true;
    }
}
