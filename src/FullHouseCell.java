
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Mikko Paukkonen
 */
public class FullHouseCell extends MarkableScoreCell {

    private class Pair {

        int kind;
        int count;

        public Pair(int kind, int count) {
            this.kind = kind;
            this.count = count;
        }
    }

    private List<Pair> countOfDice(DiceModel.DieValues dieValues) {
        List<Pair> list = new LinkedList<>();

        for(int i = DiceModel.DIE_MIN_VALUE; i <= DiceModel.DIE_MAX_VALUE; ++i)
        {
            int count = dieValues.countOfValue(i);

            if(count > 0)
                list.add(new Pair(i, count));
        }

        return list;
    }

    private void filter(List<Pair> values, int min) {
        List<Pair> r = new LinkedList<>();

        for (Pair pair : values) {
            if (pair.count < min) {
                r.add(pair);
            }
        }

        values.removeAll(r);

    }

    private int clamp(int value, int min, int max) {
        if (value < min) {
            value = min;
        }
        if (value > max) {
            value = max;
        }

        return value;

    }

    private class Comp implements Comparator<Pair> {

        @Override
        public int compare(Pair o1, Pair o2) {
            Integer s1 = clamp(o1.count, 2, 3) * o1.kind;
            Integer s2 = clamp(o2.count, 2, 3) * o2.kind;

            return s1.compareTo(s2);
        }
    }

    @Override
    public int calculateScore(DiceModel.DieValues dieValues) {
        List<Pair> counts = countOfDice(dieValues);

        filter(counts, 2);

        Collections.sort(counts, new Comp());
        Collections.reverse(counts);

        if (counts.size() >= 2) {
            Pair threeDice = counts.get(0);
            Pair twoDice = counts.get(1);

            return threeDice.kind * 3 + twoDice.kind * 2;
        }

        return 0;
    }
}
