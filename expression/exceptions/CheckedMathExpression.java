package expression.exceptions;

import expression.TripleExpression;

public interface CheckedMathExpression extends TripleExpression {
    int getOperatorPriority();
    // 0 for const and var
    // 1 for * \
    // 2 for + -

    boolean isCommutative();
    // true for const, var, *, +
    // false for \, -

    boolean isRightCommutative();

    boolean isPriorityCommutative();
}
