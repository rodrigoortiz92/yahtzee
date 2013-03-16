
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Mikko Paukkonen
 */
abstract public class Player extends Observable implements Observer {

    private String name;
    private ScoreColumn scoreColumn;
    private GameModel model;

    boolean isInTurn() {
       return this == model.getCurrentPlayer();
    }

    public class CellMarkedNotification {
    }

    public String getName() {
        return name;
    }

    public List<ScoreCell> getScoreCells() {
        return scoreColumn.getCells();
    }

    public Player(GameModel model, String name) {
        this.model = model;
        this.name = name;
        this.scoreColumn = new ScoreColumn(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof ScoreCell.ScoreChangedNotification) {
            setChanged();
            notifyObservers(new CellMarkedNotification());
        } else if (arg instanceof GameModel.TurnChangedNotification) {
            setChanged();
            notifyObservers(arg);
        }
    }

    abstract void playTurn(DiceModel diceModel);
}
