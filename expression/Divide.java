package expression;

public class Divide extends BinaryOperation {
    public Divide(final TripleExpression leftOperand, final TripleExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String getOperatorToString() {
        return OperationStrings.Divide;
    }

    @Override
    public int calculate(final int x, final int y) {
        return x / y;
    }

    @Override
    public double calculate(final double x, final double y) {
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
