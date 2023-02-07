package game;

import java.io.PrintStream;
import java.util.Scanner;


public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    public HumanPlayer(final Scanner in) {
        this(System.out, in);
    }

    @Override
    public Move move(final Position position, final Cell turnId) {
        out.println("Position");
        out.println(position);
        out.println(position.getTurnToString(turnId) + "'s move");
        while (true) {
            out.println("Enter row and column");

            if (in.hasNext() && !in.hasNextInt()) {
                invalidInputWarning();
                in.nextLine();
                continue;
            }
            int row = in.nextInt() - 1;
            if (in.hasNext() && !in.hasNextInt()) {
                invalidInputWarning();
                in.nextLine();
                continue;
            }
            int col = in.nextInt() - 1;
            if (!in.nextLine().isEmpty()) {
                invalidInputWarning();
                continue;
            }

            final Move move = new Move(row, col, turnId);
            if (position.isValid(move)) {
                return move;
            }
            invalidMoveWarning(move.toString());
        }
    }

    private void invalidInputWarning() {
        out.println("Your input is invalid, try again");
    }

    private void invalidMoveWarning(String move) {
        out.println("Move " + move + " is invalid");
    }
}
