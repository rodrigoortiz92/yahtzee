import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.util.Observer;
import java.util.Observable;

public class SetupView extends JDialog implements Observer {

    PlayerAddPane addPane;
    SetupController controller;

    public SetupView(SetupModel model, Window owner) {
        super(owner);
        setLayout(new GridBagLayout());

        this.model = model;
        this.model.addObserver(this);
        controller = new SetupController(this, this.model);

        pack();
    }

    public void update(Observable o, Object args){
    }
}
