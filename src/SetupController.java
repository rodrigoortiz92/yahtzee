import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import java.util.Observer;
import java.util.Observable;
import java.util.List;

public class SetupController implements Observer {

    private SetupView view;
    private SetupModel model;
    private AddPlayerButtonAction addPlayerButtonAction;
    private CancelButtonAction cancelButtonAction;
    private StartButtonAction startButtonAction;

    public SetupController(SetupView view, SetupModel model) {
        this.view = view;
        this.model = model;
        model.addObserver(this);
        addPlayerButtonAction = new AddPlayerButtonAction();
    }

    public void update (Observable o, Object arg){
        boolean enabled = false;
        if (model.getPlayers().size() < GameModel.MAX_PLAYER_COUNT){
            enabled = true;
        }
        view.setAddPlayerEnabled(enabled);
    }

    public List<PlayerType> getPlayerTypes(){
        return model.getPlayerTypes();
    }

    public AddPlayerButtonAction getAddPlayerButtonAction(){
        return addPlayerButtonAction;
    }

    public StartButtonAction getStartButtonAction(){
        return startButtonAction;
    }

    public CancelButtonAction getCancelButtonAction(){
        return cancelButtonAction;
    }

    public class StartButtonAction extends AbstractAction {
        public StartButtonAction(){
            super("Start game");
        }

        public void actionPerformed(ActionEvent a){

        }
    }

    public class CancelButtonAction extends AbstractAction {
        public CancelButtonAction(){
            super("Cancel");
        }

        public void actionPerformed(ActionEvent a){

        }
    }

    public class AddPlayerButtonAction extends AbstractAction {
        public AddPlayerButtonAction(){
            super("Add player");
        }

        public void actionPerformed(ActionEvent a){
            boolean unique = true;
            List<GameModel.PlayerDescription> players = model.getPlayers();

            for (GameModel.PlayerDescription player : players){
                if (player.name == view.getNewPlayerName()){
                    unique = false;
                    break;
                }
            }
            if (!unique){
                System.exit(1);
            }
            model.addPlayer(view.getNewPlayerName(), view.getNewPlayerType());
        }
    }
}
