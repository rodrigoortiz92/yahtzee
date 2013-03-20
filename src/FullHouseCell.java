
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Mikko Paukkonen
 */
public class FullHouseCell extends MarkableScoreCell {

    @Override
    public Combination getOptimalCombination() {
        return new Combination(
                new Requirement(DiceModel.DIE_MAX_VALUE, 3),
                new Requirement(DiceModel.DIE_MAX_VALUE - 1, 2)
                );
    }

    @Override
    public Integer getMaximumScore() {
        return DiceModel.DIE_MAX_VALUE * 3 + (DiceModel.DIE_MAX_VALUE - 1) * 2;
    }

    private boolean match(List<Integer> values, int count, List<Integer> kinds, List<Integer> allMatches) {
        List<Integer> matches;

        for (Integer kind : kinds) {
            matches = new LinkedList<>();

            for (Integer value : values) {
                if (kind == value && matches.size() < count) {
                    matches.add(value);
                }
            }

            if (matches.size() == count) {
                values.removeAll(matches);
                kinds.remove(kind);
                allMatches.addAll(matches);
                return true;
            }
        }

        return false;
    }

    public int sum(List<Integer> dice) {
        int sum = 0;

        for (Integer i : dice) {
            sum += i;
        }

        return sum;
    }

    @Override
    public int calculateScore(DiceModel.DieValues dieValues) {
        List<Integer> dice = new LinkedList<>();

        for (int i = 0; i < dieValues.getValueCount(); ++i) {
            dice.add(dieValues.valueAt(i));
        }

        List<Integer> kinds = new LinkedList<>();

        for (int i = DiceModel.DIE_MAX_VALUE; i >= DiceModel.DIE_MIN_VALUE; --i) {
            kinds.add(i);
        }

        List<Integer> allMatches = new LinkedList<>();

        boolean matched;
        matched = match(dice, 3, kinds, allMatches);
        matched &= match(kinds, 2, kinds, allMatches);

        if (matched) {
            return sum(allMatches);
        }

        return 0;
    }

    private interface Matcher
    {
        public boolean matches(Integer a, Integer b);
    }

    private class MaximumMatcher implements Matcher
    {

        @Override
        public boolean matches(Integer a, Integer b) {
            return true;
        }

    }

    private class ScoreMatcher implements Matcher
    {

        @Override
        public boolean matches(Integer a, Integer b) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

    private int doMatching()
    {
        return 0;
    }
}
