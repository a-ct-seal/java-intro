package expression;

public class Reverse extends UnaryOperation {
    public Reverse(TripleExpression expr) {
        super(expr);
    }

    @Override
    public int calculate(int x) {
        int ans = 0;
        while (x != 0) {
            ans *= 10;
            ans += x % 10;
            x /= 10;
        }
        return ans;
    }

    @Override
    public double calculate(double x) {
        return 0;
    }

    @Override
    public String getOperatorToString() {
        return OperationStrings.Reverse;
    }
}
