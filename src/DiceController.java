import java.util.Observer;
import java.util.Observable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DiceController implements ActionListener{

    private DiceModel model;
    private DiceView view;

    public DiceController(DiceView dView, DiceModel dModel){
        view = dView;
        model = dModel;
    }

    public void actionPerformed(ActionEvent ae){


    }
}
