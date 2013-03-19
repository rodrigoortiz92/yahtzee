
import java.awt.Container;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mikko Paukkonen
 */
public class EndGameView extends JDialog {

    EndGameController controller;

    private class PlayerScoreComparator implements Comparator<Player> {

        @Override
        public int compare(Player o1, Player o2) {
            return -Integer.compare(o1.calculateScore(), o2.calculateScore());
        }
    }

    public EndGameView(MainView mainView, GameModel model) {
        super(mainView, true);
        setTitle("Game Over");

        controller = new EndGameController(this, model);

        List<Player> players = new LinkedList<>(model.getPlayers());

        Collections.sort(players, new PlayerScoreComparator());

        int score = players.get(0).calculateScore();

        List<Player> remove = new LinkedList<>();

        for (Player player : players) {
            if (player.calculateScore() < score) {
                remove.add(player);
            }
        }

        players.removeAll(remove);

        String text;

        if (players.size() == 1) {
            text = "Game Over!\n"
                    + "\n"
                    + "Player " + players.get(0).getName() + " won.";
        } else {
            text = "Game Over!\n"
                    + "\n"
                    + "The game ended in a draw between ";

            for (int i = 0; i < players.size(); ++i) {
                text += players.get(i).getName();

                if (i < players.size() - 2) {
                    text += ", ";
                } else if (i == players.size() - 2) {
                    text += " and ";
                }
            }

            text += ".";
        }

        Container content = new JOptionPane(text,
                JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION,
                null, controller.getOptions(), controller.getDefaultOption());
        content.addPropertyChangeListener(controller.getPaneValueListener());

        setContentPane(content);

        pack();
        setLocationRelativeTo(mainView);
    }
}
