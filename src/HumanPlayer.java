
public class HumanPlayer extends Player {

    public HumanPlayer(GameModel model, DiceModel diceModel, ScoreColumn column, String name) {
        super(model, diceModel, column, name);
    }

    @Override
    void playTurn(DiceModel diceModel) {
    }

    @Override
    boolean acceptsUiInput() {
        return true;
    }
}
