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

    JPanel PlayerList;
    SetupController controller;
    JButton startButton;
    JButton cancelButton;
    PlayerAddPane addPane = new PlayerAddPane(controller);

    public SetupView(SetupModel model, Window owner) {
        super(owner);
        controller = new SetupController(this, model);
        setLayout(new GridBagLayout());
        startButton = new JButton(controller.getStartButtonAction());
        cancelButton = new JButton(controller.getCancelButtonAction());

        controller = new SetupController(this, model);

        pack();
    }

    public void update(Observable o, Object args){
    }
}
