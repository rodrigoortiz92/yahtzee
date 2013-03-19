import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.util.List;
import java.util.Hashtable;

public class PlayerAddPane extends JPanel {
    private JTextField nameField;
    private ButtonGroup typeSelectionGroup;
    private List<JRadioButton> buttons;
    private Hashtable typeTable;
    private JButton addPlayerButton;
    private SetupController controller;

    public PlayerAddPane(List<PlayerType> types, SetupController controller){
        int i = 1;
        this.controller = controller;
        typeTable = new Hashtable<ButtonModel, PlayerType>();
        addPlayerButton = new JButton(controller.getAddPlayerAction());
        EasyGridBagLayout.addToLayout(this, nameField, 0, 0);

        for (PlayerType type : types) {
            JRadioButton button = new JRadioButton(type.getName());
            typeTable.put(button.getModel(), type);
            typeSelectionGroup.add(button);
            EasyGridBagLayout.addToLayout(this, button, i++, 0);
        }

        EasyGridBagLayout.addToLayout(this, addPlayerButton, i, 0);






    }
}
