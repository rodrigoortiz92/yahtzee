/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mikko Paukkonen <mikko.paukkonen at uta.fi>
 */
public class MaxiGameType extends GameType {

    @Override
    public ScoreColumn createScoreColumn() {
        return new MaxiScoreColumn();
    }

    @Override
    public int getDieCount() {
        return 6;
    }

    @Override
    public String getName() {
        return "Maxi";
    }

}
