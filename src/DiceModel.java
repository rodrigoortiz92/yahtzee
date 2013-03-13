import java.util.Observable;
import java.util.Random;

public class DiceModel extends Observable {

  private static final dieLowerBound = 1;
  private static final dieUpperBound = 6;
  private static Random rand = new Random(4);

  private DieValues dice;
  private int rollsTotal;
  private int rolledTimes;
  private boolean locked[];


  public int getDieCount(){
    return dice.values.length;
  }

  public DieValues getDieValues(){
    return dice;
  }

  public boolean canDiceBeLocked(){
    return ((rollsTotal - rolledTimes) > 0);
  }

  public void setDieLock(int i, boolean state){
    locked[i] = state;
  }

  public void roll(){
    int i = 0;
    while (i < dice.values.length){
      if (!locked[i]){
        dice.values[i] = rand.nextInt(dieUpperBound) + dieLowerBound;
      }
      i++;
    }
  }


  public class DieValues {

    private int values[];

    private DieValues(int count){
      values = new int[count];
    }
  }
}
