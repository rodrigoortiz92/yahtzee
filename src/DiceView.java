import java.util.Observer;
import javax.swing.JPanel;
import javax.swing.JButton;

public class DiceView extends Jpanel implements Observer {

  private DiceModel model;
  private DiceController controller;
  private DiceModel.DieValues dice;


  public DiceView(DiceModel dmodel){
    super();
    controller = new DiceController(this, model);
  }
}
