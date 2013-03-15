
import java.util.Observable;

/**
 *
 * @author Mikko Paukkonen
 */
public class DiceModel extends Observable {

    public int[] dieValues = {1, 1, 1, 1, 1};

    public int[] getDieValues() {
        return dieValues;
    }

    public void setDieValues(int[] dieValues) {
        this.dieValues = dieValues;

        setChanged();
        notifyObservers();
    }
}
