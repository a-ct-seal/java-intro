package expression.exceptions;

public class NumberParseException extends UnparsableStringException {

    public NumberParseException(String message, int idx) {
        super("Can not parse as number: " + message, idx);
    }
}
