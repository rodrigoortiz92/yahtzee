
import java.util.Arrays;
import java.util.Collections;

public class SumOfKindCell extends MarkableScoreCell {

    private int[] kinds;

    @Override
    public Combination getOptimalCombination() {
        return new Combination(
                new Requirement(getMaximumKind(), getDiceModel().getDieCount()));
    }

    @Override
    public int calculateScore(DiceModel.DieValues dieValues) {
        int score = 0;

        for (Integer kind : kinds) {
            score += dieValues.countOfValue(kind) * kind;
        }

        return score;
    }

    public SumOfKindCell(int... kinds) {
        this.kinds = kinds;
    }

    @Override
    public Integer getMaximumScore() {
        return getMaximumKind() * getDiceModel().getDieCount();
    }

    private Integer getMaximumKind() {
        Integer max = null;

        for (int kind : kinds) {
            if (max == null || kind > max) {
                max = kind;
            }
        }

        return max;
    }
}
