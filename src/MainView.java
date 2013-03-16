
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

    public MainView(GameView gameView, DiceView diceView) {
        super("Yahtzee");
        this.controller = new MainController();

        JMenuBar menu = new JMenuBar();

        JMenu gameMenu = new JMenu("Game");
        menu.add(gameMenu);

        gameMenu.add(new JMenuItem(controller.getNewGameAction()));
        gameMenu.add(new JMenuItem(controller.getExitAction()));

        setJMenuBar(menu);

        Container content = getContentPane();

        content.setLayout(new EasyGridBagLayout());

        EasyGridBagLayout.addToLayout(this, gameView, 0, 0);
        EasyGridBagLayout.addToLayout(this, diceView, 0, 1);

        pack();
    }
}
