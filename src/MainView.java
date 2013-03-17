
import java.awt.Container;
import javax.swing.ImageIcon;
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

    public MainView(GameView gameView, DiceView diceView, SetupView setupView) {
        super("Yahtzee");
        this.controller = new MainController(setupView.controller);

        setIconImage(new ImageIcon(getClass().getResource("/images/icon.png")).getImage());

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
