/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mikko Paukkonen
 */
public class SumOfAllDiceCell extends MarkableScoreCell {

    @Override
    public int calculateScore(DiceModel.DieValues dieValues) {
        int score = 0;

        for (int i = 0; i < dieValues.getValueCount(); ++i) {
            score += dieValues.valueAt(i);
        }

        return score;
    }
}
