
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Mikko Paukkonen <mikko.paukkonen at uta.fi>
 */
public class MainView extends JFrame implements Observer{

    MainController controller;
    MainModel model;

    public MainView(MainModel model, SetupModel setupModel) {
        super("Yahtzee");
        this.controller = new MainController(this, model, setupModel);
        this.model = model;
        addWindowListener(controller.getMainWindowListener());

        setIconImage(new ImageIcon(getClass().getResource("/images/icon.png")).getImage());

        JMenuBar menu = new JMenuBar();

        JMenu gameMenu = new JMenu("Game");
        menu.add(gameMenu);

        gameMenu.add(new JMenuItem(controller.getNewGameAction()));
        gameMenu.add(new JMenuItem(controller.getExitAction()));

         JMenu helpMenu = new JMenu("Help");
        menu.add(helpMenu);

        helpMenu.add(new JMenuItem(controller.getAboutAction()));

        setJMenuBar(menu);

        createViews();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createViews()
    {
        Container content = getContentPane();
        content.removeAll();

        content.setLayout(new EasyGridBagLayout());

        GridBagConstraints c = new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL,
                new Insets(5, 5, 5, 5), 0, 0);
        add(new GameView(model.getGameModel()), c);

        GridBagConstraints c2 = new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.NONE,
                new Insets(5, 5, 5, 5), 0, 0);
        add(new DiceView(model.getGameModel().getDiceModel()), c2);

        validate();
        pack();
    }

    @Override
    public void update(Observable o, Object arg) {
        createViews();
    }
}
