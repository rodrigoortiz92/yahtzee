
import java.util.Observable;
import java.util.Observer;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mikko Paukkonen <mikko.paukkonen at uta.fi>
 */
public class SumCell extends ScoreCell implements Observer{
    ScoreCell[] cells;

    public SumCell(ScoreCell... cells) {
        this.cells = cells;

        for(ScoreCell cell : cells)
        {
            cell.addObserver(this);
        }
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

    @Override
    public void update(Observable o, Object arg) {
        for(ScoreCell cell : cells)
        {
            if(cell.getScore() == null)
                return;
        }

        setChanged();
        notifyObservers(new ScoreChangedNotification());
    }

    @Override
    public Integer getMaximumScore() {
        Integer sum = 0;

        for(ScoreCell cell : cells)
        {
            if(cell.getMaximumScore() == null)
                return null;

            sum += cell.getMaximumScore();
        }

        return sum;
    }
}
