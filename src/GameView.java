
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Mikko Paukkonen
 */
public class GameView extends JPanel implements Observer {

    GameModel model;
    GameController controller;
    DiceModel diceModel;
    JButton[][] fields;

    /**
     * Creates new form GameView
     */
    public GameView(GameModel model, DiceModel diceModel) {
        this.model = model;
        this.controller = new GameController(model, diceModel);
        this.diceModel = diceModel;

        model.addObserver(this);

        createComponents();
        updateScores();
    }

    private void addToLayout(Container container, Component c, int x, int y) {
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = x;
        constraints.gridy = y;
        constraints.weightx = 0.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        container.add(c, constraints);
    }

    private void updateScores() {
        List<Player> playerColumns = model.getPlayers();

        int x = 0;

        for (Player player : model.getPlayers()) {
            int y = 0;

            for (ScoreCell cell : player.getScoreCells()) {
                JButton field = fields[y][x];

                String text = " ";

                if (cell.getScore() != null) {
                    text = String.valueOf(cell.getScore());
                } else if (player == model.getCurrentPlayer()) {
                    if (cell instanceof MarkableScoreCell) {
                        MarkableScoreCell rollableCell = (MarkableScoreCell) cell;
                        text = String.valueOf(rollableCell.calculateScore(diceModel.getDieValues()));
                    }
                }

                field.setText(text);

                Font currentFont = field.getFont();
                Color color;

                if (cell.getScore() != null) {
                    currentFont = currentFont.deriveFont(Font.PLAIN);
                    color = new Color(0, 0, 0);
                } else {
                    currentFont = currentFont.deriveFont(Font.ITALIC);
                    color = new Color(0.5f, 0.5f, 0.5f);
                }

                field.setForeground(color);
                field.setFont(currentFont);

                y++;
            }
            x++;
        }
    }

    private void createComponents() {
        removeAll();

        List<Player> players = model.getPlayers();
        List<String> scoreCellNames = model.getScoreCellNames();

        int rows = scoreCellNames.size() + 1;
        int columns = players.size() + 1;

        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        for (int i = 0; i < scoreCellNames.size(); i++) {
            addToLayout(this, new JLabel(scoreCellNames.get(i)), 0, i + 1);
        }

        for (int i = 0; i < players.size(); i++) {
            addToLayout(this, new JLabel(players.get(i).getName()), i + 1, 0);
        }

        fields = new JButton[scoreCellNames.size()][players.size()];

        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < players.get(i).getScoreCells().size(); j++) {
                ScoreCell cell = players.get(i).getScoreCells().get(j);
                JButton field = new JButton();

                field.setBackground(Color.white);
                field.setFocusable(false);

                if (cell instanceof MarkableScoreCell) {
                    MarkableScoreCell rollableCell = (MarkableScoreCell) cell;
                    Action action = controller.getMarkAction(rollableCell);
                    field.setAction(action);
                }

                fields[j][i] = field;

                addToLayout(scorePanel, field, i + 1, j + 1);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof GameModel.NewGameNotification) {
            createComponents();
            updateScores();
        } else if (arg instanceof GameModel.ScoreChangeNotification) {
            updateScores();
        } else if (arg instanceof GameModel.TurnChangedNotification) {
            updateScores();
        }
        updateScores();
    }
}
