
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
