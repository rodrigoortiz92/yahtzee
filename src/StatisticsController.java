
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Comparator;
import javax.swing.AbstractAction;
import javax.swing.JComboBox;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mikko Paukkonen
 */
public class StatisticsController {

    StatisticsView view;
    private SortOptionListener sortOptionListener = new SortOptionListener();
    private CloseAction closeAction = new CloseAction();

    public SortOptionListener getSortOptionListener() {
        return sortOptionListener;
    }

    public class SortOptionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() instanceof JComboBox) {
                JComboBox selection = (JComboBox) e.getSource();
                if (selection.getSelectedItem() instanceof SortOption) {
                    SortOption option = (SortOption) selection.getSelectedItem();
                    StatisticsController.this.view.sort(option.comparator);
                }
            }
        }
    }

    public static class PlayerScoreComparator implements Comparator<Player> {

        @Override
        public int compare(Player o1, Player o2) {
            return -Integer.compare(o1.calculateScore(), o2.calculateScore());
        }
    }

    public static class PlayerTopScoreComparator implements Comparator<Player> {

        @Override
        public int compare(Player o1, Player o2) {
            return -Integer.compare(o1.calculateTopScore(), o2.calculateTopScore());
        }
    }

    public static class PlayerNameComparator implements Comparator<Player> {

        @Override
        public int compare(Player o1, Player o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    public static class SortOption {

        public Comparator<Player> comparator;
        public String name;

        public SortOption(Comparator<Player> comparator, String name) {
            this.comparator = comparator;
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public StatisticsController(StatisticsView view) {
        this.view = view;
    }

    public CloseAction getCloseAction() {
        return closeAction;
    }

    public SortOption[] getSortOptions() {
        return new SortOption[]{
                    new SortOption(new PlayerScoreComparator(), "Total"),
                    new SortOption(new PlayerTopScoreComparator(), "Top total only"),
                    new SortOption(new PlayerNameComparator(), "Player name")
                };
    }

    public class CloseAction extends AbstractAction {

        public CloseAction() {
            super("Close");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            StatisticsController.this.view.dispose();
        }
    }
}
