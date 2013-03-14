import java.util.Observer;

public class DiceController implements Observer {

  private DiceModel model;
  private DiceView view;

  public DiceController(DiceView dView, DiceModel dModel){
    view = dView;
    model = dModel;
  }
}
