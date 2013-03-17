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
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DiceView extends JPanel implements Observer {
    private DiceModel model;
    private DiceController controller;
    private JComponent dice[];
    private DiceModel.DieValues oldValues;

    private JButton rollButton;
    private JToggleButton lockButtons[];
    private Face face;

    private DiceAnimation animation;

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
        oldValues = null;

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

    public void update(Observable o, Object arg){
        DiceModel.DieValues values = (DiceModel.DieValues)arg;


        int i = values.getValueCount();

        while (i-- > 0){
            lockButtons[i].setSelected(controller.isLocked(i));
            lockButtons[i].setEnabled(false);
        }
        //determines if this is the first time update() is called, and
        //chooses approppriate action (populate oldValues OR animate)
        if (oldValues == null){
            oldValues = values;
            drawDice(values);
        }
        else {
            animation = new DiceAnimation(10, 50, values);
        }
    }

    private void setButtons(){
        int i = oldValues.getValueCount();
        while (i-- > 0){
            lockButtons[i].setSelected(controller.isLocked(i));
            lockButtons[i].setEnabled(controller.isLockable());
        }
        rollButton.setEnabled(controller.isRollable());
    }

    private void drawDice(DiceModel.DieValues values){
        int i = values.getValueCount();
        while (i-- > 0){
            remove(dice[i]);
            dice[i] = face.getFace(values.valueAt(i));
            EasyGridBagLayout.addToLayout(this, dice[i], i, 0);
        }
        validate();
    }

    private class DiceAnimation implements ActionListener {
        private Timer timer;
        private DiceModel.DieValues currValues;
        private DiceModel.DieValues targetValues;
        private int round;
        private int totalRounds;

        public DiceAnimation (int totalRounds, int delay,
                DiceModel.DieValues targetValues){
            timer = new Timer(delay, this);
            currValues = oldValues;
            this.targetValues = targetValues;
            this.totalRounds = totalRounds;
            timer.setRepeats(true);
            round = 0;
            timer.start();
        }

        public void actionPerformed(ActionEvent a){
            if (round == totalRounds){
                timer.stop();
                drawDice(targetValues);
                setButtons();
                return;
            }
            int i = currValues.getValueCount();
            while (i-- > 0){
                if (!controller.isLocked(i)){
                    currValues.valueAt(i, (currValues.valueAt(i) + 1) %
                            DiceModel.DIE_MAX_VALUE + DiceModel.DIE_MIN_VALUE);
                }
            }
            round++;
            drawDice(currValues);
        }
    }
}
