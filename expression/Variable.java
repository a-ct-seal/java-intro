package expression;

import java.util.Objects;

public class Variable extends Operand {
    private final String variable;

    public Variable(String variable) {
        this.variable = variable;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Variable that && Objects.equals(variable, that.variable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variable);
    }

    @Override
    public String toString() {
        return variable;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        if (variable.equals("x")) {
            return x;
        } else if (variable.equals("y")) {
            return y;
        } else {
            return z;
        }
    }
}
