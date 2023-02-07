package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tournament extends PlayableObject {
    private final int[][] tournamentTable;

    public Tournament(final boolean log, List<Player> players) {
        super(log, players);
        tournamentTable = new int[numOfPlayers][numOfPlayers];
        for (int i = 0; i < numOfPlayers; i++) {
            Arrays.fill(tournamentTable[i], -1);
        }
    }

    // always returns 0 cause where is no way to determine a winner
    @Override
    public int play(Board board) {
        for (int i = 0; i < numOfPlayers; i++) {
            for (int j = 0; j < numOfPlayers; j++) {
                if (i == j) {
                    continue;
                }
                final List<Player> round = new ArrayList<>(2);
                round.add(players.get(i));
                round.add(players.get(j));
                System.out.println("Game between " + (i + 1) + " and " + (j + 1));
                final Game game = new Game(log, round);
                int result = game.play(board);
                board.resetBoard();
                if (result == 1) {
                    tournamentTable[i][j] = 3;
                    System.out.println("Player " + (i + 1) + " won");
                } else if (result == 0) {
                    tournamentTable[i][j] = 1;
                    System.out.println("Draw");
                } else {
                    tournamentTable[i][j] = 0;
                    System.out.println("Player " + (j + 1) + " won");
                }
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tournament\n  ");
        for (int i = 0; i < numOfPlayers; i++) {
            sb.append(i + 1);
        }
        for (int r = 0; r < numOfPlayers; r++) {
            sb.append("\n").append(r + 1).append(" ");
            for (int c = 0; c < numOfPlayers; c++) {
                if (tournamentTable[r][c] == -1) {
                    sb.append('.');
                } else {
                    sb.append(tournamentTable[r][c]);
                }
            }
        }
        return sb.toString();
    }

    public List<Integer> winners() {
        int[] playersScore = new int[numOfPlayers];
        Arrays.fill(playersScore, 0);
        for (int i = 0; i < numOfPlayers; i++) {
            for (int j = 0; j < numOfPlayers; j++) {
                if (tournamentTable[i][j] == 1) {
                    playersScore[i]++;
                    playersScore[j]++;
                } else if (tournamentTable[i][j] == 0) {
                    playersScore[j] += 3;
                } else {
                    playersScore[i] += 3;
                }
            }
        }
        List<Integer> winners = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < numOfPlayers; i++) {
            if (playersScore[i] == max) {
                winners.add(i);
            } else if (playersScore[i] > max) {
                winners.clear();
                max = playersScore[i];
                winners.add(i);
            }
        }
        return winners;
    }
}
