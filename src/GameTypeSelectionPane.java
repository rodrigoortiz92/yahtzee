
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.util.List;
import java.util.Hashtable;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

public class GameTypeSelectionPane extends TitledPanel {


    private Hashtable<TypeDescription, GameType> typeTable;

    public GameTypeSelectionPane(List<GameType> types) {
        super("Game");

        setLayout(new GridBagLayout());

        typeTable = new Hashtable<>();
        transferRolls = new JCheckBox("Remaining rolls transfer to the next turn");
        gameTypes = new JComboBox<>();

        for (GameType type : types) {
            TypeDescription description = new TypeDescription(type.getName());
            typeTable.put(description, type);
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
    }


}
