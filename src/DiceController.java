
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JToggleButton;
import javax.swing.Timer;

public class DiceController {

    private DiceModel model;
    private DiceView view;

    public DiceController(DiceView view, DiceModel model) {
        this.view = view;
        this.model = model;
    }

    public boolean isLockable() {
        return model.canDiceBeLocked();
    }

    public boolean isRollable() {
        return model.canDiceBeRolled();
    }

    public boolean isLocked(int i) {
        return model.isDieLocked(i);
    }

    public Action getRollAction() {
        return new RollAction();
    }

    public Action getLockAction(int dieId) {
        return new LockAction(dieId);
    }

    public class RollAction extends AbstractAction {

        public RollAction() {
            super("Roll");
        }

        public void actionPerformed(ActionEvent a) {
            final int ANIMATION_TIME = 500;

            DiceController.this.view.animate(ANIMATION_TIME);
            Timer timer = new Timer(ANIMATION_TIME, new DelayedRoller());
            timer.setRepeats(false);
            timer.start();
        }

        private class DelayedRoller implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                DiceController.this.model.roll();
            }
        }
    }

    public class LockAction extends AbstractAction {

        private int dieId;

        public LockAction(int dieId) {
            super("Lock");

            this.dieId = dieId;
        }

        public void actionPerformed(ActionEvent a) {
            JToggleButton state = (JToggleButton) a.getSource();
            DiceController.this.model.setDieLock(dieId, state.isSelected());
        }
    }
}
