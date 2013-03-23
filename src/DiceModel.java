
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

public class DiceModel extends Observable {

    public static final int DIE_MIN_VALUE = 1;
    public static final int DIE_MAX_VALUE = 6;
    private int diceCount;
    private DieValues dice = null;
    private boolean canLock;
    private int rollsLeft;
    private boolean locked[];
    private Random rand = new Random();
    private boolean acceptUserInput = false;

    public boolean acceptsUserInput() {
        return acceptUserInput;
    }

    public class RollNotification {

        DieValues values;
        List<Integer> rolledDice;

        public RollNotification(DieValues values, List<Integer> rolledDice) {
            this.values = values;
            this.rolledDice = rolledDice;
        }
    }

    public class ResetNotification {
    }

    public DiceModel(int dieCount) {
        this.diceCount = dieCount;
        locked = new boolean[diceCount];
        this.canLock = false;
    }

    public void clear(boolean acceptUserInput, int rolls) {
        rollsLeft = rolls;
        Arrays.fill(locked, false);

        this.acceptUserInput = acceptUserInput;
        dice = null;

        setChanged();
        notifyObservers(new ResetNotification());
    }

    public int getDieCount() {
        return diceCount;
    }

    public DieValues getDieValues() {
        return dice;
    }

    public List<Integer> getKinds() {
        List<Integer> kinds = new LinkedList<>();

        for (int i = DiceModel.DIE_MAX_VALUE; i >= DiceModel.DIE_MIN_VALUE; --i) {
            kinds.add(i);
        }

        return kinds;
    }

    public boolean canDiceBeLocked() {
        return canDiceBeRolled() && canLock;
    }

    public boolean canDiceBeRolled() {
        return (rollsLeft > 0);
    }

    public int getRollsLeft() {
        return rollsLeft;
    }

    public void setDieLock(int i, boolean state) {
        if (canDiceBeLocked()) {
            locked[i] = state;
        }
    }

    public boolean isDieLocked(int i) {
        return locked[i];
    }

    public void roll() {
        if (rollsLeft == 0) {
            return;
        }

        if (dice == null) {
            dice = new DieValues(diceCount);
        }

        List<Integer> rolledDice = new LinkedList<>();

        int i = 0;
        while (i < diceCount) {
            if (!locked[i]) {
                int value = rand.nextInt(DIE_MAX_VALUE - DIE_MIN_VALUE + 1)
                        + DIE_MIN_VALUE;

                dice.values[i] = value;

                rolledDice.add(value);
            }
            i++;
        }

        rollsLeft--;

        if (!canDiceBeRolled()) {
            Arrays.fill(locked, false);
        }
        setChanged();
        notifyObservers(new RollNotification(dice, rolledDice));
    }

    public class DieValues {

        private int values[];

        private DieValues(int count) {
            values = new int[count];
        }

        private void clear() {
            int i = getValueCount();
            while (i-- > 0) {
                values[i] = DiceModel.DIE_MIN_VALUE;
            }
        }

        public int getValueCount() {
            return values.length;
        }

        public int valueAt(int i) {
            return values[i];
        }

        public void valueAt(int i, int v) {
            values[i] = v;
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
