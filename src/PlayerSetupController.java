import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import javax.swing.AbstractAction;

public class PlayerSetupController {

    private SetupModel model;
    private PlayerSetupView view;


    public PlayerSetupController(PlayerSetupView view, PlayerSetupModel model) {
        this.view = view;
        this.model = model;
    }

    public class AddPlayerAction extends AbstractAction {

        public void actionPerformed(ActionEvent a){

        }
    }
}
