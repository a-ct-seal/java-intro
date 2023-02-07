package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.min;


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter m:");
        int m = enterInt(in);
        System.out.println("Enter n:");
        int n = enterInt(in);
        System.out.println("Enter k:");
        int k = enterInt(in);

        System.out.println("Choose playing mode:\n1\tGame\n2\tTournament");
        int mode = enterInt(in);
        while (mode != 1 && mode != 2) {
            invalidInputWarning();
            mode = enterInt(in);
        }

        System.out.println("Choose board mode:\n1\tNo Forbidden cells\n2\tForbidden cells on main diagonal");
        int boardMode = enterInt(in);
        while (boardMode != 1 && boardMode != 2) {
            invalidInputWarning();
            boardMode = enterInt(in);
        }
        int[][] forbiddenCells = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(forbiddenCells[i], 0);
        }
        if (boardMode == 2) {
            for (int i = 0; i < min(m, n); i++) {
                forbiddenCells[i][i] = 1;
            }
        }

        System.out.println("Enter number of players:");
        int numOfPlayers = enterInt(in);
        List<Player> players = new ArrayList<>(numOfPlayers);
        for (int i = 0; i < numOfPlayers; i++) {
            System.out.println("Choose " + (i + 1) + " player:\n1\tCheatingPlayer\n2\tHumanPlayer" +
                    "\n3\tRandomPlayer\n4\tSequentialPlayer");
            int playerId = enterInt(in);
            while (playerId < 1 || playerId > 4) {
                invalidInputWarning();
                playerId = enterInt(in);
            }
            players.add(choosePlayer(playerId));
        }

        System.out.println("Do you want to see log?\n1\tYes\n2\tNo");
        int logInt = enterInt(in);
        while (logInt != 1 && logInt != 2) {
            invalidInputWarning();
            logInt = enterInt(in);
        }
        boolean log = (logInt == 1);

        if (mode == 1) {
            final Game game = new Game(log, players);
            int result = game.play(new TicTacToeBoard(m, n, k, forbiddenCells, numOfPlayers));
            System.out.println("Game result: " + result);
        } else {
            final Tournament tournament = new Tournament(log, players);
            tournament.play(new TicTacToeBoard(m, n, k, forbiddenCells, 2));
            System.out.println(tournament);
            System.out.print("Winners are: ");
            List<Integer> winners = tournament.winners();
            for (Integer winner : winners) {
                System.out.print((winner + 1) + " ");
            }
        }

        in.close();
    }

    public static int enterInt(Scanner in) {
        try {
            while (true) {
                if (!in.hasNext() || in.hasNextInt()) {
                    int x = in.nextInt();
                    if (x > 0) {
                        in.nextLine();
                        return x;
                    }
                }
                invalidInputWarning();
                in.nextLine();
            }
        } catch (Exception e) {
            System.out.println("Invalid action, I give up");
            System.exit(0);
        }
        return 0;
    }

    private static void invalidInputWarning() {
        System.out.println("Your input is invalid, try again");
    }

    private static Player choosePlayer(int id) {
        switch (id) {
            case 1 -> {
                return new CheatingPlayer();
            }
            case 2 -> {
                return new HumanPlayer();
            }
            case 4 -> {
                return new SequentialPlayer();
            }
            default -> {
                return new RandomPlayer();
            }
        }
    }
}
