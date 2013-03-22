
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import javax.swing.BoxLayout;
import javax.swing.JLabel;

public class PlayerAddPane extends TitledPanel {

    private JTextField nameField;
    private ButtonGroup typeSelectionGroup;
    private List<JRadioButton> buttons;
    private Hashtable<ButtonModel, PlayerType> typeTable;
    private JButton addPlayerButton;
    private SetupController controller;

    public PlayerAddPane(SetupController controller) {
        super("Add player");
        setLayout(new GridBagLayout());
        int i = 1;
        this.controller = controller;
        typeSelectionGroup = new ButtonGroup();
        typeTable = new Hashtable<>();
        addPlayerButton = new JButton(controller.getAddPlayerAction());
        nameField = new JTextField(10);

        add(new JLabel("Name"), new GridBagConstraints(0, 0, 1, 1, 1, 0,
                GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        add(nameField, new GridBagConstraints(1, 0, 1, 1, 1, 0,
                GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        TitledPanel typePanel = new TitledPanel("Type");
        typePanel.setLayout(new BoxLayout(typePanel, BoxLayout.Y_AXIS));

        add(typePanel, new GridBagConstraints(0, 1, 2, 1, 1, 0,
                GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        for (PlayerType type : controller.getPlayerTypes()) {
            JRadioButton button = new JRadioButton(type.getName());
            typeTable.put(button.getModel(), type);
            typeSelectionGroup.add(button);
            typePanel.add(button);
        }

        add(addPlayerButton, new GridBagConstraints(0, 2, 2, 1, 1, 0,
                GridBagConstraints.PAGE_END,
                GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
    }

    public String getName() {
        return nameField.getText();
    }

    public PlayerType getType() {
        return typeTable.get(typeSelectionGroup.getSelection());
    }

    public void paneEnabled(boolean enabled) {
        nameField.setEnabled(enabled);
        addPlayerButton.setEnabled(enabled);
        for (Enumeration<AbstractButton> buttons =
                typeSelectionGroup.getElements(); buttons.hasMoreElements();) {
            buttons.nextElement().setEnabled(enabled);
        }
    }
}
