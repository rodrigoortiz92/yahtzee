
import java.util.Observable;
import java.util.Random;
import java.util.Arrays;

public class DiceModel extends Observable {

    public static final int DIE_MIN_VALUE = 1;
    public static final int DIE_MAX_VALUE = 6;
    private static final int rollsPerTurn = 3;
    private static final int diceCount = 5;
    private DieValues dice;
    private int rolledTimes;
    private boolean locked[];
    private Random rand = new Random(3);

    public DiceModel() {

        dice = new DieValues(diceCount);
        locked = new boolean[diceCount];
    }

    public void clear(){
        rolledTimes = 0;
        Arrays.fill(locked, false);
        dice.clear();
        setChanged();
        notifyObservers(dice);
    }

    public int getDieCount() {
        return diceCount;
    }

    public DieValues getDieValues() {
        return dice;
    }

    public boolean canDiceBeLocked() {
        return canDiceBeRolled() && (rolledTimes > 0);
    }

    public boolean canDiceBeRolled() {
        return (rollsPerTurn - rolledTimes > 0);
    }

    public void setDieLock(int i, boolean state) {
        locked[i] = state;
    }

    public boolean isDieLocked(int i){
        return locked[i];
    }

    public void roll() {
        int i = 0;
        while (i < diceCount) {
            if (!locked[i]) {
                dice.values[i] = rand.nextInt(DIE_MAX_VALUE - DIE_MIN_VALUE + 1)
                    + DIE_MIN_VALUE;
            }
            i++;
        }
        rolledTimes++;
        setChanged();
        notifyObservers(dice);
    }

    public class DieValues {

        private int values[];

        private DieValues(int count) {
            values = new int[count];
        }

        private void clear(){
            int i = getValueCount();
            while (i-- > 0){
                values[i] = DiceModel.DIE_MIN_VALUE;
            }
        }

        public int getValueCount() {
            return values.length;
        }

        public int valueAt(int i) {
            return values[i];
        }

        public int countOfValue(int v) {
            int count = 0;

            for (int i : values) {
                if (i == v) {
                    count++;
                }
            }

            return count;
        }
    }
}
