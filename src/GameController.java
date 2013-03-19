
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 *
 * @author Mikko Paukkonen
 */
public class GameController {

    GameModel model;
    DiceModel diceModel;

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

    public GameController(GameModel model, DiceModel diceModel) {
        this.model = model;
        this.diceModel = diceModel;
    }

    public Action getMarkAction(MarkableScoreCell cell) {
        return new MarkAction(cell);
    }
}
