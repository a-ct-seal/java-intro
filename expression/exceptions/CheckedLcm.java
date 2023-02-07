package expression.exceptions;

import expression.TripleExpression;

public class CheckedLcm extends CheckedBinaryOperation {
    public CheckedLcm(TripleExpression leftOperand, TripleExpression rightOperand) {
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
        int a = x / abs(gcd(abs(x), abs(y)));
        if (Checkers.checkMul(a, y)) {
            return a * y;
        }
        throw new OverflowException(this.toString());
    }

    private static int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }

    private static int abs(int n) {
        if (n < 0) {
            return -n;
        }
        return n;
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
