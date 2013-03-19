
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 *
 * @author Mikko Paukkonen
 */
public class GameController {

    GameModel model;

    public class MarkAction extends AbstractAction {

        private MarkableScoreCell cell;

        @Override
        public void actionPerformed(ActionEvent e) {
            cell.markScore();
        }

        public MarkAction(MarkableScoreCell cell) {
            this.cell = cell;
        }
    }

    public GameController(GameModel model) {
        this.model = model;
    }

    public Action getMarkAction(MarkableScoreCell cell) {
        return new MarkAction(cell);
    }
}
