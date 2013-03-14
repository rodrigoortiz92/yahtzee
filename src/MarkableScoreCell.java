/**
 *
 * @author Mikko Paukkonen <mikko.paukkonen at uta.fi>
 */
abstract public class MarkableScoreCell extends ScoreCell {

    private Integer score = null;
    private boolean hasBeenRolled = false;
    private Player player = null;

    public abstract int calculateScore(int[] dieValues);

    public void markScore(int[] dieValues) {
        // TODO: think about how to handle multiple markings
        if (score == null && player != null && player.isInTurn()) {
            score = new Integer(calculateScore(dieValues));
        }
    }

    /**
     * Marks the cell as having been rolled if the set of values applies to this
     * cell. The cell is considered applying if the calculated score for this
     * cell for the set of values is greater than zero.
     */
    public void markAsRolled(int[] dieValues) {
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

        addObserver(player);
    }
}
