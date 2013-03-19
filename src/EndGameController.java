
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Mikko Paukkonen
 */
public class EndGameController {

    private EndGameView view;
    private GameModel model;
    private PaneValueListener paneValueListener = new PaneValueListener();
    private final String NEW_GAME = "New Game";
    private final String CLOSE = "Close";
    private final String OPEN_STATISTICS = "Show Statistics";

    public EndGameController(EndGameView view, GameModel model) {
        this.view = view;
        this.model = model;
    }

    public Object[] getOptions()
    {
        return new Object[]
        {
            NEW_GAME,
            CLOSE,
            OPEN_STATISTICS
        };
    }

    public Object getDefaultOption()
    {
        return NEW_GAME;
    }

    public PaneValueListener getPaneValueListener() {
        return paneValueListener;
    }

    private void newGame() {
    }

    private void openStatistics() {
        new StatisticsView(EndGameController.this.view, model).setVisible(true);
    }

    private void close() {
        EndGameController.this.view.setVisible(false);
    }

    public class PaneValueListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals(JOptionPane.VALUE_PROPERTY)) {
                Object value = evt.getNewValue();

                if (value == NEW_GAME) {
                    newGame();
                } else if (value == OPEN_STATISTICS) {
                    openStatistics();
                } else if (value == CLOSE) {
                    close();
                }

                JOptionPane optionPane = (JOptionPane) evt.getSource();

                optionPane.setValue(JOptionPane.UNINITIALIZED_VALUE);
            }
        }
    }
}
