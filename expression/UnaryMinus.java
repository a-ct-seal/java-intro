package expression;

public class UnaryMinus extends UnaryOperation {

    public UnaryMinus(TripleExpression expr) {
        super(expr);
    }

    @Override
    public String getOperatorToString() {
        return OperationStrings.UnaryMinus;
    }

    @Override
    public int calculate(int x) {
        return -x;
    }

    @Override
    public double calculate(double x) {
        return -x;
    }
}
