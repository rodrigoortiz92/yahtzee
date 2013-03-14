import java.util.Observer;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.Icon;

public class DiceView extends Jpanel implements Observer {
  private DiceModel model;
  private DiceController controller;
  private DiceModel.DieValues dice;

  public DiceView(DiceModel dmodel){
    super();
    controller = new DiceController(this, model);
  }


  private class Die extends JComponent {
    private int maxValue;
    private int currValue;
    private icon facet[];

    public Die(int max){
      maxValue = max;
      facet = new icon[maxValue];

      int i = 0;
      while (i <= maxValue){
        facet[i] = new ImageIcon("images/facet" + (i + 1) + ".png");
    }

    public setValue(int v){
      currValue = v;
    }
  }
}
