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

    PlayerAddPane addPane;
    JPanel PlayerList;
    SetupController controller;
    JButton startButton;
    PlayerAddPane addPane = new PlayerAddPane(

    public SetupView(SetupModel model, Window owner) {
        super(owner);
        setLayout(new GridBagLayout());
        startButton = new JButton();

        this.model = model;
        controller = new SetupController(this, this.model);

        pack();
    }

    public void update(Observable o, Object args){
    }
}
