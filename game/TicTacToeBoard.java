package game;

import java.util.*;

public class TicTacToeBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X1, 'X',
            Cell.X2, 'O',
            Cell.X3, '-',
            Cell.X4, '|',
            Cell.E, '.',
            Cell.F, 'F'
    );

    private static final List<Cell> PLAYERS_CELLS = List.of(
            Cell.X1, Cell.X2, Cell.X3, Cell.X4
    );
    private final List<Cell> playersOrder = new ArrayList<>();
    private final Cell[][] cells;
    private final int[][] forbiddenCells;
    private int emptyCellsCount;
    private Cell turn;
    private final int m, n, k;
    private final String columnFormat, firstColumnFormat, cornerFormat;
    private final int numOfPlayers;

    public TicTacToeBoard(int m, int n, int k, int[][] forbiddenCells, int numOfPlayers) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.cells = new Cell[m][n];
        this.forbiddenCells = new int[m][n];
        this.numOfPlayers = numOfPlayers;
        int mFormat = generateFormat(m);
        this.columnFormat = "%-" + generateFormat(n) + "s";
        this.firstColumnFormat = "%-" + mFormat + "s";
        this.cornerFormat = String.format("%-" + mFormat + "s", (" ").repeat(mFormat));
        for (int i = 0; i < m; i++) {
            System.arraycopy(forbiddenCells[i], 0, this.forbiddenCells[i], 0, n);
        }
        resetBoard();
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            deleteTurn();
            return Result.LOSE;
        }

        int row = move.getRow(), col = move.getColumn();
        Cell val = move.getValue();
        cells[row][col] = val;

        if (isWinPos(row, col, val, 0, 1) ||
                isWinPos(row, col, val, 1, 0) ||
                isWinPos(row, col, val, 1, 1) ||
                isWinPos(row, col, val, 1, -1)) {
            return Result.WIN;
        }

        emptyCellsCount--;
        if (emptyCellsCount == 0) {
            return Result.DRAW;
        }

        turn = playersOrder.get((playersOrder.indexOf(turn) + 1) % playersOrder.size());
        return Result.UNKNOWN;
    }

    private boolean isWinPos(int row, int col, Cell val, int rowMult, int colMult) {
        int cellsCounter = 1;
        boolean leftEnd = false, rightEnd = false;
        for (int i = 1; i < k; i++) {
            if (!leftEnd && isValidCell(row + rowMult * i, col + colMult * i)
                    && val == cells[row + rowMult * i][col + colMult * i]) {
                cellsCounter++;
            } else {
                leftEnd = true;
            }
            if (!rightEnd && isValidCell(row - rowMult * i, col - colMult * i)
                    && val == cells[row - rowMult * i][col - colMult * i]) {
                cellsCounter++;
            } else {
                rightEnd = true;
            }
        }
        return cellsCounter >= k;
    }

    @Override
    public void deleteTurn() {
        int idx = playersOrder.indexOf(turn);
        turn = playersOrder.get((idx + 1) % playersOrder.size());
        playersOrder.remove(idx);
    }

    @Override
    public Position getPosition() {
        return new PlayerBoard(this);
    }

    @Override
    public Cell getTurn() {
        return turn;
    }

    @Override
    public Cell getCell(final int r, final int c) throws InvalidCellRequestException {
        if (isValidCell(r, c)) {
            return cells[r][c];
        }
        throw new InvalidCellRequestException();
    }

    @Override
    public int getM() {
        return m;
    }

    @Override
    public int getN() {
        return n;
    }

    @Override
    public int getK() {
        return k;
    }

    @Override
    public boolean isValid(final Move move) {
        return isValidCell(move.getRow(), move.getColumn())
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == move.getValue();
    }

    private boolean isValidCell(int row, int col) {
        return 0 <= row && row < m && 0 <= col && col < n;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(cornerFormat);
        for (int c = 0; c < n; c++) {
            sb.append(String.format(columnFormat, c + 1));
        }
        for (int r = 0; r < m; r++) {
            sb.append("\n").append(String.format(firstColumnFormat, r + 1));
            for (int c = 0; c < n; c++) {
                sb.append(String.format(columnFormat, SYMBOLS.get(cells[r][c])));
            }
        }
        return sb.toString();
    }

    private static int generateFormat(int n) {
        return Integer.toString(n).length() + 1;
    }

    @Override
    public String getTurnToString(Cell turn) {
        return SYMBOLS.get(turn).toString();
    }

    @Override
    public void resetBoard() {
        emptyCellsCount = m * n;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (forbiddenCells[i][j] == 1) {
                    emptyCellsCount--;
                    cells[i][j] = Cell.F;
                } else {
                    cells[i][j] = Cell.E;
                }
            }
        }
        turn = Cell.X1;
        for (int i = 0; i < numOfPlayers; i++) {
            playersOrder.add(PLAYERS_CELLS.get(i));
        }
    }
}