
import java.util.List;
import java.util.Observable;

public class AddPlayerModel extends Observable {
    private String name;
    private PlayerType playerType;
    private SetupModel model;

    public AddPlayerModel(SetupModel model) {
        this.name = "";
        this.playerType = model.getPlayerTypes().get(0);
        this.model = model;
    }

    public List<PlayerType> getPlayerTypes() {
        return model.getPlayerTypes();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

        setChanged();
        notifyObservers();
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;

        setChanged();
        notifyObservers();
    }

    public boolean addPlayer()
    {
        if(playerType == null)
            return false;

        return model.addPlayer(name, playerType);
    }

    void clear() {
        setName("");
        setPlayerType(getPlayerTypes().get(0));
    }
}
