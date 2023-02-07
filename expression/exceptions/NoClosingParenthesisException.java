package expression.exceptions;

public class NoClosingParenthesisException extends UnparsableStringException {
    public NoClosingParenthesisException(int idx) {
        super("Symbol ')' not found", idx);
    }
}
