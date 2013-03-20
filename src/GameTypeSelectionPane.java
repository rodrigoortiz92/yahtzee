import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.util.List;
import java.util.Hashtable;
import javax.swing.JCheckBox;

public class GameTypeSelectionPane extends JPanel {
    private JCheckBox transferRolls;
    private JComboBox gameTypes;
    private Hashtable<TypeDescription, GameType> typeTable;

    public GameTypeSelectionPane(List<GameType> types){
        super(new GridBagLayout());
        typeTable = new Hashtable<>();
        transferRolls = new JCheckBox();
        gameTypes = new JComboBox();

        for (GameType type : types){
            TypeDescription description = new TypeDescription(type.getName());
            typeTable.put(description, type);
            gameTypes.addItem(description);
        }
        EasyGridBagLayout.addToLayout(this, gameTypes, 0, 0);
        EasyGridBagLayout.addToLayout(this, transferRolls, 1, 0);
    }

    public class TypeDescription {
        public String name;

        public TypeDescription(String name){
            this.name = name;
        }

        public String toString(){
            return name;
        }
    }
}
