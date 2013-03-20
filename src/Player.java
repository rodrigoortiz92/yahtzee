
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private Map<Integer, Integer> rollFrequencies;

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

    public Player(GameModel model, DiceModel diceModel, ScoreColumn column, String name) {
        this.model = model;
        this.name = name;
        this.scoreColumn = column;
        column.setThings(this, diceModel);
        this.rollFrequencies = new HashMap<>();

        for (int i = DiceModel.DIE_MIN_VALUE; i <= DiceModel.DIE_MAX_VALUE; ++i) {
            rollFrequencies.put(i, 0);
        }

        diceModel.addObserver(this);
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
        } else if (arg instanceof GameModel.TurnEndNotification) {
            GameModel.TurnEndNotification end = (GameModel.TurnEndNotification) arg;

            if (end.player == this) {
                setChanged();
                notifyObservers(arg);
            }
        } else if (arg instanceof DiceModel.RollNotification && isInTurn()) {
            DiceModel.RollNotification roll = (DiceModel.RollNotification) arg;

            for (Integer die : roll.rolledDice) {
                rollFrequencies.put(die, rollFrequencies.get(die) + 1);
            }
        }
    }

    public Map<Integer, Integer> getRollFrequencies() {
        return Collections.unmodifiableMap(rollFrequencies);
    }

    public Integer getTotalRolls() {
        Integer total = 0;

        for (Integer kind : rollFrequencies.keySet()) {
            total += rollFrequencies.get(kind);
        }

        return total;
    }

    public int calculateScore() {
        return scoreColumn.getTotalCell().getScore();
    }

    public boolean hasBonus() {
        if (scoreColumn.getBonusCell().getScore() != null) {
            return (scoreColumn.getBonusCell().getScore() > 0);
        }

        return false;
    }

    boolean hasYahtzee() {
        if (scoreColumn.getYahtzeeCell().getScore() != null) {
            return (scoreColumn.getYahtzeeCell().getScore() > 0);
        }

        return false;
    }

    public int calculateTopScore() {
        return scoreColumn.getTopTotalCell().getScore();
    }

    abstract void playTurn(DiceModel diceModel);

    abstract boolean acceptsUiInput();
}
