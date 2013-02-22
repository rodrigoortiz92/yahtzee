
import java.util.LinkedList;

/**
 *
 * @author Mikko Paukkonen
 */
public class GameModelTest {

    public static void printGameState(GameModel gameModel, DiceModel diceModel) {
        System.out.println("---------------");
        printDice(diceModel);
        System.out.println("---------------");
        printScoreTable(gameModel);
    }

    public static void printDice(DiceModel model) {
        System.out.print("Dice: ");

        for (int value : model.getDieValues())
            System.out.print(value + ", ");

        System.out.println();
    }

    public static void printScoreTable(GameModel model) {
        final int ROW_TITLE_FIELD = 25;
        final String ROW_TITLE_FORMAT = "%" + ROW_TITLE_FIELD + "s ";

        GameModel.PlayerColumn[] columns = model.getPlayerColumns();
        String[] rows = model.getScoreCellNames();

        System.out.print(String.format(ROW_TITLE_FORMAT, ""));

        for (GameModel.PlayerColumn column : columns) {
            String fieldFormat = "%" + column.name.length() + "s ";

            System.out.print(String.format(fieldFormat, column.name));

        }
        System.out.println();
        for (int i = 0; i < rows.length; ++i) {
            System.out.print(String.format(ROW_TITLE_FORMAT, rows[i]));

            for (GameModel.PlayerColumn column : columns) {
                GameModel.PlayerColumn.Cell cell = column.cells[i];
                String scoreFormat;


                if (cell.isFilled())
                    scoreFormat = "%d";
                else
                    scoreFormat = "(%d)";

                String score = String.format(scoreFormat, column.cells[i].getScore());

                String fieldFormat = "%" + column.name.length() + "s ";

                System.out.print(String.format(fieldFormat, score));


            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        DiceModel diceModel = new DiceModel();
        GameModel model = new GameModel(diceModel);

        Player player = new Player("Rupe");

        LinkedList<Player> players = new LinkedList<>();
        players.add(player);

        model.startNewGame(players);

        diceModel.dieValues = new int[]{1, 2, 1, 1, 1};
        printGameState(model, diceModel);

        model.getPlayerColumns()[0].cells[0].mark();

        diceModel.dieValues = new int[]{6, 6, 6, 6, 6};
        printGameState(model, diceModel);

        model.getPlayerColumns()[0].cells[5].mark();

        diceModel.dieValues = new int[]{4, 4, 4, 4, 4};
        printGameState(model, diceModel);

    }
}
