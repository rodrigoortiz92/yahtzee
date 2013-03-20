import java.util.Observable;
import java.util.List;
import java.util.LinkedList;
import java.util.Collections;

public class SetupModel extends Observable {
    private List<PlayerType> playerTypes;
    private List<GameType> gameTypes;
    private List<GameModel.PlayerDescription> players;
    
    public SetupModel(List<PlayerType> playerTypes, List<GameType> gameTypes) {
        this.playerTypes = playerTypes;
        this.gameTypes = gameTypes;
        players = new LinkedList<>();
    }

    public List<PlayerType> getPlayerTypes() {
        return Collections.unmodifiableList(playerTypes);
    }
    
    public void addPlayer(String name, PlayerType type) {
        players.add(new GameModel.PlayerDescription(name, type));
        setChanged();
        notifyObservers();
    }
    
    public void removePlayer(GameModel.PlayerDescription player) {
        players.remove(player);
        setChanged();
        notifyObservers(getPlayers());
    }

    public List<GameType> getGameTypes(){
        return gameTypes; 
    }

    public boolean isFull(){
        return (players.size() < GameModel.MAX_PLAYER_COUNT);
    }

    public List<GameModel.PlayerDescription> getPlayers(){
        return Collections.unmodifiableList(players);
    } 

    private <T> void moveElement(List<T> list, T element, int distance){
        int i = list.indexOf(element);
        list.remove(element);
        list.add(distance + i, element);
    }

    public void movePlayerUp(GameModel.PlayerDescription player){
        moveElement(players, player, -1);
    }

    public void movePlayerDown(GameModel.PlayerDescription player){
        moveElement(players, player, 1);
    }
}
