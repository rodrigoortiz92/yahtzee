/**
*
*@author Erkki Mattila
*/

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.util.Observer;
import java.util.Observable;

public class PlayerSetupView extends JPanel implements Observer {

    PlayerSetupModel model;
    PlayerSetupController controller;
    JTextField namefield;
    JComboBox typeSelection;
    
    public PlayerSetupView(PlayerSetupModel model) {
        this.model = model;
    }
    
    public void update(Observable o, Object arg) {
    
    }
}