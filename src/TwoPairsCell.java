
import java.util.LinkedList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mikko Paukkonen
 */
public class TwoPairsCell extends MarkableScoreCell {
    
    @Override
    public int calculateScore(int[] dieValues) {
        LinkedList<Integer> pairs = new LinkedList<>();
        
        for (int i = 6; i > 0; --i) {
            int countOfKind = 0;
            
            for (int value : dieValues) {
                if (value == i) {
                    countOfKind++;
                }
            }
            
            if (countOfKind >= 2) {
                pairs.add(i);
            }
        }
        
        int score = 0;
        
        if (pairs.size() >= 2) {
            for (int k : pairs) {
                score += k * 2;
            }
        }
        
        return score;
    }
}
