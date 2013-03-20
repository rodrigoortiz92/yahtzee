
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.Dimension2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

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
    private AboutAction aboutAction = new AboutAction();
    private MainWindowListener mainWindowListener = new MainWindowListener();
    private SetupModel setupModel;
    private MainView view;
    private final String WINDOW_X_KEY = "window.x";
    private final String WINDOW_Y_KEY = "window.y";
    private final String WINDOW_WIDTH_KEY = "window.width";
    private final String WINDOW_HEIGHT_KEY = "window.height";
    private final String PROPERTY_FILE = "properties";

    public MainController(MainView view, SetupModel setupModel) {
        this.view = view;
        this.setupModel = setupModel;

        loadProperties();
    }

    public NewGameAction getNewGameAction() {
        return newGameAction;
    }

    public ExitAction getExitAction() {
        return exitAction;
    }

    public AboutAction getAboutAction() {
        return aboutAction;
    }

    public MainWindowListener getMainWindowListener() {
        return mainWindowListener;
    }

    public class NewGameAction extends AbstractAction {

        public NewGameAction() {
            super("New Game");

            putValue(AbstractAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("F2"));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            SetupView view = new SetupView(setupModel, MainController.this.view);
            view.setVisible(true);
        }
    }

    public class ExitAction extends AbstractAction {

        public ExitAction() {
            super("Exit");

            putValue(AbstractAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("alt F4"));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            WindowEvent event = new WindowEvent(view, WindowEvent.WINDOW_CLOSING);
            MainController.this.view.dispatchEvent(event);
        }
    }

    public class AboutAction extends AbstractAction {

        public AboutAction() {
            super("About");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String ok = "Ok";

            int result = JOptionPane.showOptionDialog(view,
                    "Yahtzee\n"
                    + "\n"
                    + "by Erkki Mattila, Mikko Paukkonen & Markus Salmijärvi 2013\n",
                    "About Yahtzee", JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon(getClass().getResource("/images/icon.png")),
                    new Object[]{ok}, ok);
        }
    }

    private void loadProperties() {
        Properties properties = new Properties();

        FileInputStream in = null;

        try {
            in = new FileInputStream(PROPERTY_FILE);
            properties.load(in);
            int x = new Integer(properties.getProperty(WINDOW_X_KEY));
            int y = new Integer(properties.getProperty(WINDOW_Y_KEY));
            int height = new Integer(properties.getProperty(WINDOW_HEIGHT_KEY));
            int width = new Integer(properties.getProperty(WINDOW_WIDTH_KEY));

            view.setBounds(x, y, width, height);
        } catch (FileNotFoundException e) {
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveProperties() {
        Properties properties = new Properties();

        Rectangle bounds = view.getBounds();

        properties.setProperty(WINDOW_X_KEY, Integer.toString(bounds.x));
        properties.setProperty(WINDOW_Y_KEY, Integer.toString(bounds.y));

        Dimension2D size = view.getSize();

        properties.setProperty(WINDOW_WIDTH_KEY, Integer.toString((int) size.getWidth()));
        properties.setProperty(WINDOW_HEIGHT_KEY, Integer.toString((int) size.getHeight()));

        FileOutputStream out = null;

        try {
            out = new FileOutputStream(PROPERTY_FILE);
            properties.store(out, "---Properties---");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public class MainWindowListener implements WindowListener {

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

                saveProperties();
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
}
