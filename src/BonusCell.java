/**
 *
 * @author Mikko Paukkonen <mikko.paukkonen at uta.fi>
 */
public class BonusCell extends ScoreCell{
    ScoreCell cell;
    int cutoff;
    int bonus;

    public BonusCell(ScoreCell cell, int cutoff, int bonus) {
        this.cell = cell;
        this.cutoff = cutoff;
        this.bonus = bonus;
    }
    
    @Override
    public Integer getScore() {
        if(cell.getScore() == null)
            return null;
        
        if(cell.getScore() >= cutoff)
            return bonus;
        
        return 0;
    }   
}
