
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
    
    private List<Pair> countOfDice(int[] values) {
        Map<Integer, Integer> counts = new HashMap<>();
        
        int count = 0;
        
        for (int v : values) {
            if (counts.containsKey(v)) {
                counts.put(v, counts.get(v) + 1);
            } else {
                counts.put(v, new Integer(1));
            }
        }
        
        List<Pair> list = new LinkedList<>();
        
        for (int v : counts.keySet()) {
            list.add(new Pair(v, counts.get(v)));
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
    public int calculateScore(int[] dieValues) {
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
