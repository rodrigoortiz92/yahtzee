/**
*
*@author Erkki Mattila
*/

import javax.swing.JDialog;
import java.util.Observer;
import java.util.Observable;

public class SetupView extends JDialog implements Observer {

    SetupController controller;
    SetupModel model; 
    
    public SetupView(SetupModel model) {
        this.model = model;
        controller = new SetupController(this);
        
    }
    
    public void update(Observable o, Object args){
    }
}