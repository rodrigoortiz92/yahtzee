
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.Insets;
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
        this.controller = new MainController(this, setupView.controller);
        addWindowListener(controller);

        setIconImage(new ImageIcon(getClass().getResource("/images/icon.png")).getImage());

        JMenuBar menu = new JMenuBar();

        JMenu gameMenu = new JMenu("Game");
        menu.add(gameMenu);

        gameMenu.add(new JMenuItem(controller.getNewGameAction()));
        gameMenu.add(new JMenuItem(controller.getExitAction()));

        setJMenuBar(menu);

        Container content = getContentPane();

        content.setLayout(new EasyGridBagLayout());

        GridBagConstraints c = new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5, 5), 0, 0);
        add(gameView, c);

        GridBagConstraints c2 = new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.NONE,
                new Insets(5, 5, 5, 5), 0, 0);
        add(diceView, c2);

        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
