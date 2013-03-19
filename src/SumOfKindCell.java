
/**
 * Cell type that calculates the sum of dice of given kind.
 *
 * @author Mikko Paukkonen
 */
public class SumOfKindCell extends MarkableScoreCell {

    private int kind;

     @Override
    public Combination getOptimalCombination() {
        return new Combination(
                new Requirement(kind, getDiceModel().getDieCount())
                );
    }


    @Override
    public int calculateScore(DiceModel.DieValues dieValues) {
        return dieValues.countOfValue(kind) * kind;
    }

    public SumOfKindCell(int kind) {
        this.kind = kind;
    }
}
