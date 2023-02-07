package expression.exceptions;

public class DefinitionScopeException extends ArithmeticException {
    public DefinitionScopeException(String message) {
        super("Value is not in function definition scope: " + message);
    }
}
