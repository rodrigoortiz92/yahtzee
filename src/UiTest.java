
import java.util.LinkedList;
import javax.swing.SwingUtilities;

/**
 *
 * @author Mikko Paukkonen
 */
public class UiTest {

    public static class UiCreator implements Runnable {

        @Override
        public void run() {

            DiceModel diceModel = new DiceModel();
            GameModel model = new GameModel(diceModel);

            LinkedList<Player> players = new LinkedList<>();

            players.add(new Player(model, "Pelaaja #1"));
            players.add(new Player(model, "Pelaaja #2"));
            players.add(new Player(model, "Pelaaja #3"));

            model.startNewGame(players);

            GameView view = new GameView(model, diceModel);

            view.setVisible(true);

            DiceView diceView = new DiceView(diceModel);

            diceView.setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new UiCreator());
    }
}
