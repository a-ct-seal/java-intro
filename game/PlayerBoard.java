package game;

public class PlayerBoard implements Position {
    private final TicTacToeBoard board;

    public PlayerBoard(TicTacToeBoard board) {
        this.board = board;
    }

    @Override
    public boolean isValid(Move move) {
        return board.isValid(move);
    }

    @Override
    public Cell getCell(int r, int c) throws InvalidCellRequestException {
        return board.getCell(r, c);
    }

    @Override
    public int getM() {
        return board.getM();
    }

    @Override
    public int getN() {
        return board.getN();
    }

    @Override
    public int getK() {
        return board.getK();
    }

    @Override
    public String toString() {
        return board.toString();
    }

    @Override
    public String getTurnToString(Cell turn) {
        return board.getTurnToString(turn);
    }
}
