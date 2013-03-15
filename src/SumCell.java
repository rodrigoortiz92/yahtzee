/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mikko Paukkonen <mikko.paukkonen at uta.fi>
 */
public class SumCell extends ScoreCell{
    ScoreCell[] cells;

    public SumCell(ScoreCell... cells) {
        this.cells = cells;
    }

    @Override
    public Integer getScore() {
        Integer sum = 0; 
        
        for(ScoreCell cell : cells)
        {
            if(cell.getScore() == null)
                return null;
            
            sum += cell.getScore();
        }
        
        return sum;
    }   
}
