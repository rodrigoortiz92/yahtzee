
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public class GameModel extends Observable {

    private DiceModel diceModel;
    private List<Player> players;
    private Player currentPlayer;

    public class PlayerColumn {

        public String name;
        public boolean isInTurn;
        public Cell[] cells;

        public class Cell {

            private ScoreCell cell;

            public int getScore() {
                Integer score = cell.getScore();

                if (score != null)
                    return score.intValue();

                int[] dieValues = GameModel.this.diceModel.getDieValues();

                return cell.calculateScore(dieValues);
            }

            public boolean isFilled() {
                return cell.getScore() != null;
            }

            public void mark() {
                int[] dieValues = GameModel.this.diceModel.getDieValues();

                cell.markScore(dieValues);
            }

            public Cell(ScoreCell cell) {
                this.cell = cell;
            }
        }
    }

    public PlayerColumn[] getPlayerColumns() {
        PlayerColumn[] columns = new PlayerColumn[players.size()];

        for (int i = 0; i < players.size(); ++i) {
            Player player = players.get(i);
            PlayerColumn column = new PlayerColumn();

            column.name = player.getName();
            column.isInTurn = (player == currentPlayer);

            ScoreCell[] cells = player.getScoreCells();

            column.cells = new PlayerColumn.Cell[cells.length];

            for (int j = 0; j < cells.length; ++j) {
                ScoreCell backingCell = cells[j];

                column.cells[j] = column.new Cell(backingCell);
            }

            columns[i] = column;
        }

        return columns;
    }

    public String[] getScoreCellNames() {
        return new ScoreColumn().getCellNames();
    }

    public void startNewGame(List<Player> players) {
        this.players = players;

        if (players.isEmpty())
            currentPlayer = null;
        else
            currentPlayer = players.get(0);
    }

    public GameModel(DiceModel diceModel) {
        this.diceModel = diceModel;

        startNewGame(new LinkedList<Player>());
    }
}
