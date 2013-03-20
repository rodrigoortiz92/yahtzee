
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Mikko Paukkonen
 */
public class CountPatternCell extends MarkableScoreCell {

    Integer[] counts;

    public CountPatternCell(Integer... counts) {
        this.counts = counts;
    }

    @Override
    public Combination getOptimalCombination() {
        return new Combination(
                new Requirement(DiceModel.DIE_MAX_VALUE, 3),
                new Requirement(DiceModel.DIE_MAX_VALUE - 1, 2));
    }

    @Override
    public Integer getMaximumScore() {
        List<Integer> dice = new LinkedList<>();

        for (int i = 0; i < getDiceModel().getDieCount(); ++i) {
            dice.add(null);
        }

        return doMatching(dice, new MaximumMatcher());
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

        return doMatching(dice, new ScoreMatcher());
    }

    private interface Matcher {

        public boolean match(List<Integer> values, int count,
                List<Integer> kinds, List<Integer> allMatches);
    }

    private class MaximumMatcher implements Matcher {

        @Override
        public boolean match(List<Integer> values, int count,
                List<Integer> kinds, List<Integer> allMatches) {

            for (Integer kind : kinds) {
                if (values.size() >= count) {
                    for (int i = 0; i < count; ++i) {
                        allMatches.add(kind);
                        values.remove(0);
                        kinds.remove(kind);
                    }

                    return true;
                }
            }

            return false;
        }
    }

    private class ScoreMatcher implements Matcher {

        @Override
        public boolean match(List<Integer> values, int count,
                List<Integer> kinds, List<Integer> allMatches) {
            List<Integer> matches;

            for (Integer kind : kinds) {
                matches = new LinkedList<>();

                for (Integer value : values) {
                    if (value == kind && matches.size() < count) {
                        matches.add(kind);
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
    }

    private int doMatching(List<Integer> dice, Matcher matcher) {
        List<Integer> kinds = getDiceModel().getKinds();
        List<Integer> allMatches = new LinkedList<>();

        boolean matched = true;

        for (Integer count : counts) {
            matched = matcher.match(dice, count, kinds, allMatches);
        }

        if (matched) {
            return sum(allMatches);
        }

        return 0;
    }
}
