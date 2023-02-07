package expression.exceptions;

import expression.TripleExpression;

import java.util.Objects;

public abstract class CheckedBinaryOperation implements CheckedMathExpression {
    protected final CheckedMathExpression leftOperand;
    protected final CheckedMathExpression rightOperand;

    public CheckedBinaryOperation(TripleExpression leftOperand, TripleExpression rightOperand) {
        this.leftOperand = (CheckedMathExpression) leftOperand;
        this.rightOperand = (CheckedMathExpression) rightOperand;
    }

    public abstract String getOperatorToString();

    public abstract int calculate(int x, int y);


    @Override
    public int evaluate(int x, int y, int z) {
        return calculate(leftOperand.evaluate(x, y, z), rightOperand.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return "(" + leftOperand.toString() +
                " " + getOperatorToString() +
                " " + rightOperand.toString() + ")";
    }

    @Override
    public String toMiniString() {
        StringBuilder res = new StringBuilder();
        boolean leftCondition = leftOperand.getOperatorPriority() > getOperatorPriority();
        appendOperatorToStringBuilder(res, leftCondition, leftOperand);
        res.append(" ").append(getOperatorToString()).append(" ");
        boolean rightCondition = rightOperand.getOperatorPriority() > getOperatorPriority() ||
                rightOperand.getOperatorPriority() == getOperatorPriority() &&
                        (!isCommutative() || !rightOperand.isRightCommutative()) ||
                (rightOperand.getOperatorPriority() == getOperatorPriority() &&
                        !rightOperand.isPriorityCommutative() &&
                        this.getClass() != rightOperand.getClass());
        appendOperatorToStringBuilder(res, rightCondition, rightOperand);
        return res.toString();
    }

    private static void appendOperatorToStringBuilder(StringBuilder sb, boolean condition, CheckedMathExpression operator) {
        if (condition) {
            sb.append("(").append(operator.toMiniString()).append(")");
        } else {
            sb.append(operator.toMiniString());
        }
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj.getClass() == this.getClass() &&
                Objects.equals(((CheckedBinaryOperation) obj).leftOperand, this.leftOperand) &&
                Objects.equals(((CheckedBinaryOperation) obj).rightOperand, this.rightOperand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftOperand, rightOperand, getClass());
    }
}
