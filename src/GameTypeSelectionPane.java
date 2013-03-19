import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JCheckbox;
import java.util.List;
import java.util.Hashtable;

public class GameTypeSelectionPane extends JPanel {
    private JCheckbox transferRolls;
    private JComboBox gameTypes;
    private Hashtable<TypeDescription, GameType> typeTable;

    public GameTypeSelectionPane(List<GameType> types){
        super(new GridBagLayout());
        typeTable = new Hashtable<>();
        transferRolls = new JComboBox();
        gameTypes = new JComboBox();

        for (GameType type : types){
            TypeDescription description = new TypeDescription(type.getName());
            typeTable.put(description, type);
            gameTypes.add(description);
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
