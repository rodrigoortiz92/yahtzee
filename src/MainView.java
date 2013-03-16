
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Mikko Paukkonen <mikko.paukkonen at uta.fi>
 */
public class MainView extends JFrame {

    MainController controller;
    SetupView setupView;

    public MainView(GameView gameView, SetupView setupView) {
        super("Yahtzee");
        this.controller = new MainController(setupView.controller);

        JMenuBar menu = new JMenuBar();

        JMenu gameMenu = new JMenu("Game");
        menu.add(gameMenu);

        gameMenu.add(new JMenuItem(controller.getNewGameAction()));
        gameMenu.add(new JMenuItem(controller.getExitAction()));

        setJMenuBar(menu);

        Container content = getContentPane();

        content.add(gameView, BorderLayout.CENTER);

        pack();
    }
}
