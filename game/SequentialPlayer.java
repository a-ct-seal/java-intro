package game;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class SequentialPlayer implements Player {
    @Override
    public Move move(final Position position, final Cell turnId) {
        for (int r = 0; r < position.getM(); r++) {
            for (int c = 0; c < position.getN(); c++) {
                final Move move = new Move(r, c, turnId);
                if (position.isValid(move)) {
                    return move;
                }
            }
        }
        throw new IllegalStateException("No valid moves"); // unattainable
    }
}
