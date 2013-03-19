
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 *
 * @author Mikko Paukkonen
 */
public class StatisticsView extends JDialog {

    GameModel model;

    private abstract class Row {

        public abstract String getTitle();

        public abstract String getData(Player player);
    }

    private class NameRow extends Row {

        @Override
        public String getTitle() {
            return "Name";
        }

        @Override
        public String getData(Player player) {
            return player.getName();
        }
    }

    private class ScoreRow extends Row {

        @Override
        public String getTitle() {
            return "Score";
        }

        @Override
        public String getData(Player player) {
            return String.valueOf(player.calculateScore());
        }
    }

    private class BestCellRow extends Row {

        @Override
        public String getTitle() {
            return "Highest scoring cell";
        }

        @Override
        public String getData(Player player) {
            ScoreCell bestCell = null;
            List<ScoreCell> cells = player.getScoreCells();

            for (ScoreCell cell : cells) {
                if (!(cell instanceof MarkableScoreCell)) {
                    continue;
                }

                MarkableScoreCell markable = (MarkableScoreCell) cell;

                Integer score = markable.getScore();

                if (score == null) {
                    continue;
                }

                if (bestCell == null) {
                    bestCell = cell;
                    continue;
                }

                if (bestCell.getScore() < score) {
                    bestCell = cell;
                }
            }

            if (bestCell == null) {
                return "";
            }

            return model.getScoreCellNames().get(cells.indexOf(bestCell));
        }
    }

    private class MaximumRow extends Row {

        @Override
        public String getTitle() {
            return "Maximum";
        }

        @Override
        public String getData(Player player) {
            return String.valueOf(StatisticsView.this.model.getMaximumScore());
        }
    }

    private class ScorePercentageRow extends Row {

        @Override
        public String getTitle() {
            return "Percentage";
        }

        @Override
        public String getData(Player player) {
            double f = (double) player.calculateScore();
            f /= (double) StatisticsView.this.model.getMaximumScore();

            return String.format("%.3f %%", f * 100.0);
        }
    }

    private class DieFrequencyRow extends Row {

        private Integer kind;
        private final String[] titles = {
            "Ones",
            "Twos",
            "Threes",
            "Fours",
            "Fives",
            "Sixes"
        };

        public DieFrequencyRow(int kind) {
            this.kind = kind;
        }

        @Override
        public String getTitle() {
            return titles[kind - 1];
        }

        @Override
        public String getData(Player player) {
            double f = (double) player.getRollFrequencies().get(kind) / (double) player.getTotalRolls();

            return String.format("%.3f %%", f * 100.0);
        }
    }

    private class PlayerScoreComparator implements Comparator<Player> {

        @Override
        public int compare(Player o1, Player o2) {
            return -Integer.compare(o1.calculateScore(), o2.calculateScore());
        }
    }

    public StatisticsView(Window view, GameModel model) {
        super(view, ModalityType.APPLICATION_MODAL);
        setTitle("Statistics");

        this.model = model;

        List<Player> players = new LinkedList<>(model.getPlayers());

        Collections.sort(players, new PlayerScoreComparator());

        Container content = getContentPane();
        content.setLayout(new GridBagLayout());

        List<Row> rows = new LinkedList<>();

        rows.add(new NameRow());
        rows.add(new ScoreRow());
        rows.add(new BestCellRow());

        for (int i = DiceModel.DIE_MIN_VALUE; i <= DiceModel.DIE_MAX_VALUE; ++i) {
            rows.add(new DieFrequencyRow(i));
        }

        rows.add(new ScorePercentageRow());
        rows.add(new MaximumRow());

        int y = 0;

        for (Row row : rows) {
            GridBagConstraints c = new GridBagConstraints(0, y, 1, 1, 1, 0,
                    GridBagConstraints.FIRST_LINE_START,
                    GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0);

            JLabel label = new JLabel(row.getTitle());

            content.add(label, c);

            y++;
        }


        for (int x = 0; x < players.size(); ++x) {
            Player player = players.get(x);

            y = 0;

            for (Row row : rows) {
                GridBagConstraints c = new GridBagConstraints(x + 1, y, 1, 1, 1, 0,
                        GridBagConstraints.FIRST_LINE_START,
                        GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0);

                JLabel label = new JLabel(row.getData(player));

                content.add(label, c);

                y++;
            }
        }

        pack();
        setLocationRelativeTo(view);
    }
}
