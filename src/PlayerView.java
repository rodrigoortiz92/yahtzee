import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Observer;
import java.util.Observable;

public class PlayerView extends JPanel {

    private GameModel.PlayerDescription player;
    private SetupModel model;
    private PlayerSetupController controller;
    private JButton upButton;
    private JButton downButton;
    private JButton removeButton;

    public PlayerView(SetupModel model, GameModel.PlayerDescription playerModel) {
        super(new GridBagLayout());
        this.model = model;
        this.player = playerModel;
        controller = new PlayerSetupController(model, player);
        upButton = new JButton(controller.getUpButtonAction());
        downButton = new JButton(controller.getDownButtonAction());
        removeButton = new JButton(controller.getRemoveButtonAction());

        Insets insets = new Insets(2, 2, 2, 2);

        add(new JLabel(player.name), new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, insets, 0, 0));
        add(new JLabel(player.type.getName()), new GridBagConstraints(1, 0, 1, 1, 1, 0, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, insets, 0, 0));
        add(upButton, new GridBagConstraints(2, 0, 1, 1, 1, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, insets, 0, 0));
        add(downButton, new GridBagConstraints(3, 0, 1, 1, 1, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, insets, 0, 0));
        add(removeButton, new GridBagConstraints(4, 0, 1, 1, 1, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, insets, 0, 0));

    }
}
