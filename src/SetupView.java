/**
*
*@author Erkki Mattila
*/

import javax.swing.JDialog;
import java.util.Observer;

public class SetupView extends JDialog implements Observer{

    SetupController controller;
    SetupModel model; 
    
    public SetupView(SetupModel model) {
        this.model = model;
    }
    
    public void update(SetupModel model, Object args){
    }
}