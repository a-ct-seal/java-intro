package expression.exceptions;

public class OverflowException extends ArithmeticException {
    public OverflowException(String s) {
        super("Overflow: " + s);
    }
}
