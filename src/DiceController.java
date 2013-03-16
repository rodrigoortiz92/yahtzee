import java.util.Observer;
import java.util.Observable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DiceController implements ActionListener {

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


    public class RollAction extends AbstractAction {

        public void actionPerformed(ActionEvent a){
            DiceController.this.model.roll();
        }
    }
}
