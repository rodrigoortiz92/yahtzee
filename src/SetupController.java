
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedList;
import javax.swing.AbstractAction;
import java.util.Observer;
import java.util.Observable;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class SetupController implements Observer {

    private SetupView view;
    private SetupModel model;
    private CancelAction cancelAction = new CancelAction();
    private StartAction startAction = new StartAction();
    private GameTypeOptionListener gameTypeOptionListener =
            new GameTypeOptionListener();
    private TransferRollsOptionListener transferRollsOptionListener =
            new TransferRollsOptionListener();

    public SetupController(SetupView view, SetupModel model) {
        this.view = view;
        this.model = model;
        model.addObserver(this);
    }

    public void update(Observable o, Object arg) {
        boolean enabled = model.getPlayers().size() < GameModel.MAX_PLAYER_COUNT;

        view.setAddPlayerEnabled(enabled);
    }

    public List<PlayerType> getPlayerTypes() {
        return model.getPlayerTypes();
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

    public TransferRollsOptionListener getTransferRollsOptionListener() {
        return transferRollsOptionListener;
    }

    public class StartAction extends AbstractAction {

        public StartAction() {
            super("Start game");
        }

        public void actionPerformed(ActionEvent a) {
            if (model.getPlayers().size() < 2) {
                JOptionPane.showMessageDialog(view,
                        "To start a game you need at least two players.",
                        "Too few players",
                        JOptionPane.INFORMATION_MESSAGE, null);

                return;
            }

            model.startGame();
            SetupController.this.view.dispose();
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

    public class GameTypeOptionListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            Object item = e.getItem();

            if (item instanceof SetupModel.GameTypeOption) {
                SetupModel.GameTypeOption option = (SetupModel.GameTypeOption) item;
                model.setSelectedGameType(option.type);
            }
        }
    }

    public class TransferRollsOptionListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            boolean selected = (e.getStateChange() == ItemEvent.SELECTED);

            model.setRollTransferSelected(selected);
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

            if (player != null) {
                List<GameModel.PlayerDescription> players = model.getPlayers();
                int i = players.indexOf(player);

                moveDownAction.setEnabled(i < players.size() - 1);
                moveUpAction.setEnabled(i != 0);
            } else {
                moveDownAction.setEnabled(false);
                moveUpAction.setEnabled(false);
                removeAction.setEnabled(false);
            }
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
                putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/images/up.png")));
            }

            public void actionPerformed(ActionEvent a) {
                model.movePlayerUp(player);
            }
        }

        public class MoveDownAction extends AbstractAction {

            public MoveDownAction() {
                putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/images/down.png")));
            }

            public void actionPerformed(ActionEvent a) {
                model.movePlayerDown(player);
            }
        }

        public class RemoveAction extends AbstractAction {

            public RemoveAction() {
                putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/images/remove.png")));
            }

            public void actionPerformed(ActionEvent a) {
                model.removePlayer(player);
            }
        }
    }
}
