import java.util.Observable;
import java.util.List;
import java.util.Collections;

public class SetupModel extends Observable {
    private List<PlayerType> playerTypes;
    private List<Player> players;
    
    public List<PlayerType> getPlayerTypes() {
        return playerTypes;
    }
    
    public void addPlayer(String name, PlayerType type) {
        players.add(PlayerType.createPlayer(name, type));
        setChanged();
        notifyObservers(getPlayers());
    }
    
    public void removePlayer(Player player) {
        players.remove(player);
        setChanged();
        notifyObservers(getPlayers());
    }

    public boolean isFull(){
        return (players.size() < GameModel.MAX_PLAYER_COUNT);
    }

    public List<Player> getPlayers(){
        return Collections.unmodifiableList(players);
    } 

    private void moveElement(List<T> list, T element, int distance){
        int i = list.indexOf(element);
        list.remove(player);
        list.add(i + delta, element);
    }

    public void movePlayerUp(Player player){
        moveElement(players, player, -1);
    }

    public void movePlayerDown(Player player){
        moveElement(players, player, 1);
    }
}
