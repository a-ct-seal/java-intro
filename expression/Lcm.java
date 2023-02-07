package expression;

public class Lcm extends BinaryOperation {
    public Lcm(TripleExpression leftOperand, TripleExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String getOperatorToString() {
        return OperationStrings.Lcm;
    }

    @Override
    public int calculate(int x, int y) {
        if (x == 0 || y == 0) {
            return 0;
        }
        return x / abs(gcd(abs(x), abs(y))) * y;
    }

    private int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }

    private int abs(int n) {
        if (n < 0) {
            return -n;
        }
        return n;
    }

    @Override
    public double calculate(double x, double y) {
        return 0;
    }

    @Override
    public int getOperatorPriority() {
        return OperationPriority.Lcm;
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
        return false;
    }
}
