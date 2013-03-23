
public class YahtzeeCell extends MarkableScoreCell {

    int points;

    public YahtzeeCell(int points) {
        this.points = points;
    }

    @Override
    protected int calculateScore(DiceModel.DieValues dieValues) {
        for (int i = 6; i > 0; --i) {
            if (dieValues.countOfValue(i) == getDiceModel().getDieCount()) {
                return points;
            }
        }

        return 0;
    }

    @Override
    public Combination getOptimalCombination() {
        return new Combination(
                new Requirement(DiceModel.DIE_MAX_VALUE, getDiceModel().getDieCount()));
    }

    @Override
    public Integer getMaximumScore() {
        return points;
    }
}
