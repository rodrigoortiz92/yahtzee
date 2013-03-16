/**
*
*@author Erkki Mattila
*/

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.Observer;
import java.util.Observable;

public class PlayerSetupController implements ActionListener, ItemListener, Observer{

    PlayerSetupModel model;
    PlayerSetupView view;
    
    public PlayerSetupController(PlayerSetupView view, PlayerSetupModel model) {
        this.view = view;
        this.model = model;
    }
    
    public void changeName() {
    }
    
    public void changeType() {
    }
    
    public void update(Observable o, Object args){
    }
        
    public void actionPerformed(ActionEvent e) {
    }
    
    public void itemStateChanged(ItemEvent e) {
    }
}