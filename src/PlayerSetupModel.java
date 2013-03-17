/**
*
*@author Erkki Mattila
*/

import java.util.Observable;

public class PlayerSetupModel extends Observable {
    
    String name;
    PlayerType playerType;
    
    public PlayerSetupModel(String name, PlayerType type) {
        setName(name);
        setType(type);
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        name = name;
        notifyObservers();
    }
    
    public PlayerType getType() {
        return playerType;
    }
    
    public void setType(PlayerType type) {
        playerType = type;
        notifyObservers();
    }
    
}