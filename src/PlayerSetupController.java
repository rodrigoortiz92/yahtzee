/**
*
*@author Erkki Mattila
*/

import javax.swing.ActionListener;
import javax.swing.ItemListener;
import java.util.Observer;

class PlayerSetupController implements ActionListener, ItemListener, Observer{

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
    
}