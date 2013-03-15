/**
*
*@author Erkki Mattila
*/

import java.awt.event.ActionListener;
import java.util.Observer;

public class SetupController implements Observer, ActionListener {
    
    SetupView view;
    SetupController controller;
    
    public SetupController(SetupView view) {
        this.view = view;
    }
    
    public void removePlayer() {
    }
    
    public void newPlayer() {
    }
    
    public void update(SetupModel model, Object args){
    }
}