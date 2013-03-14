
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public class GameModel extends Observable {

    private DiceModel diceModel;
    private List<Player> players;
    private Player currentPlayer;

    public class NewGameNotification {
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
