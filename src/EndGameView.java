
import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mikko Paukkonen
 */
public class EndGameView extends JDialog {

    EndGameController controller;

    public EndGameView(MainView mainView, GameModel model) {
        super(mainView, true);

        controller = new EndGameController(this, model);

        Container content = getContentPane();

        content.setLayout(new FlowLayout());
        content.add(new JButton(controller.getCloseAction()));
        content.add(new JButton(controller.getNewGameAction()));
        content.add(new JButton(controller.getShowStatisticsAction()));
    }
}
