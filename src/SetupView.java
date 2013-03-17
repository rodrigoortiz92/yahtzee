/**
*
*@author Erkki Mattila
*/

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.util.Observer;
import java.util.Observable;

public class SetupView extends JDialog implements Observer {
    
    JButton addPlayer;
    JButton removePlayer;
    int counter;

    SetupController controller;
    SetupModel model; 
    
    public SetupView(SetupModel model) {
        super();
        this.setLayout(new GridBagLayout());
        counter = 0;
        
        this.model = model;
        this.model.addObserver(this);
        controller = new SetupController(this, this.model);
        
        addPlayer = new JButton(controller.getNewPlayerAction());
        removePlayer = new JButton(controller.getRemovePlayerAction());
        
        EasyGridBagLayout.addToLayout(this, new JLabel("LOL1"), 0, counter);   
        EasyGridBagLayout.addToLayout(this, new JLabel("LOL2"), 1, counter);
        EasyGridBagLayout.addToLayout(this, addPlayer, 0, ++counter);
        EasyGridBagLayout.addToLayout(this, removePlayer, 1, counter);
        
        pack();

    }
    
    public void update(Observable o, Object args){
        System.out.println("SetupView observes!");
        if (args instanceof SetupModel.PlayerAddedNotification){
            System.out.println("PlayerAdded");
            EasyGridBagLayout.addToLayout(this, new JLabel("LOL1"), 0, ++counter);   
            EasyGridBagLayout.addToLayout(this, new JLabel("LOL2"), 1, counter);
            EasyGridBagLayout.addToLayout(this, addPlayer, 0, ++counter);
            EasyGridBagLayout.addToLayout(this, removePlayer, 1, counter);
            pack();
        }
        else if (args instanceof SetupModel.PlayerRemovedNotification){
            System.out.println("PlayerRemoved");
        }
    }
    
    /*
    public PlayerSetupView createView(){
    }*/
}