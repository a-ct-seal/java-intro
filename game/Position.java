package game;

public interface Position {
    boolean isValid(Move move);

    Cell getCell(int r, int c) throws InvalidCellRequestException;

    int getM();

    int getN();

    int getK();

    String getTurnToString(Cell turn);
}
