package expression.exceptions;

import expression.TripleExpression;

public class CheckedGcd extends CheckedBinaryOperation {
    public CheckedGcd(TripleExpression leftOperand, TripleExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String getOperatorToString() {
        return OperationStrings.Gcd;
    }

    @Override
    public int calculate(int x, int y) {
        if (x == 0 && y == 0) {
            return 0;
        }
        if (x == 0 && y != Integer.MIN_VALUE) {
            return abs(y);
        }
        if (y == 0 && x != Integer.MIN_VALUE) {
            return abs(x);
        }
        return abs(gcd(abs(x), abs(y)));
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
        return OperationPriority.Gcd;
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
