
public class DivisibleSumCell extends MarkableScoreCell {

    int divisor;

    @Override
    public Combination getOptimalCombination() {
        return new Combination(
                new Requirement(DiceModel.DIE_MAX_VALUE, getDiceModel().getDieCount()));
    }

    @Override
    public int calculateScore(DiceModel.DieValues dieValues) {
        int score = 0;

        for (int i = 0; i < dieValues.getValueCount(); ++i) {
            score += dieValues.valueAt(i);
        }

        if (score % divisor == 0) {
            return score;
        }

        return 0;
    }

    public DivisibleSumCell(int divisor) {
        this.divisor = divisor;
    }

    @Override
    public Integer getMaximumScore() {
        int i = 0;

        while (i + divisor < getDiceModel().getDieCount() * DiceModel.DIE_MAX_VALUE) {
            i += divisor;
        }

        return i;
    }
}
