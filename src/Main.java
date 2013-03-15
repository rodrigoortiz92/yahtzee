
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
            MainView view = new MainView();
            
            view.setVisible(true);
        }
        
    }
    
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Creator());
    }
}
