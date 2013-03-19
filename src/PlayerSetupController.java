import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import javax.swing.AbstractAction;
import java.util.Observer;
import java.util.Observable;
import java.util.List;

public class PlayerSetupController implements Observer {

    private SetupModel model;
    private GameModel.PlayerDescription player;

    private DownButtonAction downButtonAction;
    private UpButtonAction upButtonAction;
    private RemoveButtonAction removeButtonAction;


    public PlayerSetupController(SetupModel model,
            GameModel.PlayerDescription player) {

        this.model = model;

        upButtonAction = new UpButtonAction();
        downButtonAction = new DownButtonAction();
        removeButtonAction = new RemoveButtonAction();
    }

    public UpButtonAction getUpButtonListener() {
        return upButtonAction;
    }

    public DownButtonAction getDownButtonListener() {
        return downButtonAction;
    }

    public RemoveButtonAction getRemoveButtonListener(){
        return removeButtonAction;
    }

    public class UpButtonAction extends AbstractAction {
        public void actionPerformed(ActionEvent a){
            model.movePlayerUp(player);
        }
    }

    public class DownButtonAction extends AbstractAction {
        public void actionPerformed(ActionEvent a){
            model.movePlayerDown(player);
        }
    }

    public class RemoveButtonAction extends AbstractAction {
        public void actionPerformed(ActionEvent a){
            model.removePlayer(player);
        }
    }

    public void update(Observable o, Object arg){
        List<GameModel.PlayerDescription> players = model.getPlayers();
        int i = players.indexOf(player);

        downButtonAction.setEnabled(i < players.size() - 1);
        upButtonAction.setEnabled(i != 0);
    }
}
