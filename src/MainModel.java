
import java.util.Observable;

public class MainModel extends Observable {
    private GameModel gameModel;

    public MainModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;

        setChanged();
        notifyObservers();
    }
}
