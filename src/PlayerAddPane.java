import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.util.List;
import java.util.Hashtable;

public class PlayerAddPane extends JFrame {
    private JTextField nameField;
    private ButtonGroup typeSelectionGroup;
    private List<JRadioButton> buttons;
    private Hashtable typeTable;

    public PlayerAddPane(List<PlayerType> types){
        typeTable = new Hashtable<ButtonModel, PlayerType>();

       for (PlayerType type : types) {
           JRadioButton button = new JRadioButton(type.getName());
           typeTable.put(button.getModel(), type);
           typeSelectionGroup.add(button);
       } 
    }
}
