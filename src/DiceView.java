import java.util.Observer;
import java.util.Observable;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.JToggleButton;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import javax.swing.JComponent;

public class DiceView extends JPanel implements Observer {
    private DiceModel model;
    private DiceController controller;
    private DiceModel.DieValues values;
    private JComponent dice[];

    private JButton rollButton;
    private JToggleButton lockButtons[];
    private Face face;

    public DiceView(DiceModel model){
        super(new GridBagLayout());
        this.model = model;
        model.addObserver(this);
        dice = new JComponent[model.getDieCount()];
        lockButtons = new JToggleButton[model.getDieCount()];
        controller = new DiceController(this, model);
        rollButton = new JButton("roll");
        rollButton.addActionListener(controller.getRollAction());
        face = new Face();

        int i = dice.length;
        EasyGridBagLayout.addToLayout(this, rollButton, i, 0);
        while (i-- > 0){
            dice[i] = face.getFace(1);
            lockButtons[i] = new JToggleButton("lock");
            lockButtons[i].addActionListener(controller.getLockAction(i));

            EasyGridBagLayout.addToLayout(this, dice[i], i, 0);
            EasyGridBagLayout.addToLayout(this, lockButtons[i], i, 1);
        }
    }

    public void update(Observable o, Object arg) {
        if (arg instanceof DiceModel.RollNotification) {
            DiceModel.RollNotification n = (DiceModel.RollNotification) arg;
            DiceModel.DieValues values = n.values;

            int i = values.getValueCount();
            while (i-- > 0) {
                remove(dice[i]);
            }
            validate();
            i = values.getValueCount();
            Face temp = new Face();
            while (i-- > 0) {
                dice[i] = face.getFace(values.valueAt(i));

                EasyGridBagLayout.addToLayout(this, dice[i], i, 0);
                lockButtons[i].setSelected(controller.isLocked(i));
                lockButtons[i].setEnabled(controller.isLockable());
            }
            rollButton.setEnabled(controller.isRollable());
            validate();
        }
        if (arg instanceof DiceModel.ResetNotification) {
            for (int i = 0; i < model.getDieCount(); ++i) {
                lockButtons[i].setSelected(controller.isLocked(i));
                lockButtons[i].setEnabled(controller.isLockable());
            }
            rollButton.setEnabled(controller.isRollable());
        }
    }
}
