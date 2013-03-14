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
    public int calculateScore(int[] dieValues) {
        for (int i = 6; i > 0; --i) {
            int countOfKind = 0;

            for (int value : dieValues) {
                if (value == i) {
                    countOfKind++;
                }
            }
            
            if(countOfKind >= 2)
                return 2 * i;
        }
        
        return 0;
    }
}
