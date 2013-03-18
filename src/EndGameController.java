
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.AbstractAction;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mikko Paukkonen
 */
public class EndGameController implements Observer {

    private NewGameAction newGameAction = new NewGameAction();
    private ShowStatisticsAction showStatisticsAction = new ShowStatisticsAction();
    private CloseAction closeAction = new CloseAction();
    private EndGameView view;
    private GameModel model;

    public EndGameController(EndGameView view, GameModel model) {
        this.view = view;
        this.model = model;

        this.model.addObserver(this);

    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof GameModel.EndGameNotification) {
            view.setVisible(true);
            view.setLocationRelativeTo(view.getParent());
        }


    }

    public NewGameAction getNewGameAction() {
        return newGameAction;
    }

    public ShowStatisticsAction getShowStatisticsAction() {
        return showStatisticsAction;
    }

    public CloseAction getCloseAction() {
        return closeAction;
    }

    public class NewGameAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }

    public class ShowStatisticsAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }

    public class CloseAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            EndGameController.this.view.setVisible(false);
        }
    }
}
