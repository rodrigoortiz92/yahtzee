import java.util.Observer;
import java.util.Observable;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.JToggleButton;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;

public class DiceView extends JPanel implements Observer {
    private DiceModel model;
    private DiceController controller;
    private DiceModel.DieValues values;
    private Die dice[];

    private JButton rollButton;
    private JToggleButton lockButtons[];

    public DiceView(DiceModel model){
        super(new GridBagLayout());
        this.model = model;
        model.addObserver(this);
        controller = new DiceController(this, model);
        dice = new Die[model.getDieCount()];
        lockButtons = new JToggleButton[model.getDieCount()];
        rollButton = new JButton("roll");

        int i = dice.length;
        EasyGridBagLayout.addToLayout(this, rollButton, i, 0);
        while (i-- > 0){
            dice[i] = new Die(model.DIE_MAX_VALUE);
            lockButtons[i] = new JToggleButton("Lock");

            EasyGridBagLayout.addToLayout(this, dice[i], i, 0);
            EasyGridBagLayout.addToLayout(this, lockButtons[i], i, 1);
        }
    }

    public void update(Observable o, Object arg){
        DiceModel.DieValues values = (DiceModel.DieValues)arg;

        int i = values.getValueCount();
        while (i-- > 0){
            dice[i].setValue(values.valueAt(i));
            lockButtons[i].setEnabled(controller.isLockable());
        }
        rollButton.setEnabled(controller.isRollable());
        System.out.println("?");
    }

    private class Die extends JLabel {
        private int maxValue;
        private int currValue;
        //private icon facet[];

        public Die(int max){
            super("2");
            maxValue = max;
            //facet = new icon[maxValue];

            int i = 0;
            /*while (i-- <= maxValue){
                facet[i] = new ImageIcon("images/facet" + (i + 1) + ".png");
            }*/
        }

        public void setValue(int v){
            currValue = v;
            super.setText(Integer.toString(v));
        }
    }
}
