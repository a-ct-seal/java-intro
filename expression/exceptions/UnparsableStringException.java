package expression.exceptions;

public class UnparsableStringException extends RuntimeException {
    public UnparsableStringException(String message, int idx) {
        super(message + " in index " + idx);
    }
}
