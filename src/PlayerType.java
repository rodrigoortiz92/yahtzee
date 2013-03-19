/**
*
*@author Erkki Mattila
*/

public abstract class PlayerType {

    public abstract Player createPlayer(GameModel model, DiceModel diceModel,
            ScoreColumn column, String name);

    public abstract String getName();

}