import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import java.util.Observer;
import java.util.Observable;
import java.util.List;

public class SetupController implements Observer {

    private SetupView view;
    private SetupModel model;
    private PlayerAddPane addPane;
    private AddPlayerButtonAction addPlayerButtonAction;

    public SetupController(SetupView view, SetupModel model) {
        this.view = view;
        this.model = model;
        this.model.addObserver(this);
        addPlayerButtonAction = new AddPlayerButtonAction();
    }

    public void update (Observable o, Object arg){
        boolean enabled = false;
        if (model.getPlayers().size() < GameModel.MAX_PLAYER_COUNT){
            enabled = true;
        }
        addPane.paneEnabled(enabled);
    }

    public AddPlayerButtonAction getAddPlayerButtonAction(){
        return addPlayerButtonAction;
    }

    public class AddPlayerButtonAction extends AbstractAction {
        public AddPlayerButtonAction(){
            super("Add player");
        }

        public void actionPerformed(ActionEvent a){
            boolean unique = true;
            List<GameModel.PlayerDescription> players = model.getPlayers();

            for (GameModel.PlayerDescription player : players){
                if (player.name == addPane.getName()){
                    unique = false;
                    break;
                }
            }
            if (!unique){
                System.exit(1);
            }
        }
    }
}
