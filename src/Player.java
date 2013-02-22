
/**
 *
 * @author Mikko Paukkonen
 */
public class Player {

    private String name;
    private ScoreColumn scoreColumn;

    public String getName() {
        return name;
    }

    public ScoreCell[] getScoreCells() {
        return scoreColumn.getCells();
    }

    public Player(String name) {
        this.name = name;
        this.scoreColumn = new ScoreColumn();
    }
}
