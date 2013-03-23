
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class TitledPanel extends JPanel {

    public TitledPanel(String title) {
        Border etchedBorder = BorderFactory.createEtchedBorder(
                EtchedBorder.LOWERED);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
                etchedBorder, title);
        titledBorder.setTitleJustification(TitledBorder.LEFT);
        this.setBorder(titledBorder);
    }
}
