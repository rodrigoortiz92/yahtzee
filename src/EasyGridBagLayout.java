import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class EasyGridBagLayout extends GridBagLayout {

    public static void addToLayout(Container container, Component c, int x,
            int y) {
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = x;
        constraints.gridy = y;
        constraints.weightx = 0.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        container.add(c, constraints);
    }
}
