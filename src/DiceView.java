
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.Timer;

public class DiceView extends JPanel implements Observer {

    private DiceModel model;
    private DiceController controller;
    private Die dice[];
    private JButton rollButton;
    private JToggleButton lockButtons[];
    private List<DiceAnimation> animations;

    public DiceView(DiceModel model) {
        super(new GridBagLayout());
        this.model = model;
        model.addObserver(this);

        dice = new Die[model.getDieCount()];
        lockButtons = new JToggleButton[model.getDieCount()];
        controller = new DiceController(this, model);
        rollButton = new JButton(controller.getRollAction());
        animations = new LinkedList<>();

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

        for (DiceAnimation animation : animations) {
            animation.stop();
        }

        animations.clear();

        DiceModel.DieValues values = model.getDieValues();

        if (values != null) {
            drawDice(values);
        }
    }

    public void animate(int time) {
        DiceModel.DieValues values = model.getDieValues();

        for (int i = 0; i < model.getDieCount(); ++i) {
            int old = DiceModel.DIE_MIN_VALUE;

            if (values != null) {
                old = model.getDieValues().valueAt(i);
            }

            animations.add(new DiceAnimation(old, i));

            lockButtons[i].setSelected(controller.isLocked(i));
            lockButtons[i].setEnabled(false);
        }
    }

    private void updateButtons() {
        for (int i = 0; i < model.getDieCount(); ++i) {
            updateButton(i);
        }

        rollButton.setEnabled(controller.isRollable() && model.acceptsUserInput());
    }

    private void updateButton(int i) {
        lockButtons[i].setSelected(controller.isLocked(i));
        lockButtons[i].setEnabled(controller.isLockable() && model.acceptsUserInput());
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
        private int value;
        private int index;
        private final int DELAY = 100;

        public DiceAnimation(int value, int index) {
            timer = new Timer(DELAY, this);
            this.value = value;
            this.index = index;

            timer.setRepeats(true);
            timer.start();
        }

        public void actionPerformed(ActionEvent a) {
            value = (value + 1) % DiceModel.DIE_MAX_VALUE + DiceModel.DIE_MIN_VALUE;
            dice[index].setValue(value);
        }

        public void stop() {
            timer.stop();
        }
    }
}
