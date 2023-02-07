package expression.exceptions;

public class ArgumentParseException extends UnparsableStringException {

    public ArgumentParseException(String message, int idx) {
        super("Can not parse as argument: \"" + message + "\"", idx);
    }
}
