
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Mikko Paukkonen
 */
public class GameView extends JPanel implements Observer {

    GameModel model;
    GameController controller;
    JButton[][] fields;
    Map<Player, JLabel> playerLabels;

    /**
     * Creates new form GameView
     */
    public GameView(GameModel model) {
        this.model = model;
        this.controller = new GameController(model);

        model.addObserver(this);

        createComponents();
    }

    private void addToLayout(Container container, Component c, int x, int y) {
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = x;
        constraints.gridy = y;
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(-1, -1, 0, 0);

        container.add(c, constraints);
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
            JLabel label = new JLabel(scoreCellNames.get(i));

            label.setHorizontalTextPosition(SwingConstants.TRAILING);

            addToLayout(this, label, 0, i + 1);
        }

        playerLabels = new HashMap<>();

        for (int i = 0; i < players.size(); i++) {
            JLabel label = new JLabel(players.get(i).getName());

            playerLabels.put(players.get(i), label);

            addToLayout(this, label, i + 1, 0);
        }

        fields = new JButton[scoreCellNames.size()][players.size()];

        final int MAX_PLAYERS = 10;

        for (int i = 0; i < MAX_PLAYERS; i++) {
            for (int j = 0; j < scoreCellNames.size(); j++) {
                ScoreCell cell = null;

                if (i < players.size()) {
                    cell = players.get(i).getScoreCells().get(j);
                }

                CellView view = new CellView(cell, (j % 2) == 1);

                addToLayout(this, view, i + 1, j + 1);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof GameModel.NewGameNotification) {
            createComponents();
        } else if (arg instanceof GameModel.TurnBeginNotification) {
            GameModel.TurnBeginNotification begin = (GameModel.TurnBeginNotification) arg;

            JLabel label = playerLabels.get(begin.player);

            label.setFont(label.getFont().deriveFont(Font.BOLD));
        } else if (arg instanceof GameModel.TurnEndNotification) {
            GameModel.TurnEndNotification end = (GameModel.TurnEndNotification) arg;

            JLabel label = playerLabels.get(end.player);

            label.setFont(label.getFont().deriveFont(Font.PLAIN));
        }
    }
}
