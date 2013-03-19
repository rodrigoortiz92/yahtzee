
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
    public Combination getOptimalCombination() {
        return new Combination(
                new Requirement(DiceModel.DIE_MAX_VALUE, 2),
                new Requirement(DiceModel.DIE_MAX_VALUE - 1, 2));
    }

    @Override
    public int calculateScore(DiceModel.DieValues dieValues) {
        LinkedList<Integer> pairs = new LinkedList<>();

        for (int i = 6; i > 0; --i) {
            if (dieValues.countOfValue(i) >= 2) {
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

    @Override
    public Integer getMaximumScore() {
        return DiceModel.DIE_MAX_VALUE * 2 + (DiceModel.DIE_MAX_VALUE - 1) * 2;
    }
}
