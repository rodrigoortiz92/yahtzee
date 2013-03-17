
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
public class MainController implements WindowListener {

    private NewGameAction newGameAction = new NewGameAction();
    private ExitAction exitAction = new ExitAction();
    private SetupController controller;
    
    public MainController(SetupController controller){
        this.controller = controller;
    }

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
            controller.view.setVisible(true);
            //throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    public class ExitAction extends AbstractAction {

        public ExitAction() {
            super("Exit");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            WindowEvent event = new WindowEvent(view, WindowEvent.WINDOW_CLOSING);
            MainController.this.view.dispatchEvent(event);
        }
    }


    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        String yes = "Yes";
        String no = "No";

        int result = JOptionPane.showOptionDialog(view,
                "Do you really want to quit?",
                "Confirm exit", JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE, null,
                new Object[]{yes, no}, no);

        if (result == JOptionPane.NO_OPTION || result == JOptionPane.CLOSED_OPTION) {
            view.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        } else {
            view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

    }
    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
