import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.util.Observer;
import java.util.Observable;
import javax.swing.JButton;

public class SetupView extends JDialog implements Observer {

    private JPanel playerList;
    private SetupController controller;
    private JButton startButton;
    private JButton cancelButton;
    private PlayerAddPane addPane;
    private GameTypeSelectionPane gameTypeSelectionPane;

    public SetupView(SetupModel model, Window owner) {
        super(owner);
        controller = new SetupController(this, model);
        setLayout(new GridBagLayout());
        startButton = new JButton(controller.getStartButtonAction());
        cancelButton = new JButton(controller.getCancelButtonAction());
        addPane = new PlayerAddPane(controller);
        gameTypeSelectionPane = new GameTypeSelectionPane(model.getGameTypes());
        playerList = new JPanel();



        startButton = new JButton();

        EasyGridBagLayout.addToLayout(this, gameTypeSelectionPane, 0, 0);
        EasyGridBagLayout.addToLayout(this, playerList, 0, 1);
        EasyGridBagLayout.addToLayout(this, addPane, 1, 1);
        EasyGridBagLayout.addToLayout(this, startButton, 0, 2);
        EasyGridBagLayout.addToLayout(this, cancelButton, 1, 2);

        pack();
    }

    public void update(Observable o, Object args){
    }
}
