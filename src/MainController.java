
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mikko Paukkonen <mikko.paukkonen at uta.fi>
 */
public class MainController {

    private NewGameAction newGameAction = new NewGameAction();
    private ExitAction exitAction = new ExitAction();

    public NewGameAction getNewGameAction() {
        return newGameAction;
    }

    public ExitAction getExitAction() {
        return exitAction;
    }

    public class NewGameAction extends AbstractAction {

        public NewGameAction() {
            super("New Game");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    public class ExitAction extends AbstractAction {

        public ExitAction() {
            super("Exit");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
