
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

    public void markScore(DiceModel.DieValues dieValues) {
        // TODO: think about how to handle multiple markings
        if (score == null && player != null && player.isInTurn()) {
            score = new Integer(calculateScore(dieValues));
            setChanged();
            notifyObservers(new ScoreChangedNotification());
        }
    }

    public int getMarkableScore() {
        return calculateScore(getDiceModel().getDieValues());
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

    public void setPlayer(Player player) {
        if (this.player != null) {
            deleteObserver(this.player);
        }

        this.player = player;

        if (this.player != null) {
            addObserver(this.player);
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
        if (arg instanceof GameModel.TurnChangedNotification) {
            setChanged();
            notifyObservers(new MarkableChangeNotification());
        }
    }
}
