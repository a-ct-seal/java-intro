package expression;

public class Subtract extends BinaryOperation {
    public Subtract(TripleExpression leftOperand, TripleExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String getOperatorToString() {
        return OperationStrings.Subtract;
    }

    @Override
    public int calculate(int x, int y) {
        return x - y;
    }

    @Override
    public double calculate(double x, double y) {
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
