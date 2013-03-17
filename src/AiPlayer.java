
import java.util.Arrays;

/**
 *
 * @author Mikko Paukkonen
 */
public class AiPlayer extends Player {

    public AiPlayer(GameModel model, String name) {
        super(model, name);
    }

    private boolean[] determineLocks(DiceModel.DieValues values, MarkableScoreCell cell) {
        boolean[] locks = new boolean[values.getValueCount()];

        Arrays.fill(locks, false);

        for (MarkableScoreCell.Requirement requirement : cell.getOptimalCombination().requirements) {
            int diceLeft = requirement.count;

            for (int i = 0; i < values.getValueCount(); ++i) {
                if (values.valueAt(i) == requirement.kind) {
                    diceLeft--;
                    locks[i] = true;
                }
            }

        }

        return locks;
    }

    private int countOfLocks(boolean[] locks) {
        int count = 0;

        for (boolean l : locks) {
            if (l) {
                count++;
            }
        }

        return count;
    }

    @Override
    void playTurn(DiceModel diceModel) {
        DiceModel.DieValues values = diceModel.getDieValues();

        while (diceModel.canDiceBeRolled()) {

            if (diceModel.canDiceBeLocked()) {

                boolean[] bestLocks = new boolean[diceModel.getDieCount()];

                for (ScoreCell cell : getScoreCells()) {
                    if (!(cell instanceof MarkableScoreCell)) {
                        continue;
                    }

                    MarkableScoreCell markable = (MarkableScoreCell) cell;

                    if (markable.getScore() != null) {
                        continue;
                    }

                    boolean[] locks = determineLocks(values, markable);

                    if (countOfLocks(locks) > countOfLocks(bestLocks)) {
                        bestLocks = locks;
                    }
                }

                for (int i = 0; i < values.getValueCount(); ++i) {
                    diceModel.setDieLock(i, bestLocks[i]);
                }
            }

            diceModel.roll();
        }

        MarkableScoreCell bestCell = null;


        for (ScoreCell cell : getScoreCells()) {
            if (!(cell instanceof MarkableScoreCell)) {
                continue;
            }

            MarkableScoreCell markable = (MarkableScoreCell) cell;

            if (markable.getScore() != null) {
                continue;
            }

            if (bestCell == null || markable.calculateScore(values) > bestCell.calculateScore(values)) {
                bestCell = markable;
            }
        }

        if (bestCell != null) {
            bestCell.markScore(values);
        }
    }
}
