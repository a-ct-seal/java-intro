package expression.parser;

import expression.*;

import java.util.List;

public class ExpressionParser extends BaseParser implements TripleParser {
    private static TripleExpression buildExpression(String exprName, TripleExpression left, TripleExpression right) {
        return switch (exprName) {
            case OperationStrings.Gcd -> new Gcd(left, right);
            case OperationStrings.Lcm -> new Lcm(left, right);
            case OperationStrings.Add -> new Add(left, right);
            case OperationStrings.Subtract -> new Subtract(left, right);
            case OperationStrings.Multiply -> new Multiply(left, right);
            case OperationStrings.Divide -> new Divide(left, right);
            default -> throw new AssertionError("Unreachable statement");
        };
    }

    @Override
    public TripleExpression parse(String expression) {
        setStream(new StringSource(expression));
        return parseComplexObject(OperationPriority.GreatestPriority);
    }

    private TripleExpression parseComplexObject(int priority) {
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

    private TripleExpression parsePrimaryObject() {
        skipWhitespaces();
        if (take('(')) {
            skipWhitespaces();
            TripleExpression res = parseComplexObject(OperationPriority.GreatestPriority);
            take(')');
            skipWhitespaces();
            return res;
        } else if (takeOperator(OperationStrings.UnaryMinus)) {
            if (Character.isDigit(ch)) {
                return getToken(true);
            }
            return new UnaryMinus(parsePrimaryObject());
        } else if (takeOperator(OperationStrings.Reverse)) {
            return new Reverse(parsePrimaryObject());
        } else {
            return getToken(false);
        }
    }

    private Operand getToken(boolean isMinus) {
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
            res = new Const(Integer.parseInt(num.toString()));
        } else {
            StringBuilder var = new StringBuilder();
            while(Character.isLetter(ch) || Character.isDigit(ch) || ch == '_' || ch == '$') {
                var.append(ch);
                take();
            }
            res = new Variable(var.toString());
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

    private void skipWhitespaces() {
        while (Character.isWhitespace(ch)) {
            take();
        }
    }
}
