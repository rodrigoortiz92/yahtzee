
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.Timer;

public class DiceView extends JPanel implements Observer {

    private DiceModel model;
    private DiceController controller;
    private DiceModel.DieValues oldValues;
    private Die dice[];
    private JButton rollButton;
    private JToggleButton lockButtons[];
    private DiceAnimation animation;

    public DiceView(DiceModel model) {
        super(new GridBagLayout());
        this.model = model;
        model.addObserver(this);

        dice = new Die[model.getDieCount()];
        lockButtons = new JToggleButton[model.getDieCount()];
        controller = new DiceController(this, model);
        rollButton = new JButton(controller.getRollAction());

        int i = dice.length;
        EasyGridBagLayout.addToLayout(this, rollButton, i, 0);
        while (i-- > 0) {
            dice[i] = new Die();
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
        updateButtons();
        //determines if this is the first time update() is called, and
        //chooses approppriate action (populate oldValues OR animate)
        DiceModel.DieValues values = model.getDieValues();

        if (values == null) {
            return;
        }

        if (oldValues == null) {
            oldValues = values;
            drawDice(values);
        }

        animation = new DiceAnimation(5, 100, model.getDieValues());

        for (int i = 0; i < model.getDieCount(); ++i) {
            lockButtons[i].setSelected(controller.isLocked(i));
            lockButtons[i].setEnabled(false);
        }

        rollButton.setEnabled(controller.isRollable());
    }

    private void updateButtons() {
        for (int i = 0; i < model.getDieCount(); ++i) {
            lockButtons[i].setSelected(controller.isLocked(i));
            lockButtons[i].setEnabled(controller.isLockable());
        }

        rollButton.setEnabled(controller.isRollable());
    }

    public void update(Observable o, Object arg) {
        if (arg instanceof DiceModel.RollNotification
                || arg instanceof DiceModel.ResetNotification) {
            update();
        }
    }

    private void drawDice(DiceModel.DieValues values) {
        int i = values.getValueCount();
        while (i-- > 0) {
            dice[i].setValue(values.valueAt(i));
        }
    }

    private class DiceAnimation implements ActionListener {

        private Timer timer;
        private DiceModel.DieValues currValues;
        private DiceModel.DieValues targetValues;
        private int round;
        private int totalRounds;

        public DiceAnimation(int totalRounds, int delay,
                DiceModel.DieValues targetValues) {
            timer = new Timer(delay, this);
            currValues = oldValues;
            this.targetValues = targetValues;
            this.totalRounds = totalRounds;
            timer.setRepeats(true);
            round = 0;
            timer.start();
        }

        public void actionPerformed(ActionEvent a) {
            if (round == totalRounds) {
                timer.stop();
                drawDice(targetValues);
                updateButtons();
                return;
            }
            int i = currValues.getValueCount();
            while (i-- > 0) {
                if (!controller.isLocked(i)) {
                    currValues.valueAt(i, (currValues.valueAt(i) + 1)
                            % DiceModel.DIE_MAX_VALUE + DiceModel.DIE_MIN_VALUE);
                }
            }
            round++;
            drawDice(currValues);
        }
    }
}
