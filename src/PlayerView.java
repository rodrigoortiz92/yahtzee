import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.util.Observer;
import java.util.Observable;

public class PlayerView extends JPanel {

    private Player playerModel;
    private playerType type;
    private SetupModel setupModel;
    private PlayerSetupController controller;
    private JButton upButton;
    private JButton downButton;
    private JButton removeButton;

    public PlayerView(PlayerSetupModel setupModel, Player playerModel) {
        super(new GridBagLayout()); 
        this.setupModel = setupModel;
        this.playerModel = playerModel;
        controller = new PlayerController(this, model);

        EasyGridBagLayout.addToLayout(this, playerModel.getName(), 0, 0);
        EasyGridBagLayout.addToLayout(this, type.getName(), 0, 1);
        EasyGridBagLayout.addToLayout(this, upButton, 0, 2);
        EasyGridBagLayout.addToLayout(this, downButton, 0, 3);
        EasyGridBagLayout.addToLayout(this, deleteButton, 0, 3);
    }
}
