package expression.exceptions;

public class Checkers {
    public static boolean checkMul(int x, int y) {
        return !(x == -1 && y == Integer.MIN_VALUE) && ((x == 0) || (x * y / x) == y);
    }

    public static boolean checkSum(int x, int y) {
        return (x < 0 || Integer.MAX_VALUE - x >= y) && (x > 0 || Integer.MIN_VALUE - x <= y);
    }

    public static boolean checkDiv(int x, int y) {
        return x != Integer.MIN_VALUE || y != -1;
    }

    public static boolean checkSub(int x, int y) {
        return (y != Integer.MIN_VALUE && checkSum(x, -y)) ||
                (y == Integer.MIN_VALUE && x <= -1);
    }
}
