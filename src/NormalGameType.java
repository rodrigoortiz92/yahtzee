/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mikko Paukkonen <mikko.paukkonen at uta.fi>
 */
public class NormalGameType extends GameType{

    @Override
    public ScoreColumn createScoreColumn() {
        return new NormalScoreColumn();
    }

    @Override
    public int getDieCount() {
        return 5;
    }

    @Override
    public String getName() {
        return "Normal";
    }

}
