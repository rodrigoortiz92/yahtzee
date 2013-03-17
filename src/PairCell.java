/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mikko Paukkonen <mikko.paukkonen at uta.fi>
 */
public class PairCell extends MarkableScoreCell {

     @Override
    public Combination getOptimalCombination() {
        return new Combination(
                new Requirement(DiceModel.DIE_MAX_VALUE, 2)
                );
    }


    @Override
    public int calculateScore(DiceModel.DieValues dieValues) {
        for (int i = DiceModel.DIE_MAX_VALUE; i >= DiceModel.DIE_MIN_VALUE; ++i) {
            int countOfKind = dieValues.countOfValue(i);

            if (countOfKind >= 2) {
                return 2 * i;
            }
        }

        return 0;
    }
}
