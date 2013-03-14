
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Stores the score cells of a player and associates a name with each cell.
 *
 * @author Mikko Paukkonen
 */
public class ScoreColumn {

    private CellNamePair[] pairs;

    private class CellNamePair {

        private ScoreCell cell;
        private String name;

        public CellNamePair(ScoreCell cell, String name) {
            this.cell = cell;
            this.name = name;
        }
    }

    public ScoreColumn(Player player) {
        ScoreCell ones = new SumOfKindCell(1);
        ScoreCell twos = new SumOfKindCell(2);
        ScoreCell threes = new SumOfKindCell(3);
        ScoreCell fours = new SumOfKindCell(4);
        ScoreCell fives = new SumOfKindCell(5);
        ScoreCell sixes = new SumOfKindCell(6);

        ScoreCell topSum = new SumCell(ones, twos, threes, fours, fives, sixes);

        ScoreCell topBonus = new BonusCell(topSum, 63, 50);

        ScoreCell pair = new PairCell();
        ScoreCell twoPairs = new TwoPairsCell();
        ScoreCell threeOfKind = new CountOfKindCell(3);
        ScoreCell fourOfKind = new CountOfKindCell(4);
        ScoreCell smallStraight = new SumOfGivenDiceCell(1, 2, 3, 4, 5);
        ScoreCell largeStraight = new SumOfGivenDiceCell(2, 3, 4, 5, 6);
        ScoreCell fullHouse = new FullHouseCell();
        ScoreCell chance = new SumOfAllDiceCell();
        ScoreCell yahtzee = new CountOfKindCell(5);

        SumCell bottomSum = new SumCell(pair, twoPairs, threeOfKind, fourOfKind,
                smallStraight, largeStraight, fullHouse, chance, yahtzee);

        SumCell total = new SumCell(topSum, bottomSum);

        pairs = new CellNamePair[]{
            new CellNamePair(ones, "Ones"),
            new CellNamePair(twos, "Twos"),
            new CellNamePair(threes, "Threes"),
            new CellNamePair(fours, "Fours"),
            new CellNamePair(fives, "Fives"),
            new CellNamePair(sixes, "Sixes"),
            new CellNamePair(topSum, "Top total"),
            new CellNamePair(topBonus, "Bonus"),
            new CellNamePair(pair, "Pair"),
            new CellNamePair(twoPairs, "Two pairs"),
            new CellNamePair(threeOfKind, "Three of a kind"),
            new CellNamePair(fourOfKind, "Four of a kind"),
            new CellNamePair(smallStraight, "Small straight"),
            new CellNamePair(largeStraight, "Large straight"),
            new CellNamePair(fullHouse, "Full house"),
            new CellNamePair(chance, "Chance"),
            new CellNamePair(yahtzee, "Yahtzee"),
            new CellNamePair(bottomSum, "Bottom total"),
            new CellNamePair(total, "Total")
        };

        for (CellNamePair cellNamePair : pairs) {
            ScoreCell cell = cellNamePair.cell;

            if (cell instanceof MarkableScoreCell) {
                MarkableScoreCell rollable = (MarkableScoreCell) cell;
                rollable.setPlayer(player);
            }
        }
    }

    public List<String> getCellNames() {
        List<String> names = new ArrayList<>(pairs.length);

        for (CellNamePair pair : pairs) {
            names.add(pair.name);
        }

        return Collections.unmodifiableList(names);
    }

    public List<ScoreCell> getCells() {
        List<ScoreCell> cells = new ArrayList<>(pairs.length);

        for (CellNamePair pair : pairs) {
            cells.add(pair.cell);
        }

        return Collections.unmodifiableList(cells);
    }
}
