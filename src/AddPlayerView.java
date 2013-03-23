
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class AddPlayerView extends TitledPanel implements Observer {

    private JTextField nameField;
    private ButtonGroup typeSelectionGroup;
    private HashMap<ButtonModel, PlayerType> typeTable;
    private JButton addPlayerButton;
    private AddPlayerController controller;
    private AddPlayerModel model;

    public AddPlayerView(AddPlayerModel model) {
        super("Add player");
        controller = new AddPlayerController(this, model);
        typeSelectionGroup = new ButtonGroup();
        typeTable = new HashMap<>();
        addPlayerButton = new JButton(controller.getAddPlayerAction());
        nameField = new JTextField(10);
        this.model = model;

        setLayout(new GridBagLayout());

        add(new JLabel("Name"), new GridBagConstraints(0, 0, 1, 1, 1, 0,
                GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        nameField.getDocument().addDocumentListener(controller.getNameListener());
        add(nameField, new GridBagConstraints(1, 0, 1, 1, 1, 0,
                GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        TitledPanel typePanel = new TitledPanel("Type");
        typePanel.setLayout(new BoxLayout(typePanel, BoxLayout.Y_AXIS));

        add(typePanel, new GridBagConstraints(0, 1, 2, 1, 1, 0,
                GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        for (PlayerType type : model.getPlayerTypes()) {
            JRadioButton button = new JRadioButton(type.getName());
            typeTable.put(button.getModel(), type);
            typeSelectionGroup.add(button);
            typePanel.add(button);
            button.addActionListener(controller.getTypeOptionAction());
        }

        add(Box.createVerticalGlue(), new GridBagConstraints(0, 2, 2, 1, 1, 1,
                GridBagConstraints.LINE_START,
                GridBagConstraints.VERTICAL, new Insets(2, 2, 2, 2), 0, 0));

        add(addPlayerButton, new GridBagConstraints(0, 3, 2, 1, 1, 0,
                GridBagConstraints.LINE_START,
                GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));

        update();

        model.addObserver(this);
    }

    public String getName() {
        return nameField.getText();
    }

    public PlayerType getType() {
        if (typeSelectionGroup.getSelection() == null) {
            return null;
        }

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

    private void update() {
        if (!nameField.getText().equals(model.getName())) {
            nameField.setText(model.getName());
        }

        typeSelectionGroup.clearSelection();

        for (Map.Entry<ButtonModel, PlayerType> entry : typeTable.entrySet()) {
            if (entry.getValue() == model.getPlayerType()) {
                entry.getKey().setSelected(true);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        update();
    }
}
