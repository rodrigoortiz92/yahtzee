
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Mikko Paukkonen <mikko.paukkonen at uta.fi>
 */
public class BonusCell extends ScoreCell implements Observer{
    ScoreCell cell;
    int cutoff;
    int bonus;

    public BonusCell(ScoreCell cell, int cutoff, int bonus) {
        this.cell = cell;
        this.cutoff = cutoff;
        this.bonus = bonus;

        cell.addObserver(this);
    }

    @Override
    public Integer getScore() {
        if(cell.getScore() == null)
            return null;

        if(cell.getScore() >= cutoff)
            return bonus;

        return 0;
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers(new ScoreChangedNotification());
    }

    @Override
    public Integer getMaximumScore() {
        return bonus;
    }
}
