
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

abstract public class ScoreColumn {

    private List<CellNamePair> pairs;

    public class CellNamePair {

        private ScoreCell cell;
        private String name;

        public CellNamePair(ScoreCell cell, String name) {
            this.cell = cell;
            this.name = name;
        }
    }

    public ScoreColumn() {
        pairs = new LinkedList<>();
    }

    public void setThings(Player player, DiceModel diceModel) {
        for (ScoreCell cell : getCells()) {
            if (cell instanceof MarkableScoreCell) {
                MarkableScoreCell rollable = (MarkableScoreCell) cell;
                rollable.setPlayer(player);
                rollable.setDiceModel(diceModel);
            }
        }
    }

    protected void addPair(ScoreCell cell, String name) {
        pairs.add(new CellNamePair(cell, name));
    }

    abstract public ScoreCell getBonusCell();

    abstract public ScoreCell getTotalCell();

    abstract public ScoreCell getYahtzeeCell();

    abstract public ScoreCell getTopTotalCell();

    public List<String> getCellNames() {
        List<String> names = new ArrayList<>(pairs.size());

        for (CellNamePair pair : pairs) {
            names.add(pair.name);
        }

        return Collections.unmodifiableList(names);
    }

    public List<ScoreCell> getCells() {
        List<ScoreCell> cells = new ArrayList<>(pairs.size());

        for (CellNamePair pair : pairs) {
            cells.add(pair.cell);
        }

        return Collections.unmodifiableList(cells);
    }
}
