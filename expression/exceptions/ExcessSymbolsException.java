package expression.exceptions;

public class ExcessSymbolsException extends UnparsableStringException {
    public ExcessSymbolsException(String message, int idx) {
        super("Expected EOF, found: " + message, idx);
    }
}
