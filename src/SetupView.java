
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
    private JCheckBox transferRolls;
    private JComboBox<SetupModel.GameTypeOption> gameTypes;
    private AddPlayerView addPlayerView;

    public SetupView(SetupModel model, AddPlayerView addPlayerView,
            Window owner) {
        super(owner, ModalityType.APPLICATION_MODAL);
        setTitle("New game");

        this.model = model;
        this.addPlayerView = addPlayerView;
        model.addObserver(this);
        controller = new SetupController(this, model);

        Container content = getContentPane();

        content.setLayout(new GridBagLayout());

        content.add(createGameTypePanel(),
                new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.FIRST_LINE_START,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 0, 0), 0, 0));

        content.add(addPlayerView,
                new GridBagConstraints(0, 1, 1, 1, 0, 1,
                GridBagConstraints.FIRST_LINE_START,
                GridBagConstraints.BOTH, new Insets(0, 2, 0, 0), 0, 0));

        playerList = new TitledPanel("Players");

        content.add(playerList,
                new GridBagConstraints(1, 0, 1, 2, 1, 1,
                GridBagConstraints.FIRST_LINE_START,
                GridBagConstraints.BOTH, new Insets(2, 2, 0, 0), 0, 0));


        content.add(createBottomPanel(),
                new GridBagConstraints(0, 2, 2, 1, 1, 0,
                GridBagConstraints.LAST_LINE_START,
                GridBagConstraints.HORIZONTAL, new Insets(0, 2, 2, 2), 0, 0));



        update();

        pack();
        setMinimumSize(getSize());
        setLocationRelativeTo(owner);
    }

    private Component createGameTypePanel() {
        Container gameTypePanel = new TitledPanel("Game type");

        gameTypePanel.setLayout(new GridBagLayout());

        transferRolls = new JCheckBox("Remaining rolls transfer to the next turn");
        transferRolls.addItemListener(
                controller.getTransferRollsOptionListener());
        gameTypes = new JComboBox<>();

        for (SetupModel.GameTypeOption option : model.getGameTypeOptions()) {
            gameTypes.addItem(option);
        }

        gameTypes.addItemListener(controller.getGameTypeOptionListener());

        gameTypePanel.add(gameTypes,
                new GridBagConstraints(0, 0, 1, 1, 1, 0,
                GridBagConstraints.FIRST_LINE_START,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2),
                0, 0));

        gameTypePanel.add(transferRolls,
                new GridBagConstraints(0, 1, 1, 1, 1, 0,
                GridBagConstraints.FIRST_LINE_START,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2),
                0, 0));

        return gameTypePanel;
    }

    private Component createBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridBagLayout());

        bottomPanel.add(new JButton(controller.getStartAction()),
                new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.FIRST_LINE_START,
                GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));

        bottomPanel.add(Box.createHorizontalGlue(),
                new GridBagConstraints(0, 0, 1, 1, 1, 0,
                GridBagConstraints.FIRST_LINE_START,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        bottomPanel.add(new JButton(controller.getCancelAction()),
                new GridBagConstraints(2, 0, 1, 1, 0, 0,
                GridBagConstraints.FIRST_LINE_START,
                GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));

        return bottomPanel;
    }

    private void update() {
        playerList.removeAll();

        JPanel tablePanel = new JPanel(new GridLayout(0, 3, 0, 0));

        playerList.setLayout(new BoxLayout(playerList, BoxLayout.Y_AXIS));

        int y = 0;

        Insets insets = new Insets(2, 0, 0, 0);

        tablePanel.add(new JLabel("Name"));
        tablePanel.add(new JLabel("Type"));
        tablePanel.add(new JLabel(""));

        y++;

        for (int i = 0; i < GameModel.MAX_PLAYER_COUNT; ++i) {
            GameModel.PlayerDescription desc = null;

            if (i < model.getPlayers().size()) {
                desc = model.getPlayers().get(i);
            }

            JLabel name = new JLabel();
            JLabel type = new JLabel();

            if (desc != null) {
                name.setText(desc.name);
                type.setText(desc.type.getName());
            }

            tablePanel.add(name);

            tablePanel.add(type);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

            SetupController.PlayerActions actions = controller.getPlayerActions(desc);
            JButton upButton = new JButton(actions.getUpButtonAction());
            JButton downButton = new JButton(actions.getDownButtonAction());
            JButton removeButton = new JButton(actions.getRemoveButtonAction());

            upButton.setMaximumSize(new Dimension(18, 18));
            downButton.setMaximumSize(new Dimension(18, 18));
            removeButton.setMaximumSize(new Dimension(18, 18));

            upButton.setPreferredSize(new Dimension(18, 18));
            downButton.setPreferredSize(new Dimension(18, 18));
            removeButton.setPreferredSize(new Dimension(18, 18));

            upButton.setMinimumSize(new Dimension(18, 18));
            downButton.setMinimumSize(new Dimension(18, 18));
            removeButton.setMinimumSize(new Dimension(18, 18));

            buttonPanel.add(Box.createHorizontalGlue());
            buttonPanel.add(upButton);
            buttonPanel.add(downButton);
            buttonPanel.add(removeButton);

            buttonPanel.setMaximumSize(new Dimension(75, 18));
            buttonPanel.setPreferredSize(new Dimension(75, 18));
            buttonPanel.setMinimumSize(new Dimension(75, 18));

            tablePanel.add(buttonPanel);

            y++;
        }

        playerList.add(tablePanel);
        playerList.add(Box.createVerticalGlue());

        if (model.isRollTransferSelected() != transferRolls.isEnabled()) {
            transferRolls.setSelected(model.isRollTransferSelected());
        }

        if (model.getSelectedGameTypeOption() != gameTypes.getSelectedItem()) {
            gameTypes.setSelectedItem(model.getSelectedGameTypeOption());
        }

        revalidate();
        repaint();
    }

    public void update(Observable o, Object args) {
        update();
    }

    public void setAddPlayerEnabled(boolean enabled) {
        addPlayerView.paneEnabled(enabled);
    }
}
