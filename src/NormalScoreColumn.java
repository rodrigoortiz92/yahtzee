/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mikko Paukkonen <mikko.paukkonen at uta.fi>
 */
public class NormalScoreColumn extends ScoreColumn {

    ScoreCell yahtzee;
    ScoreCell bonus;
    ScoreCell total;

    public NormalScoreColumn() {
        super();

        ScoreCell ones = new SumOfKindCell(1);
        ScoreCell twos = new SumOfKindCell(2);
        ScoreCell threes = new SumOfKindCell(3);
        ScoreCell fours = new SumOfKindCell(4);
        ScoreCell fives = new SumOfKindCell(5);
        ScoreCell sixes = new SumOfKindCell(6);

        ScoreCell topSum = new SumCell(ones, twos, threes, fours, fives, sixes);

        bonus = new BonusCell(topSum, 63, 50);

        ScoreCell pair = new PairCell();
        ScoreCell twoPairs = new TwoPairsCell();
        ScoreCell threeOfKind = new CountOfKindCell(3);
        ScoreCell fourOfKind = new CountOfKindCell(4);
        ScoreCell smallStraight = new SumOfGivenDiceCell(1, 2, 3, 4, 5);
        ScoreCell largeStraight = new SumOfGivenDiceCell(2, 3, 4, 5, 6);
        ScoreCell fullHouse = new FullHouseCell();
        ScoreCell chance = new SumOfAllDiceCell();
        yahtzee = new YahtzeeCell(50);

        SumCell bottomSum = new SumCell(pair, twoPairs, threeOfKind, fourOfKind,
                smallStraight, largeStraight, fullHouse, chance, yahtzee);

        total = new SumCell(topSum, bonus, bottomSum);

        addPair(ones, "Ones");
        addPair(twos, "Twos");
        addPair(threes, "Threes");
        addPair(fours, "Fours");
        addPair(fives, "Fives");
        addPair(sixes, "Sixes");
        addPair(topSum, "Top total");
        addPair(bonus, "Bonus");
        addPair(pair, "Pair");
        addPair(twoPairs, "Two pairs");
        addPair(threeOfKind, "Three of a kind");
        addPair(fourOfKind, "Four of a kind");
        addPair(smallStraight, "Small straight");
        addPair(largeStraight, "Large straight");
        addPair(fullHouse, "Full house");
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
    public ScoreCell getYahtzeeCell() {
        return yahtzee;
    }
}
