
public class MaxiScoreColumn extends ScoreColumn {

    ScoreCell yahtzee;
    ScoreCell bonus;
    ScoreCell total;
    ScoreCell topTotal;

    public MaxiScoreColumn() {
        super();

        ScoreCell ones = new SumOfKindCell(1);
        ScoreCell twos = new SumOfKindCell(2);
        ScoreCell threes = new SumOfKindCell(3);
        ScoreCell fours = new SumOfKindCell(4);
        ScoreCell fives = new SumOfKindCell(5);
        ScoreCell sixes = new SumOfKindCell(6);

        topTotal = new SumCell(ones, twos, threes, fours, fives, sixes);

        bonus = new BonusCell(topTotal, 84, 100);

        ScoreCell pair = new CountPatternCell(2);
        ScoreCell twoPairs = new CountPatternCell(2, 2);
        ScoreCell threePairs = new CountPatternCell(2, 2, 2);
        ScoreCell threeOfKind = new CountPatternCell(3);
        ScoreCell fourOfKind = new CountPatternCell(4);
        ScoreCell fiveOfKind = new CountPatternCell(5);
        ScoreCell smallStraight = new SumOfGivenDiceCell(1, 2, 3, 4, 5);
        ScoreCell largeStraight = new SumOfGivenDiceCell(2, 3, 4, 5, 6);
        ScoreCell fullStraight = new SumOfGivenDiceCell(1, 2, 3, 4, 5, 6);
        ScoreCell house = new CountPatternCell(3, 2);
        ScoreCell fullHouse = new CountPatternCell(3, 3);
        ScoreCell tower = new CountPatternCell(4, 2);
        ScoreCell chance = new SumOfAllDiceCell();
        yahtzee = new YahtzeeCell(100);

        SumCell bottomSum = new SumCell(pair, twoPairs, threePairs, threeOfKind, fourOfKind,
                fiveOfKind, smallStraight, largeStraight, fullStraight, house,
                fullHouse, tower, chance, yahtzee);

        total = new SumCell(topTotal, bonus, bottomSum);

        addPair(ones, "Ones");
        addPair(twos, "Twos");
        addPair(threes, "Threes");
        addPair(fours, "Fours");
        addPair(fives, "Fives");
        addPair(sixes, "Sixes");
        addPair(topTotal, "Top total");
        addPair(bonus, "Bonus");
        addPair(pair, "Pair");
        addPair(twoPairs, "Two pairs");
        addPair(threePairs, "Three pairs");
        addPair(threeOfKind, "Three of a kind");
        addPair(fourOfKind, "Four of a kind");
        addPair(fiveOfKind, "Five of a kind");
        addPair(smallStraight, "Small straight");
        addPair(largeStraight, "Large straight");
        addPair(fullStraight, "Full straight");
        addPair(house, "House");
        addPair(fullHouse, "Full house");
        addPair(tower, "Tower");
        addPair(chance, "Chance");
        addPair(yahtzee, "Yahtzee");
        addPair(bottomSum, "Bottom total");
        addPair(total, "Total");
    }

    @Override
    public ScoreCell getBonusCell() {
        return bonus;
    }

    @Override
    public ScoreCell getTotalCell() {
        return total;
    }

    @Override
    public ScoreCell getTopTotalCell() {
        return topTotal;
    }

    @Override
    public ScoreCell getYahtzeeCell() {
        return yahtzee;
    }
}
