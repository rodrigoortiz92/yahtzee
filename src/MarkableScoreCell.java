
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Mikko Paukkonen <mikko.paukkonen at uta.fi>
 */
abstract public class MarkableScoreCell extends ScoreCell implements Observer {

    private Integer score = null;
    private boolean hasBeenRolled = false;
    private Player player = null;
    private DiceModel diceModel = null;

    public class Requirement {

        public int kind;
        public int count;

        public Requirement(int kind, int count) {
            this.kind = kind;
            this.count = count;
        }
    }

    public class Combination {

        public Requirement[] requirements;

        public Combination(Requirement... requirements) {
            this.requirements = requirements;
        }
    }

    public class MarkableChangeNotification {
    }

    protected abstract int calculateScore(DiceModel.DieValues dieValues);

    public abstract Combination getOptimalCombination();

    public boolean isMarkable() {
        return (score == null && getPlayer() != null && getPlayer().isInTurn() && diceModel.getDieValues() != null);
    }

    public void markScore() {
        // TODO: think about how to handle multiple markings
        if (score == null && getPlayer() != null && getPlayer().isInTurn()) {
            score = new Integer(calculateScore(diceModel.getDieValues()));
            setChanged();
            notifyObservers(new MarkableChangeNotification());
            setChanged();
            notifyObservers(new ScoreChangedNotification());

        }
    }

    public Integer getMarkableScore() {
        if (diceModel.getDieValues() != null) {
            return calculateScore(getDiceModel().getDieValues());
        }

        return null;
    }

    /**
     * Marks the cell as having been rolled if the set of values applies to this
     * cell. The cell is considered applying if the calculated score for this
     * cell for the set of values is greater than zero.
     */
    public void markAsRolled(DiceModel.DieValues dieValues) {
        if (calculateScore(dieValues) != 0) {
            hasBeenRolled = true;
        }
    }

    public boolean hasBeenRolled() {
        return hasBeenRolled;
    }

    @Override
    public Integer getScore() {
        if (score != null) {
            return new Integer(score.intValue());
        }

        return null;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        if (this.getPlayer() != null) {
            deleteObserver(this.getPlayer());
        }

        this.player = player;

        if (this.getPlayer() != null) {
            addObserver(this.getPlayer());
        }
    }



    public void setDiceModel(DiceModel diceModel) {
        if (this.diceModel != null) {
            diceModel.deleteObserver(this);
        }

        this.diceModel = diceModel;

        if (this.diceModel != null) {
            diceModel.addObserver(this);
        }
    }

    public DiceModel getDiceModel() {
        return diceModel;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (score == null || arg instanceof GameModel.TurnEndNotification) {
            setChanged();
            notifyObservers(new MarkableChangeNotification());
        } else if (arg instanceof GameModel.TurnBeginNotification && isMarkable()) {
            setChanged();
            notifyObservers(new MarkableChangeNotification());
        } else if (o instanceof DiceModel && isMarkable()) {
            setChanged();
            notifyObservers(new MarkableChangeNotification());
        }
    }
}
