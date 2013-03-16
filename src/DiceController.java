import java.util.Observer;
import java.util.Observable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class DiceController {

    private DiceModel model;
    private DiceView view;

    public DiceController(DiceView view, DiceModel model){
        this.view = view;
        this.model = model;
    }

    public boolean isLockable(){
        return model.canDiceBeLocked();
    }

    public boolean isRollable(){
        return model.canDiceBeRolled();
    }

    public boolean isLocked(int i){
        return model.isDieLocked(i);
    }


    public Action getRollAction(){
        return new RollAction();
    }

    public Action getLockAction(int dieId){
        return new LockAction(dieId);
    }

    public class RollAction extends AbstractAction {

        public void actionPerformed(ActionEvent a){
            DiceController.this.model.roll();
        }
    }

    public class LockAction extends AbstractAction {

        private boolean selected;
        private int dieId;

        public LockAction(int dieId){
            this.dieId = dieId;
        }

        public void actionPerformed(ActionEvent a){
            DiceController.this.model.setDieLock(dieId, selected);
        }
    }
}
