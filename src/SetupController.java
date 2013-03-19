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

    public AddPlayerButtonAction getAddPlayerButtonAction(){
        return addPlayerButtonAction;

    }
}
