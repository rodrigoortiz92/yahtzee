/**
*
*@author Erkki Mattila
*/

import java.util.Observable;
import java.util.List;

public class SetupModel extends Observable {
    List<PlayerType> playerTypes;
    List<PlayerSetupModel> playerSetup;
    
    public List<PlayerType> getPlayerTypes() {
        return playerTypes;
    }
    
    public void addPlayer(PlayerType type) {
        notifyObservers();
    }
    
    public void removePlayer(PlayerSetupModel playerSetupModel) {
        notifyObservers();
    }
}