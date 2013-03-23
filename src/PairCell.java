
public class PairCell extends MarkableScoreCell {

     @Override
    public Combination getOptimalCombination() {
        return new Combination(
                new Requirement(DiceModel.DIE_MAX_VALUE, 2)
                );
    }


    @Override
    public int calculateScore(DiceModel.DieValues dieValues) {
        for (int i = DiceModel.DIE_MAX_VALUE; i >= DiceModel.DIE_MIN_VALUE; --i) {
            int countOfKind = dieValues.countOfValue(i);

            if (countOfKind >= 2) {
                return 2 * i;
            }
        }

        return 0;
    }

    @Override
    public Integer getMaximumScore() {
        return DiceModel.DIE_MAX_VALUE * 2;
    }
}
