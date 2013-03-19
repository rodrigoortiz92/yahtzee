
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.Timer;

/**
 *
 * @author Mikko Paukkonen
 */
public class AiPlayer extends Player {

    public AiPlayer(GameModel model, DiceModel diceModel, ScoreColumn column, String name) {
        super(model, diceModel, column, name);
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

    private class Performer implements ActionListener {

        DiceModel diceModel;
        Timer timer;

        public Performer(DiceModel diceModel) {
            this.diceModel = diceModel;

            timer = new Timer(50, this);
            timer.setRepeats(true);
            timer.start();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (diceModel.canDiceBeRolled()) {
                if (diceModel.canDiceBeLocked()) {
                    DiceModel.DieValues values = diceModel.getDieValues();

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
            } else {

                MarkableScoreCell bestCell = null;
                DiceModel.DieValues values = diceModel.getDieValues();

                for (ScoreCell cell : getScoreCells()) {
                    if (!(cell instanceof MarkableScoreCell)) {
                        continue;
                    }

                    MarkableScoreCell markable = (MarkableScoreCell) cell;

                    if (markable.getScore() != null) {
                        continue;
                    }

                    if (bestCell == null || markable.getMarkableScore() > bestCell.getMarkableScore()) {
                        bestCell = markable;
                    }
                }

                if (bestCell != null) {
                    bestCell.markScore();
                }

                timer.stop();
            }
        }
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
        new Performer(diceModel);
    }

    @Override
    boolean acceptsUiInput() {
        return false;
    }
}
