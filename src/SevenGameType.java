
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
