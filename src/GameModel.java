
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class GameModel extends Observable implements Observer {

    private DiceModel diceModel;
    private List<Player> players;
    private Player currentPlayer;
    public static final int MAX_PLAYER_COUNT = 10;

    public class TurnChangedNotification {
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Player.CellMarkedNotification) {
            int index = players.indexOf(currentPlayer);

            if (index == players.size() - 1) {
                index = 0;
            } else {
                index++;
            }

            currentPlayer = players.get(index);

            setChanged();
            notifyObservers(new TurnChangedNotification());
        } else {
            setChanged();
            notifyObservers(new ScoreChangeNotification());
        }
    }

    Player getCurrentPlayer() {
        return currentPlayer;
    }

    public class NewGameNotification {
    }

    public class ScoreChangeNotification {
    }

    public List<String> getScoreCellNames() {
        return new ScoreColumn(null).getCellNames();
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public void startNewGame(List<Player> players) {
        this.players.clear();
        this.players.addAll(players);

        for (Player player : players) {
            player.addObserver(this);
        }

        if (players.isEmpty())
            currentPlayer = null;
        else
            currentPlayer = players.get(0);

        setChanged();
        notifyObservers(new NewGameNotification());
    }

    public GameModel(DiceModel diceModel) {
        this.diceModel = diceModel;
        this.players = new LinkedList<>();
    }
}
