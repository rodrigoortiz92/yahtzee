
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Stores the score cells of a player and associates a name with each cell.
 *
 * @author Mikko Paukkonen
 */
public class ScoreColumn {

    private CellNamePair[] pairs;

    private class CellNamePair {

        private ScoreCell cell;
        private String name;

        public CellNamePair(ScoreCell cell, String name) {
            this.cell = cell;
            this.name = name;
        }
    }

    public ScoreColumn(Player player) {
        pairs = new CellNamePair[]{
            new CellNamePair(new SumOfKindCell(1), "Ones"),
            new CellNamePair(new SumOfKindCell(2), "Twos"),
            new CellNamePair(new SumOfKindCell(3), "Threes"),
            new CellNamePair(new SumOfKindCell(4), "Fours"),
            new CellNamePair(new SumOfKindCell(5), "Fives"),
            new CellNamePair(new SumOfKindCell(6), "Sixes")
        };

        for (CellNamePair cellNamePair : pairs) {
            ScoreCell cell = cellNamePair.cell;

            if (cell instanceof MarkableScoreCell) {
                MarkableScoreCell rollable = (MarkableScoreCell) cell;
                rollable.setPlayer(player);
            }
        }
    }

    public List<String> getCellNames() {
        List<String> names = new ArrayList<>(pairs.length);

        for (CellNamePair pair : pairs) {
            names.add(pair.name);
        }

        return Collections.unmodifiableList(names);
    }

    public List<ScoreCell> getCells() {
        List<ScoreCell> cells = new ArrayList<>(pairs.length);

        for (CellNamePair pair : pairs) {
            cells.add(pair.cell);
        }

        return Collections.unmodifiableList(cells);
    }
}
