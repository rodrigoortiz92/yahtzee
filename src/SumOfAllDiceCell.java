
public class SumOfAllDiceCell extends MarkableScoreCell {

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

        return score;
    }

    @Override
    public Integer getMaximumScore() {
       return DiceModel.DIE_MAX_VALUE * getDiceModel().getDieCount();
    }
}
