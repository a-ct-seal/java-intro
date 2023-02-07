package expression.exceptions;

import expression.TripleExpression;

public class Main {
    public static void main(String[] args) {
        TripleParser parser = new ExpressionParser();
        try {
            TripleExpression expression = parser.parse("1000000*x*x*x*x*x/(x-1)");
            System.out.println(expression.evaluate(0, 1, 1));
            System.out.println(expression.evaluate(2, 1, 1));
            System.out.println(expression.evaluate(3, 1, 1));
            System.out.println(expression.evaluate(4, 1, 1));
        } catch (Exception e) {
            throw new AssertionError(e.getMessage());
        }
    }
}
