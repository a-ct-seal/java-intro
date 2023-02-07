package expression;

public class Multiply extends BinaryOperation {
    public Multiply(TripleExpression leftOperand, TripleExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String getOperatorToString() {
        return OperationStrings.Multiply;
    }

    @Override
    public int calculate(int x, int y) {
        return x * y;
    }

    @Override
    public double calculate(double x, double y) {
        return x * y;
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
