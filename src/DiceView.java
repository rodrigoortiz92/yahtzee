import java.util.Observer;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.JToggleButton;
import javax.swing.BoxLayout;

public class DiceView extends Jpanel implements Observer {
  private DiceModel model;
  private DiceController controller;
  private DiceModel.DieValues values;
  private Die dice;
  
  private JButton rollButton;

  public DiceView(DiceModel dmodel){
    super();
    model = dmodel;
    controller = new DiceController(this, model);
    dice = new Die[dmodel.getDieCount];

    int i = dice.length;
    while (i-- > 0){
      dice[i] = new Die
    }
  }

  void updated(Observable o, Object arg){

    DiceModel.DiceValues values = (DiceModel.DiceValues)arg;

    int i = values.getDieCount();
    while (i-- > 0){

      dice.setValue(values.value


    }
  }

  private class Die extends JLabel {
    private int maxValue;
    private int currValue;
    //private icon facet[];
    super("3");

    public Die(int max){
      maxValue = max;
      facet = new icon[maxValue];

      int i = 0;
      while (i <= maxValue){
        //facet[i] = new ImageIcon("images/facet" + (i + 1) + ".png");
      }

    public setValue(int v){
      currValue = v;
      super.setText(Integer.toString(v);
    }
  }
}
