
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.util.Observer;
import java.util.Observable;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class SetupView extends JDialog implements Observer {

    private JPanel playerList;
    private SetupController controller;
    private SetupModel model;
    private JButton startButton;
    private JButton cancelButton;
    private JCheckBox transferRolls;
    private JComboBox<TypeDescription> gameTypes;
    private PlayerAddPane addPane;
    private GameTypeSelectionPane gameTypeSelectionPane;

   

    public SetupView(SetupModel model, Window owner) {
        super(owner, ModalityType.APPLICATION_MODAL);

        this.model = model;
        model.addObserver(this);
        controller = new SetupController(this, model);

        Container content = getContentPane();

        content.setLayout(new GridBagLayout());

        GridBagConstraints gameTypeConstraints = new GridBagConstraints(0, 0, 2,
                1, 1, 0.5, GridBagConstraints.FIRST_LINE_START,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0);

        gameTypeSelectionPane = new GameTypeSelectionPane(model.getGameTypes());

        content.add(gameTypeSelectionPane, gameTypeConstraints);

        GridBagConstraints addPlayerConstraints = new GridBagConstraints(0, 1, 1,
                1, 1, 0.5, GridBagConstraints.FIRST_LINE_START,
                GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0);

        addPane = new PlayerAddPane(controller);

        content.add(addPane, addPlayerConstraints);

        playerList = new TitledPanel("Players");
        playerList.setLayout(new GridBagLayout());

        content.add(playerList,
                new GridBagConstraints(1, 1, 1, 1, 1, 0,
                GridBagConstraints.FIRST_LINE_START,
                GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));

        content.add(createBottomPanel(),
                new GridBagConstraints(0, 2, 2, 1, 1, 1,
                GridBagConstraints.LAST_LINE_START,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        pack();
    }

    private Component createGameTypePanel() {
        Component gameTypePanel = new TitledPanel("Game");

        setLayout(new GridBagLayout());

        transferRolls = new JCheckBox("Remaining rolls transfer to the next turn");

        gameTypes = new JComboBox<>();

        for (GameType type : model.getGameTypes()) {
            TypeDescription description = new TypeDescription(type.getName());
            gameTypes.addItem(description);
        }

        add(new JLabel("Game type"),
                new GridBagConstraints(0, 0, 1, 1, 1, 0,
                GridBagConstraints.FIRST_LINE_START,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2),
                0, 0));

        add(gameTypes,
                new GridBagConstraints(1, 0, 1, 1, 1, 0,
                GridBagConstraints.FIRST_LINE_START,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2),
                0, 0));

        add(transferRolls,
                new GridBagConstraints(0, 1, 2, 1, 1, 0,
                GridBagConstraints.FIRST_LINE_START,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2),
                0, 0));

        return gameTypePanel;
    }

    private Component createBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridBagLayout());

        bottomPanel.add(new JButton(controller.getCancelAction()),
                new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.FIRST_LINE_START,
                GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));

        bottomPanel.add(Box.createHorizontalGlue(),
                new GridBagConstraints(0, 0, 1, 1, 1, 0,
                GridBagConstraints.FIRST_LINE_START,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        bottomPanel.add(new JButton(controller.getStartAction()),
                new GridBagConstraints(2, 0, 1, 1, 0, 0,
                GridBagConstraints.FIRST_LINE_START,
                GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));

        return bottomPanel;
    }

    public void update(Observable o, Object args) {
        playerList.removeAll();

        int y = 0;

        Insets insets = new Insets(2, 2, 2, 2);

        for (GameModel.PlayerDescription desc : model.getPlayers()) {
            playerList.add(new JLabel(desc.name), new GridBagConstraints(0, y, 1, 1, 1, 0, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, insets, 0, 0));
            playerList.add(new JLabel(desc.type.getName()), new GridBagConstraints(1, y, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, insets, 0, 0));

            SetupController.PlayerActions actions = controller.getPlayerActions(desc);
            JButton upButton = new JButton(actions.getUpButtonAction());
            JButton downButton = new JButton(actions.getDownButtonAction());
            JButton removeButton = new JButton(actions.getRemoveButtonAction());

            playerList.add(upButton, new GridBagConstraints(2, y, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, insets, 0, 0));
            playerList.add(downButton, new GridBagConstraints(3, y, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, insets, 0, 0));
            playerList.add(removeButton, new GridBagConstraints(4, y, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, insets, 0, 0));

            y++;
        }

        if (model.isRollTransferSelected() != transferRolls.isEnabled()) {
            transferRolls.setEnabled(model.isRollTransferSelected());
        }

        if(model.getSelectedGameType() != gameTypes.getSelectedItem())
        {

        }

        validate();
    }

    public String getNewPlayerName() {
        return addPane.getName();
    }

    public PlayerType getNewPlayerType() {
        return addPane.getType();
    }

    public void setAddPlayerEnabled(boolean enabled) {
        addPane.paneEnabled(enabled);
    }
}
