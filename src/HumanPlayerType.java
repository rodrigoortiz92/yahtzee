
public class HumanPlayerType extends PlayerType{

    @Override
    public Player createPlayer(GameModel model, DiceModel diceModel, ScoreColumn column, String name) {
        return new HumanPlayer(model, diceModel, column, name);
    }

    @Override
    public String getName() {
        return "Human";
    }

}
