import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import javax.swing.AbstractAction;

public class PlayerSetupController {

    private PlayerSetupModel model;
    private PlayerSetupView view;


    public PlayerSetupController(PlayerSetupView view, PlayerSetupModel model) {
        this.view = view;
        this.model = model;
    }

    public class NameChangedAction extends AbstractAction {

        public void actionPerformed(ActionEvent a){

        }
    }

    public class TypeChangedAction extends AbstractAction {

        public void actionPerformed(ActionEvent a){

        }
    }
}
