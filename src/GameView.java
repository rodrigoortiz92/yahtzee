
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GameView extends JPanel implements Observer {

    GameModel model;
    GameController controller;
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

        GridLayout layout = new GridLayout(0, GameModel.MAX_PLAYER_COUNT + 1, -1, -1);
        setLayout(layout);

        add(new JLabel(""));

        playerLabels = new HashMap<>();

        for (int i = 0; i < GameModel.MAX_PLAYER_COUNT; i++) {
            JLabel label = new JLabel(" ");

            if (i < players.size()) {
                label.setText(players.get(i).getName());
                playerLabels.put(players.get(i), label);

                if (players.get(i).isInTurn()) {
                    label.setFont(label.getFont().deriveFont(Font.BOLD));
                }
            }

            add(label);
        }

        for (int i = 0; i < scoreCellNames.size(); i++) {
            JLabel label = new JLabel(scoreCellNames.get(i));

            label.setHorizontalTextPosition(SwingConstants.TRAILING);

            add(label);

            for (int j = 0; j < GameModel.MAX_PLAYER_COUNT; j++) {

                ScoreCell cell = null;

                if (j < players.size()) {
                    cell = players.get(j).getScoreCells().get(i);
                }

                CellView view = new CellView(cell, (i % 2) == 1);

                add(view);
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
