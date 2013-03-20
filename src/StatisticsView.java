
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Mikko Paukkonen
 */
public class StatisticsView extends JDialog {

    GameModel model;
    StatisticsController controller = new StatisticsController(this);
    Component statisticsPanel;

    private abstract class Column {

        public abstract String getTitle();

        public abstract String getData(Player player);
    }

    private class NameColumn extends Column {

        @Override
        public String getTitle() {
            return "Name";
        }

        @Override
        public String getData(Player player) {
            return player.getName();
        }
    }

    private class ScoreColumn extends Column {

        @Override
        public String getTitle() {
            return "Score";
        }

        @Override
        public String getData(Player player) {
            return String.valueOf(player.calculateScore());
        }
    }

    private class TopScoreColumn extends Column {

        @Override
        public String getTitle() {
            return "Top total";
        }

        @Override
        public String getData(Player player) {
            return String.valueOf(player.calculateTopScore());
        }
    }

    private class BestCellColumn extends Column {

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

    private class MaximumColumn extends Column {

        @Override
        public String getTitle() {
            return "Maximum";
        }

        @Override
        public String getData(Player player) {
            return String.valueOf(StatisticsView.this.model.getMaximumScore());
        }
    }

    private class ScorePercentageColumn extends Column {

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

    private class GotBonusColumn extends Column {

        @Override
        public String getTitle() {
            return "Got Bonus?";
        }

        @Override
        public String getData(Player player) {
            return player.hasBonus() ? "Yes" : "No";
        }
    }

    private class GotYahtzeeColumn extends Column {

        @Override
        public String getTitle() {
            return "Got Yahtzee?";
        }

        @Override
        public String getData(Player player) {
            return player.hasYahtzee() ? "Yes" : "No";
        }
    }

    private class DieFrequencyColumn extends Column {

        private Integer kind;
        private final String[] titles = {
            "Ones",
            "Twos",
            "Threes",
            "Fours",
            "Fives",
            "Sixes"
        };

        public DieFrequencyColumn(int kind) {
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

    private Component createTablePanel(Comparator<Player> comp) {
        List<Player> players = new LinkedList<>(model.getPlayers());

        Collections.sort(players, comp);

        List<Column> columns = new LinkedList<>();

        columns.add(new NameColumn());
        columns.add(new ScoreColumn());
        columns.add(new MaximumColumn());
        columns.add(new ScorePercentageColumn());
        columns.add(new TopScoreColumn());
        columns.add(new BestCellColumn());
        columns.add(new GotYahtzeeColumn());
        columns.add(new GotBonusColumn());

        for (int i = DiceModel.DIE_MIN_VALUE; i <= DiceModel.DIE_MAX_VALUE; ++i) {
            columns.add(new DieFrequencyColumn(i));
        }

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new GridBagLayout());

        int x = 0;

        for (Column column : columns) {
            GridBagConstraints c = new GridBagConstraints(x, 0, 1, 1, 1, 0,
                    GridBagConstraints.FIRST_LINE_START,
                    GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0);

            JLabel label = new JLabel(column.getTitle());

            label.setFont(label.getFont().deriveFont(Font.BOLD));

            tablePanel.add(label, c);

            x++;
        }

        for (int y = 0; y < players.size(); ++y) {
            Player player = players.get(y);

            x = 0;

            for (Column row : columns) {
                GridBagConstraints c = new GridBagConstraints(x, y + 1, 1, 1, 1, 0,
                        GridBagConstraints.FIRST_LINE_START,
                        GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 12), 0, 0);

                JLabel label = new JLabel(row.getData(player));

                if (x == 0) {
                    label.setFont(label.getFont().deriveFont(Font.BOLD));
                }

                tablePanel.add(label, c);

                x++;
            }
        }

        return tablePanel;
    }

    private Component createBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridBagLayout());

        GridBagConstraints labelConstraints = new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.LINE_START,
                GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0);

        bottomPanel.add(new JLabel("Sort by"), labelConstraints);

        GridBagConstraints boxConstraints = new GridBagConstraints(1, 0, 1, 1, 0, 0,
                GridBagConstraints.FIRST_LINE_START,
                GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0);

        JComboBox<StatisticsController.SortOption> sortSelection =
                new JComboBox(controller.getSortOptions());
        sortSelection.addActionListener(controller.getSortOptionListener());

        bottomPanel.add(sortSelection, boxConstraints);

        GridBagConstraints glueConstraints = new GridBagConstraints(2, 0, 1, 1, 1, 1,
                GridBagConstraints.FIRST_LINE_START,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0);

        bottomPanel.add(Box.createHorizontalGlue(), glueConstraints);

        GridBagConstraints buttonConstraints = new GridBagConstraints(3, 0, 1, 1, 0, 0,
                GridBagConstraints.FIRST_LINE_START,
                GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0);

        bottomPanel.add(new JButton(controller.getCloseAction()), buttonConstraints);

        return bottomPanel;
    }

    public void sort(Comparator<Player> comp) {
        Container container = (Container) statisticsPanel;
        getContentPane().remove(statisticsPanel);

        GridBagConstraints tablePanelConstraints = new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.FIRST_LINE_START,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0);

        statisticsPanel = createTablePanel(comp);

        getContentPane().add(statisticsPanel, tablePanelConstraints);

        validate();
    }

    public StatisticsView(Window view, GameModel model) {
        super(view, ModalityType.APPLICATION_MODAL);
        setTitle("Statistics");

        this.model = model;

        Container content = getContentPane();
        content.setLayout(new GridBagLayout());

        GridBagConstraints tablePanelConstraints = new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.FIRST_LINE_START,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0);

        statisticsPanel = createTablePanel(new StatisticsController.PlayerScoreComparator());

        content.add(statisticsPanel, tablePanelConstraints);

        GridBagConstraints bottomPanelContraints = new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.LAST_LINE_START,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0);
        content.add(createBottomPanel(), bottomPanelContraints);

        pack();
        setMinimumSize(getSize());
        setLocationRelativeTo(view);
    }
}
