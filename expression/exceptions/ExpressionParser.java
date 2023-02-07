package expression.exceptions;

import expression.Const;
import expression.Operand;
import expression.TripleExpression;
import expression.Variable;

import java.util.List;
import java.util.Set;

public class ExpressionParser extends BaseParser implements TripleParser {
    private static final Set<Character> allowedAfterNumSymbols = Set.of(
            '+', '-', '*', '/', END, ')'
    );
    private static final Set<String> allowedVariableNames = Set.of("x", "y", "z");

    private static TripleExpression buildExpression(String exprName, TripleExpression left, TripleExpression right) {
        return switch (exprName) {
            case OperationStrings.Gcd -> new CheckedGcd(left, right);
            case OperationStrings.Lcm -> new CheckedLcm(left, right);
            case OperationStrings.Add -> new CheckedAdd(left, right);
            case OperationStrings.Subtract -> new CheckedSubtract(left, right);
            case OperationStrings.Multiply -> new CheckedMultiply(left, right);
            case OperationStrings.Divide -> new CheckedDivide(left, right);
            default -> throw new AssertionError("Unreachable statement");
        };
    }

    @Override
    public TripleExpression parse(String expression) {
        setStream(new StringSource(expression));
        return parseExpression();
    }

    TripleExpression parseExpression() {
        TripleExpression res = parseComplexObject(OperationPriority.GreatestPriority);
        skipWhitespaces();
        if (ch == ')') {
            throw new NoOpeningParenthesisException(source.getPosition());
        }
        if (ch != END) {
            StringBuilder excessSymbols = new StringBuilder();
            while (ch != END) {
                excessSymbols.append(ch);
                take();
            }
            throw new ExcessSymbolsException(excessSymbols.toString(), source.getPosition());
        }
        return res;
    }

    TripleExpression parseComplexObject(int priority) throws UnparsableStringException {
        if (priority == 0) {
            return parsePrimaryObject();
        }
        TripleExpression left = parseComplexObject(priority - 1);
        while (true) {
            List<String> possibleOperations = OperationPriority.priorities.get(priority);
            boolean operationFound = false;
            for (String possibleOperation : possibleOperations) {
                if (takeOperator(possibleOperation)) {
                    operationFound = true;
                    TripleExpression right = parseComplexObject(priority - 1);
                    left = buildExpression(possibleOperation, left, right);
                }
            }
            if (!operationFound) {
                return left;
            }
        }
    }

    TripleExpression parsePrimaryObject() throws UnparsableStringException {
        skipWhitespaces();
        if (take('(')) {
            skipWhitespaces();
            TripleExpression res = parseComplexObject(OperationPriority.GreatestPriority);
            if (!take(')')) {
                throw new NoClosingParenthesisException(source.getPosition());
            }
            skipWhitespaces();
            return res;
        } else if (takeOperator(OperationStrings.UnaryMinus)) {
            if (Character.isDigit(ch)) {
                return getToken(true);
            }
            return new CheckedNegate(parsePrimaryObject());
        } else if (takeOperator(OperationStrings.Log)) {
            return new CheckedLog(parsePrimaryObject());
        } else if (takeOperator(OperationStrings.Pow)) {
            return new CheckedPow(parsePrimaryObject());
        } else if (takeOperator(OperationStrings.Reverse)) {
            return new CheckedReverse(parsePrimaryObject());
        } else {
            return getToken(false);
        }
    }

    Operand getToken(boolean isMinus) {
        skipWhitespaces();
        Operand res;
        if (Character.isDigit(ch)) {
            StringBuilder num = new StringBuilder();
            if (isMinus) {
                num.append('-');
            }
            while (Character.isDigit(ch)) {
                num.append(ch);
                take();
            }
            if (!Character.isWhitespace(ch) && !allowedAfterNumSymbols.contains(ch)) {
                while (!Character.isWhitespace(ch) && ch != END) {
                    num.append(ch);
                    take();
                }
                throw new NumberParseException(num.toString(), source.getPosition());
            }
            try {
                res = new Const(Integer.parseInt(num.toString()));
            } catch (NumberFormatException e) {
                throw new OverflowException(num.toString());
            }
        } else if (ch == END) {
            throw new ArgumentParseException("EOF", source.getPosition());
        } else {
            StringBuilder var = new StringBuilder();
            while(Character.isLetter(ch) || Character.isDigit(ch) || ch == '_' || ch == '$') {
                var.append(ch);
                take();
            }
            String varToken = var.toString();
            if (!allowedVariableNames.contains(varToken)) {
                if (varToken.equals("")) {
                    skipWhitespaces();
                    StringBuilder token = new StringBuilder();
                    while(!Character.isWhitespace(ch) && ch != END) {
                        token.append(ch);
                        take();
                    }
                    throw new ArgumentParseException(token.toString(), source.getPosition());
                }
                throw new ArgumentParseException(varToken, source.getPosition());
            }
            res = new Variable(varToken);
        }
        skipWhitespaces();
        return res;
    }

    protected boolean takeOperator(final String expected) {
        int oldIdx = source.getPosition();
        char oldCh = ch;
        int idx = 0;
        while (idx < expected.length()) {
            if (take(expected.charAt(idx))) {
                idx++;
            } else {
                source.setPosition(oldIdx);
                ch = oldCh;
                return false;
            }
        }
        boolean isSymbolOperation = !(Character.isDigit(expected.charAt(expected.length() - 1)) ||
                Character.isLetter(expected.charAt(expected.length() - 1)));
        if (!(Character.isWhitespace(ch) || ch == '(' || ch == '-' ||
                (isSymbolOperation && (Character.isLetter(ch) || Character.isDigit(ch)))
        )) {
            source.setPosition(oldIdx);
            ch = oldCh;
            return false;
        }
        return true;
    }

    void skipWhitespaces() {
        while (Character.isWhitespace(ch)) {
            take();
        }
    }
}
