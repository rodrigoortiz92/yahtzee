
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.AbstractAction;
import java.util.Observer;
import java.util.Observable;
import java.util.List;

public class SetupController implements Observer {

    private SetupView view;
    private SetupModel model;
    private AddPlayerAction addPlayerAction = new AddPlayerAction();
    private CancelAction cancelAction = new CancelAction();
    private StartAction startAction = new StartAction();
    private GameTypeOptionListener gameTypeOptionListener =
            new GameTypeOptionListener();

    

    public SetupController(SetupView view, SetupModel model) {
        this.view = view;
        this.model = model;
        model.addObserver(this);
        addPlayerAction = new AddPlayerAction();
    }

    public void update(Observable o, Object arg) {
        boolean enabled = false;
        if (model.getPlayers().size() < GameModel.MAX_PLAYER_COUNT) {
            enabled = true;
        }
        view.setAddPlayerEnabled(enabled);
    }

    public List<PlayerType> getPlayerTypes() {
        return model.getPlayerTypes();
    }

    public AddPlayerAction getAddPlayerAction() {
        return addPlayerAction;
    }

    public StartAction getStartAction() {
        return startAction;
    }

    public CancelAction getCancelAction() {
        return cancelAction;
    }

    public GameTypeOptionListener getGameTypeOptionListener() {
        return gameTypeOptionListener;
    }

    public class StartAction extends AbstractAction {

        public StartAction() {
            super("Start game");
        }

        public void actionPerformed(ActionEvent a) {

        }
    }

    public class CancelAction extends AbstractAction {

        public CancelAction() {
            super("Cancel");
        }

        public void actionPerformed(ActionEvent a) {
            SetupController.this.view.dispose();
        }
    }

    public class AddPlayerAction extends AbstractAction {

        public AddPlayerAction() {
            super("Add player");
        }

        public void actionPerformed(ActionEvent a) {
            if (!model.addPlayer(view.getNewPlayerName(), view.getNewPlayerType())) {
                //JOptionPane
            }
        }
    }

    public class GameTypeOptionListener implements ItemListener
    {

        @Override
        public void itemStateChanged(ItemEvent e) {
            Object item = e.getItem();

            if(item instanceof )
        }

    }

    public PlayerActions getPlayerActions(GameModel.PlayerDescription player) {
        return new PlayerActions(player);
    }

    public class PlayerActions {

        private GameModel.PlayerDescription player;
        private MoveUpAction moveUpAction = new MoveUpAction();
        private MoveDownAction moveDownAction = new MoveDownAction();
        private RemoveAction removeAction = new RemoveAction();

        public PlayerActions(GameModel.PlayerDescription player) {
            this.player = player;
        }

        public MoveUpAction getUpButtonAction() {
            return moveUpAction;
        }

        public MoveDownAction getDownButtonAction() {
            return moveDownAction;
        }

        public RemoveAction getRemoveButtonAction() {
            return removeAction;
        }

        public class MoveUpAction extends AbstractAction {

            public MoveUpAction() {
                super("^");
            }

            public void actionPerformed(ActionEvent a) {
                model.movePlayerUp(player);
            }
        }

        public class MoveDownAction extends AbstractAction {

            public MoveDownAction() {
                super("v");
            }

            public void actionPerformed(ActionEvent a) {
                model.movePlayerDown(player);
            }
        }

        public class RemoveAction extends AbstractAction {

            public RemoveAction() {
                super("x");
            }

            public void actionPerformed(ActionEvent a) {
                model.removePlayer(player);
            }
        }
    }
}
