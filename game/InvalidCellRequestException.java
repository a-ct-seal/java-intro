package game;

public class InvalidCellRequestException extends Exception {
    public InvalidCellRequestException() {
        super("InvalidCellRequestException: required cell is invalid");
    }
}
