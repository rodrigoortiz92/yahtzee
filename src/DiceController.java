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

    public boolean lockable(){
        return model.canDiceBeLocked();
    }

    public  boolean rollable(){
        return model.canDiceBeRolled();
    }

    public void actionPerformed(ActionEvent ae){


    }


    public class RollAction extends AbstractAction
}
