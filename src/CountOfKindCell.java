/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mikko Paukkonen
 */
public class CountOfKindCell extends MarkableScoreCell {

    int count;

    public CountOfKindCell(int count) {
        this.count = count;
    }

    @Override
    public int calculateScore(DiceModel.DieValues dieValues) {
        for (int i = 6; i > 0; --i) {
            if (dieValues.countOfValue(i) >= count) {
                return i * count;
            }
        }

        return 0;
    }

    @Override
    public Combination getOptimalCombination() {
       return new Combination(new Requirement(DiceModel.DIE_MAX_VALUE, getDiceModel().getDieCount()));
    }
}
