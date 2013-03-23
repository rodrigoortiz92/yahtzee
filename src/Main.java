
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

    private static class Creator implements Runnable {

        @Override
        public void run() {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (UnsupportedLookAndFeelException | ClassNotFoundException |
                    InstantiationException | IllegalAccessException e) {
            }

            List<GameModel.PlayerDescription> p = new LinkedList<>();

            GameModel gameModel = new GameModel(p, new NormalGameType(), false);

            List<PlayerType> playerTypes = new LinkedList<>();
            playerTypes.add(new HumanPlayerType());
            playerTypes.add(new AiPlayerType());

            List<GameType> gameTypes = new LinkedList<>();
            gameTypes.add(new NormalGameType());
            gameTypes.add(new MaxiGameType());
            gameTypes.add(new SevenGameType());

            MainModel mainModel = new MainModel(gameModel);

            SetupModel setupModel = new SetupModel(playerTypes, gameTypes, mainModel);

            AddPlayerModel addPlayerModel = new AddPlayerModel(setupModel);

            AddPlayerView addPlayerView = new AddPlayerView(addPlayerModel);

            SetupViewFactory setupViewFactory =
                    new SetupViewFactory(addPlayerView,
                    setupModel, addPlayerModel);

            MainView view = new MainView(mainModel, setupViewFactory);

            setupViewFactory.setOwner(view);

            view.setVisible(true);

            mainModel.setGameModel(gameModel);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Creator());
    }
}
