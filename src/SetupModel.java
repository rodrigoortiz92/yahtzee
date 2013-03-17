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
    
    public class PlayerAddedNotification {
    }
    
    public class PlayerRemovedNotification {
    }
    
    public void addPlayer() {
        System.out.println("SetupModel_addPlayer");
        setChanged();
        notifyObservers(new PlayerAddedNotification());
    }
    
    public void removePlayer() {
        System.out.println("SetupModel_removePlayer");
        setChanged();
        notifyObservers(new PlayerRemovedNotification());
    }
}