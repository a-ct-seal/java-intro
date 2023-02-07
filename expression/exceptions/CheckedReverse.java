package expression.exceptions;

import expression.TripleExpression;

public class CheckedReverse extends CheckedUnaryOperation {
    private static final String MIN = Integer.toString(Integer.MIN_VALUE);
    private static final String MAX = Integer.toString(Integer.MAX_VALUE);
    public CheckedReverse(TripleExpression expr) {
        super(expr);
    }

    @Override
    public int calculate(int x) {
        String ans;
        String compareTo;
        int maxLength = 10;
        if (x < 0) {
            StringBuilder ansBuild = new StringBuilder(Integer.toString(x)).append('-').reverse();
            ansBuild.deleteCharAt(ansBuild.length() - 1);
            ans = ansBuild.toString();
            compareTo = MIN;
            maxLength++;
        } else {
            ans = new StringBuilder(Integer.toString(x)).reverse().toString();
            compareTo = MAX;
        }
        if (ans.length() < maxLength || ans.compareTo(compareTo) <= 0) {
            return Integer.parseInt(ans);
        }
        throw new OverflowException(this.toString());
    }

    @Override
    public String getOperatorToString() {
        return OperationStrings.Reverse;
    }
}
