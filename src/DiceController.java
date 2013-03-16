import java.util.Observer;
import java.util.Observable;

public class DiceController implements Observer {

  private DiceModel model;
  private DiceView view;

  public DiceController(DiceView dView, DiceModel dModel){
    view = dView;
    model = dModel;
  }

  public void update(Observable o, Object arg){
  }
}
