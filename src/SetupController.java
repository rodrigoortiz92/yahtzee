/**
*
*@author Erkki Mattila
*/

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Observer;
import java.util.Observable;

public class SetupController implements Observer, ActionListener {
    
    SetupView view;
    
    public SetupController(SetupView view) {
        this.view = view;
    }
    
    public void removePlayer() {
    }
    
    public void newPlayer() {
    }
    
    public void update(Observable o, Object args){
    }
    
    public void actionPerformed(ActionEvent e) {
    }
}