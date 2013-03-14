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
    public int calculateScore(int[] dieValues) {
        for (int i = 6; i > 0; --i) {
            int countOfKind = 0;
            
            for (int value : dieValues) {
                if (value == i) {
                    countOfKind++;
                }
            }
            
            if (countOfKind >= count) {
                return i * count;
            }
        }    
        
        return 0;
    }
}
