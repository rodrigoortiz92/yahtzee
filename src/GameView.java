
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
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
        diceModel.addObserver(this);

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
            addToLayout(this, new JLabel(scoreCellNames.get(i)), 0, i + 1);
        }

        for (int i = 0; i < players.size(); i++) {
            addToLayout(this, new JLabel(players.get(i).getName()), i + 1, 0);
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
        }
    }
}
