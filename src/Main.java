
import javax.swing.SwingUtilities;

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
            DiceModel diceModel = new DiceModel();

            GameModel gameModel = new GameModel(diceModel);

            GameView gameView = new GameView(gameModel, diceModel);

            MainView view = new MainView(gameView);

            view.setVisible(true);
        }
        
    }
    
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Creator());
    }
}
