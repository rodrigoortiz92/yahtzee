import java.util.Observable;

public class DiceModel extends Observable {

  private DieValues values;
  private int rollsLeft;





  public class DieValues {

    int values[];

    public DieValues(int count){
      values = new int[count];
    }
  }
}
