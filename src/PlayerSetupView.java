/**
*
*@author Erkki Mattila
*/

import javax.swing.JPanel;
import java.awt.GridBagLayout;

class PlayerSetupView {

    PlayerSetupModel model;
    PlayerSetupController controller;
    JTextField namefield;
    typeSelection JComboBox;
    
    public PlayerSetupView(PlayerSetupModel model) {
        this.model = model;
    }
}