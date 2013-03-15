/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mikko Paukkonen
 */
public class SumOfAllDiceCell  extends MarkableScoreCell {
   @Override
    public int calculateScore(int[] dieValues) {
        int score = 0;
        
        for(int value : dieValues)
            score += value;
        
        return score;
    }
}
