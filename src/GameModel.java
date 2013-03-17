
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class GameModel extends Observable implements Observer {

    private DiceModel diceModel;
    private List<Player> players;
    private Player currentPlayer;

    public class TurnEndNotification {

        public Player player;

        public TurnEndNotification(Player player) {
            this.player = player;
        }
    }

    public class TurnBeginNotification {

        public Player player;

        public TurnBeginNotification(Player player) {
            this.player = player;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Player.CellMarkedNotification) {
            setChanged();
            notifyObservers(new TurnEndNotification(currentPlayer));

            int index = players.indexOf(currentPlayer);

            if (index == players.size() - 1) {
                index = 0;
            } else {
                index++;
            }

            currentPlayer = players.get(index);
            diceModel.clear(currentPlayer.acceptsUiInput());

            setChanged();
            notifyObservers(new TurnBeginNotification(currentPlayer));

            currentPlayer.playTurn(diceModel);

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
        return new ScoreColumn(null, null).getCellNames();
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

        currentPlayer = null;

        setChanged();
        notifyObservers(new NewGameNotification());

        if (!players.isEmpty()) {
            currentPlayer = players.get(0);
        }

        diceModel.clear(currentPlayer.acceptsUiInput());
        setChanged();
        notifyObservers(new TurnBeginNotification(currentPlayer));

        currentPlayer.playTurn(diceModel);
    }

    public GameModel(DiceModel diceModel) {
        this.diceModel = diceModel;
        this.players = new LinkedList<>();
    }
}
