/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mikko Paukkonen <mikko.paukkonen at uta.fi>
 */
public class AiPlayerType extends PlayerType{

    @Override
    public Player createPlayer(GameModel model, DiceModel diceModel, ScoreColumn column, String name) {
        return new AiPlayer(model, diceModel, column, name);
    }

    @Override
    public String getName() {
        return "AI";
    }

}
