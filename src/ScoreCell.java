
import java.util.Observable;

public abstract class ScoreCell extends Observable {

    public class ScoreChangedNotification {
    }

    abstract public Integer getScore();
    abstract public Integer getMaximumScore();
}
