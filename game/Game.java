package game;

import java.util.List;
import java.util.ArrayList;


public class Game extends PlayableObject {
    private final List<Integer> playersID;
    int numOfLivingPlayers;

    public Game(final boolean log, List<Player> players) {
        super(log, players);
        numOfLivingPlayers = numOfPlayers;
        playersID = new ArrayList<>(numOfPlayers);
        for (int i = 0; i < numOfPlayers; i++) {
            playersID.add(i);
        }
    }

    //returns id of winner or 0 for draw
    @Override
    public int play(final Board board) {
        while (true) {
            for (int i = 0; i < numOfPlayers; i++) {
                if (playersID.get(i) == -1) {
                    continue;
                }
                final int result = move(board, players.get(i), i);
                if (result != -1) {
                    return result;
                }
            }
        }
    }

    private int move(final Board board, final Player player, final int playerId) {
        Result result;
        try {
            final Move move = player.move(board.getPosition(), board.getTurn());
            log("Player " + (playerId + 1) + " move: " + move);
            result = board.makeMove(move);
        } catch (Exception e) {
            log("Player " + (playerId + 1) + " throws exception");
            board.deleteTurn();
            result = Result.LOSE;
        }
        log("Position:, %s\n", board);
        if (result == Result.WIN) {
            log("Player " + (playerId + 1) + " won");
            System.out.println("Result\n" + board);
            return playerId + 1;
        } else if (result == Result.LOSE) {
            log("Player " + (playerId + 1) + " lose");
            playersID.set(playerId, -1);
            numOfLivingPlayers--;
            if (numOfLivingPlayers == 1) {
                for (int j = 0; j < numOfPlayers; j++) {
                    if (playersID.get(j) != -1) {
                        log("Player " + (j + 1) + " won");
                        System.out.println("Result\n" + board);
                        return j + 1;
                    }
                }
            }
        } else if (result == Result.DRAW) {
            log("Draw");
            System.out.println("Result\n" + board);
            return 0;
        }
        return -1;
    }
}
