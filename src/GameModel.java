
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public class GameModel extends Observable {

    private DiceModel diceModel;
    private List<Player> players;
    private Player currentPlayer;

    public String[] getScoreCellNames() {
        return new ScoreColumn(null).getCellNames();
    }

    public void startNewGame(List<Player> players) {
        this.players = players;

        if (players.isEmpty())
            currentPlayer = null;
        else
            currentPlayer = players.get(0);
    }

    public GameModel(DiceModel diceModel) {
        this.diceModel = diceModel;

        startNewGame(new LinkedList<Player>());
    }
}
