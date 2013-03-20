/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mikko Paukkonen <mikko.paukkonen at uta.fi>
 */
public class SevenGameType extends GameType{

    @Override
    public ScoreColumn createScoreColumn() {
        return new SevenScoreColumn();
    }

    @Override
    public int getDieCount() {
        return 7;
    }

    @Override
    public String getName() {
        return "7-yahtzee";
    }

}
