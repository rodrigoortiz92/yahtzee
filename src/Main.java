
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
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

            List<PlayerType> playerTypes = new LinkedList<>();
            playerTypes.add(new HumanPlayerType());
            playerTypes.add(new AiPlayerType());

            List<GameType> gameTypes = new LinkedList<>();
            gameTypes.add(new NormalGameType());

            SetupModel setupModel = new SetupModel(playerTypes, gameTypes);

            MainView view = new MainView(gameView, diceView, setupModel);

            EndGameListener listener = new EndGameListener(view, gameModel);

            view.setVisible(true);


            view.pack();
        }
    }

    private static class EndGameListener implements Observer {

        private MainView mainView;
        private GameModel gameModel;

        public EndGameListener(MainView mainView, GameModel gameModel) {
            this.mainView = mainView;
            this.gameModel = gameModel;

            gameModel.addObserver(this);
        }

        @Override
        public void update(Observable o, Object arg) {
            if (arg instanceof GameModel.EndGameNotification) {
                EndGameView endGameView = new EndGameView(mainView, gameModel);
                endGameView.setVisible(true);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Creator());
    }
}
