package expression.exceptions;

public class NoOpeningParenthesisException extends UnparsableStringException {
    public NoOpeningParenthesisException(int idx) {
        super("Symbol '(' not found", idx);
    }
}
