
/**
 * Cell type that calculates the sum of dice of given kind.
 *
 * @author Mikko Paukkonen
 */
public class SumOfKindCell extends MarkableScoreCell {

    private int kind;

    @Override
    public int calculateScore(int[] dieValues) {
        int countOfKind = 0;

        for (int value : dieValues)
            if (value == kind)
                countOfKind++;

        return countOfKind * kind;
    }

    public SumOfKindCell(int kind) {
        this.kind = kind;
    }
}
