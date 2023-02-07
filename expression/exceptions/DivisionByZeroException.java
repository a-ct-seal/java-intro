package expression.exceptions;

public class DivisionByZeroException extends ArithmeticException {
    public DivisionByZeroException(String message) {
        super("Division by zero: " + message);
    }
}
