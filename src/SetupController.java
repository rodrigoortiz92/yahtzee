/**
*
*@author Erkki Mattila
*/

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import java.util.Observer;
import java.util.Observable;

public class SetupController implements Observer {
    
    SetupView view;
    SetupModel model;
    private NewPlayerAction newPlayerAction = new NewPlayerAction();
    private RemovePlayerAction removePlayerAction = new RemovePlayerAction();
    
    public SetupController(SetupView view, SetupModel model) {
        this.view = view;
        this.model = model;
        this.model.addObserver(this);
    }
    
    public void update(Observable o, Object args){
        System.out.println("SetupController observes!");
    }
    
    public NewPlayerAction getNewPlayerAction() {
        return newPlayerAction;
    }

    public class NewPlayerAction extends AbstractAction {
    
        public NewPlayerAction(){
            super("Add player");
        }
        
        public void actionPerformed(ActionEvent e) {
            model.addPlayer();
        }
    }
    
    public RemovePlayerAction getRemovePlayerAction() {
        return removePlayerAction;
    }
    
    public class RemovePlayerAction extends AbstractAction {
    
        public RemovePlayerAction(){
            super("Remove player");
        }
        
        public void actionPerformed(ActionEvent e) {
            model.removePlayer();
        }
    }
}