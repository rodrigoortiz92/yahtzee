import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.util.Observer;
import java.util.Observable;

public class PlayerView extends JPanel {

    private GameModel.PlayerDescription player;
    private PlayerType type;
    private SetupModel model;
    private PlayerSetupController controller;
    private JButton upButton;
    private JButton downButton;
    private JButton removeButton;

    public PlayerView(SetupModel model, Player playerModel) {
        super(new GridBagLayout()); 
        this.model = model;
        this.player = player;
        controller = new PlayerSetupController(model, player); 
        upButton = new JButton(controller.getUpButtonAction());
        downButton = new JButton(controller.getDownButtonAction());
        removeButton = new JButton(controller.getRemoveButtonAction());

        EasyGridBagLayout.addToLayout(this, new JLabel(player.name), 0, 0);
        EasyGridBagLayout.addToLayout(this, new JLabel(type.getName()), 0, 1);
        EasyGridBagLayout.addToLayout(this, upButton, 0, 2);
        EasyGridBagLayout.addToLayout(this, downButton, 0, 3);
        EasyGridBagLayout.addToLayout(this, removeButton, 0, 5);
    }
}
