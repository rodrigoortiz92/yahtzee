import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.AbstractButton;
import java.util.List;
import java.util.Hashtable;
import java.util.Enumeration;

public class PlayerAddPane extends JPanel {
    private JTextField nameField;
    private ButtonGroup typeSelectionGroup;
    private List<JRadioButton> buttons;
    private Hashtable<ButtonModel, PlayerType> typeTable;
    private JButton addPlayerButton;
    private SetupController controller;

    public PlayerAddPane(List<PlayerType> types, SetupController controller){
        int i = 1;
        this.controller = controller;
        typeTable = new Hashtable<>();
        addPlayerButton = new JButton(controller.getAddPlayerButtonAction());
        EasyGridBagLayout.addToLayout(this, nameField, 0, 0);

        for (PlayerType type : types) {
            JRadioButton button = new JRadioButton(type.getName());
            typeTable.put(button.getModel(), type);
            typeSelectionGroup.add(button);
            EasyGridBagLayout.addToLayout(this, button, i++, 0);
        }

        EasyGridBagLayout.addToLayout(this, addPlayerButton, i, 0);
    }
    public String getName(){
        return nameField.getText();
    }

    public void paneEnabled(boolean enabled){
        nameField.setEnabled(enabled);
        addPlayerButton.setEnabled(enabled);
        for (Enumeration<AbstractButton> buttons = 
                typeSelectionGroup.getElements(); buttons.hasMoreElements();){
            buttons.nextElement().setEnabled(enabled);
        }
    }
}
