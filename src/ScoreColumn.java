
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

    public ScoreColumn() {
        pairs = new CellNamePair[]{
            new CellNamePair(new SumOfKindCell(1), "Ones"),
            new CellNamePair(new SumOfKindCell(2), "Twos"),
            new CellNamePair(new SumOfKindCell(3), "Threes"),
            new CellNamePair(new SumOfKindCell(4), "Fours"),
            new CellNamePair(new SumOfKindCell(5), "Fives"),
            new CellNamePair(new SumOfKindCell(6), "Sixes")
        };
    }

    public String[] getCellNames() {
        String[] names = new String[pairs.length];

        for (int i = 0; i < names.length; ++i)
            names[i] = pairs[i].name;

        return names;
    }

    public ScoreCell[] getCells() {
        ScoreCell[] cells = new ScoreCell[pairs.length];

        for (int i = 0; i < cells.length; ++i)
            cells[i] = pairs[i].cell;

        return cells;
    }
}
