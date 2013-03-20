
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mikko Paukkonen <mikko.paukkonen at uta.fi>
 */
public class Main {
    private static class Creator implements Runnable{

        @Override
        public void run() {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (UnsupportedLookAndFeelException | ClassNotFoundException |
                    InstantiationException | IllegalAccessException e) {
            }

            DiceModel diceModel = new DiceModel();

            GameModel gameModel = new GameModel(diceModel);

            GameView gameView = new GameView(gameModel, diceModel);

            DiceView diceView = new DiceView(diceModel);

            SetupModel setupModel = new SetupModel();

            MainView view = new MainView(gameView, diceView, setupModel);

            view.setVisible(true);

            List<Player> p = Arrays.asList(new HumanPlayer(gameModel, diceModel, "Player"), new AiPlayer(gameModel, diceModel, "Ai"), new AiPlayer(gameModel, diceModel, "Ai"), new AiPlayer(gameModel, diceModel, "Ai"), new AiPlayer(gameModel, diceModel, "Ai"));

            gameModel.startNewGame(p);

            view.pack();
        }
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Creator());
    }
}
