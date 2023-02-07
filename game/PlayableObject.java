package game;

import java.util.List;

public abstract class PlayableObject {
    protected final boolean log;
    protected final List<Player> players;
    protected int numOfPlayers;

    public PlayableObject(final boolean log, List<Player> players) {
        this.log = log;
        this.players = players;
        numOfPlayers = players.size();
    }

    //returns id of winner or 0 if there is no winner
    abstract public int play(final Board board);

    protected void log(final String message, final Object... arguments) {
        if (log) {
            System.out.printf(message + "%n", arguments);
        }
    }
}