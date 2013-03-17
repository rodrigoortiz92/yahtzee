
import java.awt.GridBagConstraints;
import java.util.Observer;
import java.util.Observable;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.JToggleButton;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
        rollButton = new JButton(controller.getRollAction());
        face = new Face();

        int i = dice.length;
        EasyGridBagLayout.addToLayout(this, rollButton, i, 0);
        while (i-- > 0){
            dice[i] = face.getFace(1);
            lockButtons[i] = new JToggleButton(controller.getLockAction(i));

            GridBagConstraints c = new GridBagConstraints(i, 0, 1, 1, 1, 1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE,
                    new Insets(5, 5, 5, 5), 0, 0);
            add(dice[i], c);

            GridBagConstraints c2 = new GridBagConstraints(i, 1, 1, 1, 1, 1,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE,
                    new Insets(5, 5, 5, 5), 0, 0);
            add(lockButtons[i], c2);


        }

        update();
    }

    private void update() {
        for (int i = 0; i < model.getDieCount(); ++i) {
            lockButtons[i].setSelected(controller.isLocked(i));
            lockButtons[i].setEnabled(controller.isLockable());
        }
        rollButton.setEnabled(controller.isRollable());

        DiceModel.DieValues values = model.getDieValues();

        if (values != null) {
            drawDice(values);
        }
    }

    public void update(Observable o, Object arg) {
        if (arg instanceof DiceModel.RollNotification
                || arg instanceof DiceModel.ResetNotification) {
            update();
        }
    }

    private void drawDice(DiceModel.DieValues values){
        int i = values.getValueCount();
        while (i-- > 0){
            remove(dice[i]);
            dice[i] = face.getFace(values.valueAt(i));

            GridBagConstraints c = new GridBagConstraints(i, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0);

            add(dice[i], c);
        }
        validate();
    }
}
