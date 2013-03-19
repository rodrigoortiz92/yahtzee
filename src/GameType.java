/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mikko Paukkonen <mikko.paukkonen at uta.fi>
 */
abstract public class GameType {
    abstract public ScoreColumn createScoreColumn();
    abstract public int getDieCount();
    abstract public String getName();
}
