
public class SevenScoreColumn extends ScoreColumn {

    ScoreCell yahtzee;
    ScoreCell bonus;
    ScoreCell total;
    ScoreCell topTotal;

    public SevenScoreColumn() {
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
        ScoreCell sixOfKind = new CountPatternCell(6);


        ScoreCell smallStraight = new SumOfGivenDiceCell(1, 2, 3, 4, 5);
        ScoreCell largeStraight = new SumOfGivenDiceCell(2, 3, 4, 5, 6);
        ScoreCell fullStraight = new SumOfGivenDiceCell(1, 2, 3, 4, 5, 6);
        ScoreCell doubleStraight = new CountPatternCell(1, 2, 2, 2);

        ScoreCell fullHouse = new CountPatternCell(3, 2);
        ScoreCell doubleHouse = new CountPatternCell(3, 2, 2);
        ScoreCell house = new CountPatternCell(4, 2);
        ScoreCell flagPole = new CountPatternCell(5, 2);
        ScoreCell sauna = new CountPatternCell(3, 3);
        ScoreCell flag = new CountPatternCell(4, 3);


        ScoreCell sevens1 = new SumOfKindCell(1, 6);
        ScoreCell sevens2 = new SumOfKindCell(2, 5);
        ScoreCell sevens3 = new SumOfKindCell(3, 4);
        ScoreCell sevenDivisible = new DivisibleSumCell(7);

        ScoreCell chance = new SumOfAllDiceCell();
        yahtzee = new YahtzeeCell(200);

        SumCell bottomSum = new SumCell(pair, twoPairs, threePairs, threeOfKind,
                fourOfKind, fiveOfKind, sixOfKind, smallStraight, largeStraight,
                fullStraight, doubleStraight, fullHouse, doubleHouse, house,
                flagPole, sauna, flag, sevens1, sevens2, sevens3,
                sevenDivisible, chance, yahtzee);

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
        addPair(sixOfKind, "Six of a kind");
        addPair(smallStraight, "Small straight");
        addPair(largeStraight, "Large straight");
        addPair(fullStraight, "Full straight");
        addPair(doubleStraight, "Double straight");

        addPair(fullHouse, "Full house");
        addPair(doubleHouse, "Double house");
        addPair(house, "House");
        addPair(flagPole, "Flag pole");
        addPair(sauna, "Sauna");
        addPair(flag, "Flag");
        addPair(chance, "Chance");

        addPair(sevens1, "Ones and sixes");
        addPair(sevens2, "Twos and fives");
        addPair(sevens3, "Threes and fours");
        addPair(sevenDivisible, "Divisible by 7");

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
