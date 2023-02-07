package expression;

public class Add extends BinaryOperation {
    public Add(TripleExpression leftOperand, TripleExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String getOperatorToString() {
        return OperationStrings.Add;
    }

    @Override
    public int calculate(int x, int y) {
        return x + y;
    }

    @Override
    public double calculate(double x, double y) {
        return x + y;
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
