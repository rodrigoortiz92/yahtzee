/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mikko Paukkonen
 */
public class SumOfGivenDiceCell extends MarkableScoreCell {

    @Override
    public Combination getOptimalCombination() {
        return new Combination(
                new Requirement(DiceModel.DIE_MAX_VALUE, 3),
                new Requirement(DiceModel.DIE_MAX_VALUE - 1, 2));
    }
    int[] values;

    public SumOfGivenDiceCell(int... values) {
        this.values = values;
    }

    @Override
    public int calculateScore(DiceModel.DieValues dieValues) {
        for (int value : values) {
            if (dieValues.countOfValue(value) == 0) {
                return 0;
            }
        }

        int score = 0;

        for (int value : values) {
            score += value;
        }

        return score;
    }

    @Override
    public Integer getMaximumScore() {
        int score = 0;

        for (int value : values) {
            score += value;
        }

        return score;
    }
}
