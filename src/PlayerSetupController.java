import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.AbstractAction;

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

    public UpButtonAction getUpButtonAction() {
        return upButtonAction;
    }

    public DownButtonAction getDownButtonAction() {
        return downButtonAction;
    }

    public RemoveButtonAction getRemoveButtonAction(){
        return removeButtonAction;
    }

    public class UpButtonAction extends AbstractAction {
        public UpButtonAction(){
            super("^");
        }

        public void actionPerformed(ActionEvent a){
            model.movePlayerUp(player);
        }
    }

    public class DownButtonAction extends AbstractAction {
        public DownButtonAction(){
            super("v");
        }

        public void actionPerformed(ActionEvent a){
            model.movePlayerDown(player);
        }
    }

    public class RemoveButtonAction extends AbstractAction {
        public RemoveButtonAction(){
            super("x");
        }

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
