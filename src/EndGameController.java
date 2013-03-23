
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JOptionPane;

public class EndGameController {

    private EndGameView view;
    private GameModel model;
    private SetupViewFactory setupViewFactory;
    private PaneValueListener paneValueListener = new PaneValueListener();
    private final String NEW_GAME = "New Game";
    private final String CLOSE = "Close";
    private final String OPEN_STATISTICS = "Show Statistics";

    public EndGameController(EndGameView view, GameModel model,
            SetupViewFactory setupViewFactory) {
        this.view = view;
        this.model = model;
        this.setupViewFactory = setupViewFactory;
    }

    public Object[] getOptions() {
        return new Object[]{
                    NEW_GAME,
                    CLOSE,
                    OPEN_STATISTICS
                };
    }

    public Object getDefaultOption() {
        return NEW_GAME;
    }

    public PaneValueListener getPaneValueListener() {
        return paneValueListener;
    }

    private void newGame() {
        EndGameController.this.view.dispose();
        setupViewFactory.create().setVisible(true);
    }

    private void openStatistics() {
        new StatisticsView(EndGameController.this.view, model).setVisible(true);
    }

    private void close() {
        EndGameController.this.view.dispose();
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
