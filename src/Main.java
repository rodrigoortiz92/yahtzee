
import java.util.LinkedList;
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

            List<GameModel.PlayerDescription> p = new LinkedList<>();

            p.add(new GameModel.PlayerDescription("Ai 1", new AiPlayerType()));
            p.add(new GameModel.PlayerDescription("Ai 2", new AiPlayerType()));
            p.add(new GameModel.PlayerDescription("Ai 3", new AiPlayerType()));
            p.add(new GameModel.PlayerDescription("Ai 4", new AiPlayerType()));

            GameModel gameModel = new GameModel(p, new NormalGameType());

            GameView gameView = new GameView(gameModel);

            DiceView diceView = new DiceView(gameModel.getDiceModel());

            SetupModel setupModel = new SetupModel();

            SetupView setupView = new SetupView(setupModel);

            MainView view = new MainView(gameView, diceView, setupView);

            view.setVisible(true);



            view.pack();
        }
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Creator());
    }
}
