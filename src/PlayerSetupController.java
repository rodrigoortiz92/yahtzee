/**
*
*@author Erkki Mattila
*/

class PlayerSetupController implements ActionListener, ItemListener{

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