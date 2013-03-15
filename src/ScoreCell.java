
import java.util.Observable;

/**
 *
 * @author Mikko Paukkonen
 */
public abstract class ScoreCell extends Observable {

    public class CellMarkedNotification {
    }

    abstract public Integer getScore();
}
