
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
