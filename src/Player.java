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

    public Player(GameModel model, DiceModel diceModel, String name) {
        this.model = model;
        this.name = name;
        this.scoreColumn = new ScoreColumn(this, diceModel);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof ScoreCell.ScoreChangedNotification) {
            setChanged();
            notifyObservers(new CellMarkedNotification());
        } else if (arg instanceof GameModel.TurnBeginNotification) {
            GameModel.TurnBeginNotification begin = (GameModel.TurnBeginNotification) arg;

            if (begin.player == this) {
                setChanged();
                notifyObservers(arg);
            }
        }else if (arg instanceof GameModel.TurnEndNotification) {
            GameModel.TurnEndNotification end = (GameModel.TurnEndNotification) arg;

            if (end.player == this) {
                setChanged();
                notifyObservers(arg);
            }
        }
    }

    abstract void playTurn(DiceModel diceModel);
    abstract boolean acceptsUiInput();
}
